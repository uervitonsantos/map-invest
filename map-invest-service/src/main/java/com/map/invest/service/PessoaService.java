package com.map.invest.service;

import com.google.common.base.Strings;
import com.map.invest.canonico.*;
import com.map.invest.entity.*;
import com.map.invest.filtro.FiltroWrapper;
import com.map.invest.repository.*;
import com.map.invest.util.validacao.MapInvestMensagens;
import com.map.invest.util.validacao.exception.ValidacaoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Service
public class PessoaService {

    private static final Pattern EMAIL_REGEX = Pattern.compile("^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$");
    @Autowired
    private PessoaRepositorio pessoaRepositorio;
    @Autowired
    private DocumentoPrincipalService documentoPrincipalService;
    @Autowired
    private DocumentoPrincipalRepositorio documentoPrincipalRepositorio;
    @Autowired
    private PessoaFisicaRepositorio pessoaFisicaRepositorio;
    @Autowired
    private PessoaJuridicaRepositorio pessoaJuridicaRepositorio;
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

    public PessoaCanonico buscaPessoa(Long pessoaID) {
        return Optional.ofNullable(pessoaRepositorio.buscaPessoa(pessoaID)).orElseThrow(
                () -> new ValidacaoException(MapInvestMensagens.ERRO_VALIDACAO_COD_USUARIO_NAO_EXISTE.getValor()));
    }

    public Pessoa buscarPessoa(Long pessoaID) {
        return Optional.ofNullable(pessoaRepositorio.busca(Pessoa.class, pessoaID)).orElseThrow(
                () -> new ValidacaoException(MapInvestMensagens.ERRO_VALIDACAO_COD_USUARIO_NAO_EXISTE.getValor()));
    }

    public List<PessoaCanonico> buscaPessoas(FiltroWrapper filtro) {
        return pessoaRepositorio.buscaPessoas(filtro);
    }

    public PessoaCanonico criaPessoa(PessoaCanonico pessoa, Endereco enderecoResult) {
        validaIdPessoa(pessoa);
        validaEmailPessoa(pessoa);
        documentoPrincipalService.validaNumDocumentoPrincipal(pessoa);
        acessoService.validaLoginPessoa(pessoa);
        validaDadosPessoa(pessoa, enderecoResult);
        Long pessoaSalvo = salvaPessoa(pessoa, enderecoResult);
        return buscaPessoa(pessoaSalvo);
    }

    private void validaIdPessoa(PessoaCanonico pessoa) {
        PessoaCanonico user = pessoaRepositorio.buscaPessoa(pessoa.getPessoaID());
        if (user != null) {
            throw new ValidacaoException(MapInvestMensagens.ERRO_VALIDACAO_COD_USUARIO_JA_EXISTE.getValor());
        }
    }

    private void validaEmailPessoa(PessoaCanonico pessoa) {
        Pessoa userEmail = pessoaRepositorio.buscarPorEmail(pessoa.getEmail());
        if (userEmail != null) {
            throw new ValidacaoException(MapInvestMensagens.ERRO_VALIDACAO_EMAIL_JA_EXISTE.getValor());
        }
    }

    public Long editaPessoa(PessoaCanonico pessoaCanonico, Endereco enderecoResult) {
        Pessoa pessoa = buscarPessoa(pessoaCanonico.getPessoaID());
        validaPessoa(pessoa, pessoaCanonico, enderecoResult);
        popularPessoa(pessoa, pessoaCanonico, enderecoResult);
        pessoa = pessoaRepositorio.merge(pessoa);
        return pessoa.getPessoaID();
    }

    private void popularPessoa(Pessoa pessoa, PessoaCanonico pessoaCanonico, Endereco enderecoResult) {
        pessoa.setNome(pessoaCanonico.getNome());
        pessoa.setEmail(pessoaCanonico.getEmail());
        popularDocumentoPrincipal(pessoa, pessoaCanonico.getDocumentoPrincipal());
        popularEndereco(pessoa, pessoaCanonico.getEndereco(), enderecoResult);
        popularTelefone(pessoa, pessoaCanonico.getTelefones());
        popularAcesso(pessoa, pessoaCanonico.getAcesso());
    }

    private void popularDocumentoPrincipal(Pessoa pessoa, DocumentoPrincipalCanonico documentoPrincipalCanonico) {
        DocumentoPrincipal documentoPrincipal = pessoa.getDocumentoPrincipal();
        if (documentoPrincipal == null) {
            documentoPrincipal = new DocumentoPrincipal();
            documentoPrincipal.setPessoaID(pessoa.getPessoaID());
            pessoa.setDocumentoPrincipal(documentoPrincipal);
        }
        documentoPrincipal.setTipoDocumentoPrincipal(documentoPrincipalCanonico.getTipoDocumentoPrincipal());
        documentoPrincipal.setNumeroDocumentoPrincipal(documentoPrincipalCanonico.getNumeroDocumentoPrincipal());
    }

    private void popularEndereco(Pessoa pessoa, EnderecoCanonico enderecoCanonico, Endereco enderecoResult) {
        Endereco endereco = pessoa.getEndereco();
        if (endereco == null) {
            endereco = new Endereco();
            endereco.setPessoaID(pessoa.getPessoaID());
            pessoa.setEndereco(endereco);
        }
        endereco.setTipoEndereco(enderecoCanonico.getTipoEndereco());
        endereco.setCep(enderecoCanonico.getCep());
        endereco.setLogradouro(enderecoResult.getLogradouro());
        endereco.setNumero(enderecoCanonico.getNumero());
        endereco.setComplemento(enderecoCanonico.getComplemento());
        endereco.setBairro(enderecoResult.getBairro());
        endereco.setLocalidade(enderecoResult.getLocalidade());
        endereco.setUf(enderecoResult.getUf());
    }

    private void popularTelefone(Pessoa pessoa, List<TelefoneCanonico> telefonesCanonicos) {
        List<Telefone> telefonesExistentes = new ArrayList<>(pessoa.getTelefones());

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
                novoTelefone.setPessoaID(pessoa.getPessoaID());
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
            pessoa.getTelefones().clear();
            idsTelefonesRemovidos.forEach(telefoneID -> {
                telefoneService.removeTelefone(telefoneID);
            });
        } else {
            // Atualiza a lista de telefones do usuário
            pessoa.setTelefones(telefonesExistentes);
        }
    }

    private void popularAcesso(Pessoa pessoa, AcessoCanonico acessoCanonico) {
        Acesso acesso = pessoa.getAcesso();
        if (acesso == null) {
            acesso = new Acesso();
            acesso.setPessoaID(pessoa.getPessoaID());
            pessoa.setAcesso(acesso);
        }
        acesso.setPerfilID(acessoCanonico.getPerfilID());
        acesso.setLogin(acessoCanonico.getLogin());
        String senhaCriptografada = passwordEncoder.encode(acessoCanonico.getSenha());
        acesso.setSenha(senhaCriptografada);
    }

    private Long salvaPessoa(PessoaCanonico canonico, Endereco enderecoResult) {
        Pessoa pessoa = geraPessoa(canonico);
        pessoa.setNome(canonico.getNome());
        pessoa.setEmail(canonico.getEmail());
        Pessoa pessoaSalvo = pessoaRepositorio.salvaPessoa(pessoa);
        salvaDocumentoPrincipal(canonico, pessoaSalvo);
        salvaEndereco(canonico, enderecoResult, pessoaSalvo);
        salvaTelefones(canonico, pessoaSalvo);
        salvaAcesso(canonico, pessoaSalvo);
        return pessoaSalvo.getPessoaID();
    }

    private void salvaDocumentoPrincipal(PessoaCanonico canonico, Pessoa pessoaSalvo) {
        DocumentoPrincipal documentoPrincipal = new DocumentoPrincipal();
        documentoPrincipal.setPessoaID(pessoaSalvo.getPessoaID());
        documentoPrincipal.setTipoDocumentoPrincipal(canonico.getDocumentoPrincipal().getTipoDocumentoPrincipal());
        documentoPrincipal.setNumeroDocumentoPrincipal(canonico.getDocumentoPrincipal().getNumeroDocumentoPrincipal());
        DocumentoPrincipal principal = documentoPrincipalRepositorio.merge(documentoPrincipal);
        salvaPessoaFisica(principal);
        salvaPessoaJuridica(principal);
    }

    private void salvaPessoaFisica(DocumentoPrincipal principal) {
        PessoaFisica pessoaFisica = new PessoaFisica();
        pessoaFisica.setDocumentoPrincipalID(principal.getDocumentoPrincipalID());
        pessoaFisica.setSobrenome(principal.getPessoaFisica().getSobrenome());
        pessoaFisica.setDataNascimento(principal.getPessoaFisica().getDataNascimento());
        pessoaFisica.setSexo(principal.getPessoaFisica().getSexo());
        pessoaFisica.setNacionalidade(principal.getPessoaFisica().getNacionalidade());
        pessoaFisica.setNaturalidade(principal.getPessoaFisica().getNaturalidade());
        pessoaFisicaRepositorio.merge(pessoaFisica);
    }

    private void salvaPessoaJuridica(DocumentoPrincipal principal) {
        PessoaJuridica pessoaJuridica = new PessoaJuridica();
        pessoaJuridica.setDocumentoPrincipalID(principal.getDocumentoPrincipalID());
        pessoaJuridica.setNomeComercia(principal.getPessoaJuridica().getNomeComercia());
        pessoaJuridica.setDataConstituicao(principal.getPessoaJuridica().getDataConstituicao());
        pessoaJuridica.setTipoInscricao(principal.getPessoaJuridica().getTipoInscricao());
        pessoaJuridica.setNumeroInscricao(principal.getPessoaJuridica().getNumeroInscricao());
        pessoaJuridica.setNaturazaJuridica(principal.getPessoaJuridica().getNaturazaJuridica());
        pessoaJuridica.setRamoAtividade(principal.getPessoaJuridica().getRamoAtividade());
        pessoaJuridica.setObjetivoSocial(principal.getPessoaJuridica().getObjetivoSocial());
        pessoaJuridicaRepositorio.merge(pessoaJuridica);
    }

    private void salvaEndereco(PessoaCanonico pessoa, Endereco enderecoResult, Pessoa pessoaSalvo) {
        Endereco endereco = new Endereco();
        endereco.setPessoaID(pessoaSalvo.getPessoaID());
        endereco.setTipoEndereco(pessoa.getEndereco().getTipoEndereco());
        endereco.setCep(enderecoResult.getCep());
        endereco.setLogradouro(enderecoResult.getLogradouro());
        endereco.setNumero(pessoa.getEndereco().getNumero());
        endereco.setComplemento(pessoa.getEndereco().getComplemento());
        endereco.setBairro(enderecoResult.getBairro());
        endereco.setLocalidade(enderecoResult.getLocalidade());
        endereco.setUf(enderecoResult.getUf());
        enderecoRepositorio.merge(endereco);
    }

    private void salvaTelefones(PessoaCanonico pessoa, Pessoa pessoaSalvo) {
        Optional.ofNullable(pessoa.getTelefones()).ifPresent(tel -> {
            persisteTelefones(pessoaSalvo, tel);
        });
    }

    private void persisteTelefones(Pessoa pessoaSalvo, List<TelefoneCanonico> tel) {
        List<Telefone> telefones = tel.stream().map(telefone -> montaTelefone(pessoaSalvo, telefone)).collect(Collectors.toList());
        pessoaSalvo.retemTelefone(telefones);
    }

    private Telefone montaTelefone(Pessoa pessoaSalvo, TelefoneCanonico telefone) {
        Telefone t = pessoaSalvo.novoTelefone(telefone.getTelefoneID());
        t.setCodigo(telefone.getCodigo());
        t.setTipoTelefone(telefone.getTipoTelefone());
        t.setNumeroTelefone(telefone.getNumeroTelefone());
        return t;
    }

    private void salvaAcesso(PessoaCanonico pessoa, Pessoa pessoaSalvo) {
        Acesso acesso = new Acesso();
        acesso.setPessoaID(pessoaSalvo.getPessoaID());
        acesso.setPerfilID(pessoa.getAcesso().getPerfilID());
        acesso.setLogin(pessoa.getAcesso().getLogin());
        String senhaCriptografada = passwordEncoder.encode(pessoa.getAcesso().getSenha());
        acesso.setSenha(senhaCriptografada);
        pessoaRepositorio.merge(acesso);
    }

    private Pessoa geraPessoa(PessoaCanonico canonico) {
        if (canonico.getPessoaID() == null) {
            return new Pessoa();
        }
        return pessoaRepositorio.busca(canonico.getPessoaID());
    }

    private void validaPessoa(Pessoa pessoa, PessoaCanonico pessoaCanonico, Endereco enderecoResult) {
        validaDadosPessoa(pessoaCanonico, enderecoResult);
    }

    private void validaDadosPessoa(PessoaCanonico pessoaCanonico, Endereco enderecoResult) {
        DocumentoPrincipalCanonico documentoCanonico = pessoaCanonico.getDocumentoPrincipal();
        EnderecoCanonico enderecoCanonico = pessoaCanonico.getEndereco();
        List<TelefoneCanonico> telefoneCanonico = pessoaCanonico.getTelefones();
        AcessoCanonico acessoCanonico = pessoaCanonico.getAcesso();


        if (Strings.isNullOrEmpty(pessoaCanonico.getNome())) {
            throw new ValidacaoException(MapInvestMensagens.ERRO_VALIDACAO_NOME_OBRIGATORIO.getValor());
        }

        if (Strings.isNullOrEmpty(pessoaCanonico.getEmail())) {
            throw new ValidacaoException(MapInvestMensagens.ERRO_VALIDACAO_EMAIL_OBRIGATORIO.getValor());
        }

        if (!EMAIL_REGEX.matcher(pessoaCanonico.getEmail()).matches()) {
            throw new ValidacaoException(MapInvestMensagens.ERRO_VALIDACAO_EMAIL_CARACTERES_INVALIDOS.getValor());
        }

        documentoPrincipalService.validaDadosDocumentoPrincipal(documentoCanonico);
        enderecoService.validaDadosEndereco(enderecoCanonico, enderecoResult);
        acessoService.validaDadosAcesso(acessoCanonico);

        for (TelefoneCanonico telefones : telefoneCanonico) {
            telefoneService.validaDadosTelefone(telefones);
        }
    }

    public void removePessoa(Long pessoaID) {
        Pessoa pessoa = buscarPessoa(pessoaID);
        pessoaRepositorio.remove(pessoa);
    }
}
