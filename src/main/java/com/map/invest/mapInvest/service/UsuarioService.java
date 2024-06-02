package com.map.invest.mapInvest.service;

import com.google.common.base.Strings;
import com.map.invest.mapInvest.canonico.*;
import com.map.invest.mapInvest.entity.*;
import com.map.invest.mapInvest.filtro.FiltroWrapper;
import com.map.invest.mapInvest.repository.DocumentoRepositorio;
import com.map.invest.mapInvest.repository.EnderecoRepositorio;
import com.map.invest.mapInvest.repository.UsuarioRepositorio;
import com.map.invest.mapInvest.util.constantes.SexoEnum;
import com.map.invest.mapInvest.util.validacao.MapInvestMensagens;
import com.map.invest.mapInvest.util.validacao.exception.ValidacaoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Service
public class UsuarioService {

    private static final Pattern EMAIL_REGEX = Pattern.compile("^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$");
    @Autowired
    private UsuarioRepositorio usuarioRepositorio;
    @Autowired
    private DocumentoService documentoService;
    @Autowired
    private DocumentoRepositorio documentoRepositorio;
    @Autowired
    private EnderecoRepositorio enderecoRepositorio;
    @Autowired
    private TelefoneService telefoneService;
    @Autowired
    private AcessoService acessoService;
    @Autowired
    private EnderecoService enderecoService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public UsuarioCanonico buscaUsuario(Long usuarioID) {
        return Optional.ofNullable(usuarioRepositorio.buscaUsuario(usuarioID)).orElseThrow(
                () -> new ValidacaoException(MapInvestMensagens.ERRO_VALIDACAO_COD_USUARIO_NAO_EXISTE.getValor()));
    }

    public Usuario buscarUsuario(Long usuarioID) {
        return Optional.ofNullable(usuarioRepositorio.busca(Usuario.class, usuarioID)).orElseThrow(
                () -> new ValidacaoException(MapInvestMensagens.ERRO_VALIDACAO_COD_USUARIO_NAO_EXISTE.getValor()));
    }

    public List<UsuarioCanonico> buscaUsuarios(FiltroWrapper filtro) {
        return usuarioRepositorio.buscaUsuarios(filtro);
    }

    public UsuarioCanonico criaUsuario(UsuarioCanonico usuario) {
        validaIdUsuario(usuario);
        validaEmailUsuario(usuario);
        documentoService.validaNumDocumento(usuario);
        acessoService.validaLoginUsuario(usuario);
        validaDadosUsuario(usuario);
        Long usuarioSalvo = salvaUsuario(usuario);
        return buscaUsuario(usuarioSalvo);
    }

    private void validaIdUsuario(UsuarioCanonico usuario) {
        UsuarioCanonico user = usuarioRepositorio.buscaUsuario(usuario.getUsuarioID());
        if (user != null) {
            throw new ValidacaoException(MapInvestMensagens.ERRO_VALIDACAO_COD_USUARIO_JA_EXISTE.getValor());
        }
    }

    private void validaEmailUsuario(UsuarioCanonico usuario) {
        Usuario userEmail = usuarioRepositorio.buscarPorEmail(usuario.getEmail());
        if (userEmail != null) {
            throw new ValidacaoException(MapInvestMensagens.ERRO_VALIDACAO_EMAIL_JA_EXISTE.getValor());
        }
    }

    public Long editaUsuario(UsuarioCanonico usuarioCanonico) {
        Usuario usuario = buscarUsuario(usuarioCanonico.getUsuarioID());
        validaUsuario(usuario, usuarioCanonico);
        popularUsuario(usuario, usuarioCanonico);
        usuario = usuarioRepositorio.merge(usuario);
        return usuario.getUsuarioID();
    }

    private void popularUsuario(Usuario usuario, UsuarioCanonico usuarioCanonico) {
        usuario.setNome(usuarioCanonico.getNome());
        usuario.setSobreNome(usuarioCanonico.getSobreNome());
        usuario.setDataNascimento(usuarioCanonico.getDataNascimento());
        usuario.setSexo(usuarioCanonico.getSexo());
        usuario.setEmail(usuarioCanonico.getEmail());
        popularDocumento(usuario, usuarioCanonico.getDocumento());
        popularEndereco(usuario, usuarioCanonico.getEndereco());
        popularTelefone(usuario, usuarioCanonico.getTelefones());
        popularAcesso(usuario, usuarioCanonico.getAcesso());
    }

    private void popularDocumento(Usuario usuario, DocumentoCanonico documentoCanonico) {
        Documento documento = usuario.getDocumento();
        if (documento == null) {
            documento = new Documento();
            documento.setUsuarioID(usuario.getUsuarioID());
            usuario.setDocumento(documento);
        }
        documento.setTipoDocumento(documentoCanonico.getTipoDocumento());
        documento.setNumeroDocumento(documentoCanonico.getNumeroDocumento());
    }

    private void popularEndereco(Usuario usuario, EnderecoCanonico enderecoCanonico) {
        Endereco endereco = usuario.getEndereco();
        if (endereco == null) {
            endereco = new Endereco();
            endereco.setUsuarioID(usuario.getUsuarioID());
            usuario.setEndereco(endereco);
        }
        endereco.setTipoEndereco(enderecoCanonico.getTipoEndereco());
        endereco.setCep(enderecoCanonico.getCep());
        endereco.setRua(enderecoCanonico.getRua());
        endereco.setNumero(enderecoCanonico.getNumero());
        endereco.setComplemento(enderecoCanonico.getComplemento());
        endereco.setCidade(enderecoCanonico.getCidade());
        endereco.setUf(enderecoCanonico.getUf());
    }

    private void popularTelefone(Usuario usuario, List<TelefoneCanonico> telefonesCanonicos) {
        List<Telefone> telefonesExistentes = new ArrayList<>(usuario.getTelefones());

        Map<Long, Telefone> mapaTelefonesExistentes = telefonesExistentes.stream()
                .collect(Collectors.toMap(Telefone::getTelefoneID, telefone -> telefone));

        // Set de IDs de telefones canônicos para controle de exclusão
        Set<Long> idsTelefonesCanonicos = telefonesCanonicos.stream()
                .map(TelefoneCanonico::getTelefoneID).collect(Collectors.toSet());

        // Atualiza os telefones existentes ou adiciona novos
        for (TelefoneCanonico telefoneCanonico : telefonesCanonicos) {
            Telefone telefoneExistente = mapaTelefonesExistentes.get(telefoneCanonico.getTelefoneID());
            if (telefoneExistente != null) {
                // Atualiza o telefone existente
                telefoneExistente.setCodigo(telefoneCanonico.getCodigo());
                telefoneExistente.setTipoTelefone(telefoneCanonico.getTipoTelefone());
                telefoneExistente.setNumeroTelefone(telefoneCanonico.getNumeroTelefone());
            } else {
                // Adiciona novo telefone
                Telefone novoTelefone = new Telefone();
                novoTelefone.setUsuarioID(usuario.getUsuarioID());
                novoTelefone.setCodigo(telefoneCanonico.getCodigo());
                novoTelefone.setTipoTelefone(telefoneCanonico.getTipoTelefone());
                novoTelefone.setNumeroTelefone(telefoneCanonico.getNumeroTelefone());
                telefonesExistentes.add(novoTelefone);
            }
        }
        // Lista para armazenar os IDs dos telefones removidos
        List<Long> idsTelefonesRemovidos = new ArrayList<>();

        // Remove os telefones que não estão mais na lista canônica e armazena os IDs dos telefones removidos
        telefonesExistentes.removeIf(telefone -> {
            if (!idsTelefonesCanonicos.contains(telefone.getTelefoneID())) {
                idsTelefonesRemovidos.add(telefone.getTelefoneID());
                return true; // Remove o telefone da lista
            }
            return false; // Mantém o telefone na lista
        });

        // Remove os telefones do banco de dados e limpa a lista de telefones
        // do usuário, se houver algum telefone removido
        if (!idsTelefonesRemovidos.isEmpty()) {
            usuario.getTelefones().clear();
            idsTelefonesRemovidos.forEach(telefoneID -> {
                telefoneService.removeTelefone(telefoneID);
            });
        } else {
            // Atualiza a lista de telefones do usuário
            usuario.setTelefones(telefonesExistentes);
        }
    }

    private void popularAcesso(Usuario usuario, AcessoCanonico acessoCanonico) {
        Acesso acesso = usuario.getAcesso();
        if (acesso == null) {
            acesso = new Acesso();
            acesso.setUsuarioID(usuario.getUsuarioID());
            usuario.setAcesso(acesso);
        }
        acesso.setPerfilID(acessoCanonico.getPerfilID());
        acesso.setLogin(acessoCanonico.getLogin());
        String senhaCriptografada = passwordEncoder.encode(acessoCanonico.getSenha());
        acesso.setSenha(senhaCriptografada);
    }

    private Long salvaUsuario(UsuarioCanonico canonico) {
        Usuario usuario = geraUsuario(canonico);
        usuario.setNome(canonico.getNome());
        usuario.setSobreNome(canonico.getSobreNome());
        usuario.setDataNascimento(canonico.getDataNascimento());
        usuario.setSexo(canonico.getSexo());
        usuario.setEmail(canonico.getEmail());
        Usuario usuarioSalvo = usuarioRepositorio.salvaUsuario(usuario);
        salvaDocumento(canonico, usuarioSalvo);
        salvaEndereco(canonico, usuarioSalvo);
        salvaTelefones(canonico, usuarioSalvo);
        salvaAcesso(canonico, usuarioSalvo);
        return usuarioSalvo.getUsuarioID();
    }

    private void salvaDocumento(UsuarioCanonico canonico, Usuario usuarioSalvo) {
        Documento documento = new Documento();
        documento.setUsuarioID(usuarioSalvo.getUsuarioID());
        documento.setTipoDocumento(canonico.getDocumento().getTipoDocumento());
        documento.setNumeroDocumento(canonico.getDocumento().getNumeroDocumento());
        documentoRepositorio.merge(documento);
    }

    private void salvaEndereco(UsuarioCanonico usuario, Usuario usuarioSalvo) {
        Endereco endereco = new Endereco();
        endereco.setUsuarioID(usuarioSalvo.getUsuarioID());
        endereco.setTipoEndereco(usuario.getEndereco().getTipoEndereco());
        endereco.setCep(usuario.getEndereco().getCep());
        endereco.setRua(usuario.getEndereco().getRua());
        endereco.setNumero(usuario.getEndereco().getNumero());
        endereco.setComplemento(usuario.getEndereco().getComplemento());
        endereco.setCidade(usuario.getEndereco().getCidade());
        endereco.setUf(usuario.getEndereco().getUf());
        enderecoRepositorio.merge(endereco);
    }

    private void salvaTelefones(UsuarioCanonico usuario, Usuario usuarioSalvo) {
        Optional.ofNullable(usuario.getTelefones()).ifPresent(tel -> {
            persisteTelefones(usuarioSalvo, tel);
        });
    }

    private void persisteTelefones(Usuario usuarioSalvo, List<TelefoneCanonico> tel) {
        List<Telefone> telefones = tel.stream().map(telefone -> montaTelefone(usuarioSalvo, telefone)).collect(Collectors.toList());
        usuarioSalvo.retemTelefone(telefones);
    }

    private Telefone montaTelefone(Usuario usuarioSalvo, TelefoneCanonico telefone) {
        Telefone t = usuarioSalvo.novoTelefone(telefone.getTelefoneID());
        t.setCodigo(telefone.getCodigo());
        t.setTipoTelefone(telefone.getTipoTelefone());
        t.setNumeroTelefone(telefone.getNumeroTelefone());
        return t;
    }

    private void salvaAcesso(UsuarioCanonico usuario, Usuario usuarioSalvo) {
        Acesso acesso = new Acesso();
        acesso.setUsuarioID(usuarioSalvo.getUsuarioID());
        acesso.setPerfilID(usuario.getAcesso().getPerfilID());
        acesso.setLogin(usuario.getAcesso().getLogin());
        String senhaCriptografada = passwordEncoder.encode(usuario.getAcesso().getSenha());
        acesso.setSenha(senhaCriptografada);
        usuarioRepositorio.merge(acesso);
    }

    private Usuario geraUsuario(UsuarioCanonico canonico) {
        if (canonico.getUsuarioID() == null) {
            return new Usuario();
        }
        return usuarioRepositorio.busca(canonico.getUsuarioID());
    }

    private void validaUsuario(Usuario usuario, UsuarioCanonico usuarioCanonico) {
        validaDadosUsuario(usuarioCanonico);
    }

    private void validaDadosUsuario(UsuarioCanonico usuarioCanonico) {
        DocumentoCanonico documentoCanonico = usuarioCanonico.getDocumento();
        EnderecoCanonico enderecoCanonico = usuarioCanonico.getEndereco();
        List<TelefoneCanonico> telefoneCanonico = usuarioCanonico.getTelefones();
        AcessoCanonico acessoCanonico = usuarioCanonico.getAcesso();


        if (Strings.isNullOrEmpty(usuarioCanonico.getNome())) {
            throw new ValidacaoException(MapInvestMensagens.ERRO_VALIDACAO_NOME_OBRIGATORIO.getValor());
        }

        if (Strings.isNullOrEmpty(usuarioCanonico.getSobreNome())) {
            throw new ValidacaoException(MapInvestMensagens.ERRO_VALIDACAO_SOBRENOME_OBRIGATORIO.getValor());
        }

        if (usuarioCanonico.getDataNascimento() == null) {
            throw new ValidacaoException(MapInvestMensagens.ERRO_VALIDACAO_DATA_NASCIMENTO_OBRIGATORIO.getValor());
        }

        SexoEnum sexo = usuarioCanonico.getSexo();
        if (sexo == null) {
            throw new ValidacaoException(MapInvestMensagens.ERRO_VALIDACAO_SEXO_OBRIGATORIO.getValor());
        }
        if (!sexo.equals(SexoEnum.F) && !sexo.equals(SexoEnum.M)) {
            throw new ValidacaoException(MapInvestMensagens.ERRO_VALIDACAO_SEXO_INVALIDO.getValor());
        }

        if (Strings.isNullOrEmpty(usuarioCanonico.getEmail())) {
            throw new ValidacaoException(MapInvestMensagens.ERRO_VALIDACAO_EMAIL_OBRIGATORIO.getValor());
        }

        if (!EMAIL_REGEX.matcher(usuarioCanonico.getEmail()).matches()) {
            throw new ValidacaoException(MapInvestMensagens.ERRO_VALIDACAO_EMAIL_CARACTERES_INVALIDOS.getValor());
        }

        documentoService.validaDadosDocumento(documentoCanonico);
        enderecoService.validaDadosEndereco(enderecoCanonico);
        acessoService.validaDadosAcesso(acessoCanonico);

        for (TelefoneCanonico telefones : telefoneCanonico) {
            telefoneService.validaDadosTelefone(telefones);
        }
    }

    public void removeUsuario(Long usuarioID) {
        Usuario usuario = buscarUsuario(usuarioID);
        usuarioRepositorio.remove(usuario);
    }
}
