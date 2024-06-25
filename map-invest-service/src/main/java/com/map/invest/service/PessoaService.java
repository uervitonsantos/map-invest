package com.map.invest.service;

import com.google.common.base.Strings;
import com.map.invest.canonico.*;
import com.map.invest.entity.*;
import com.map.invest.filtro.FiltroWrapper;
import com.map.invest.repository.*;
import com.map.invest.util.constantes.AtivoEnum;
import com.map.invest.util.constantes.TipoDocumentoEnum;
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
    private DocumentosSecundariosService documentosSecundariosService;
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
        pessoa.setAtivo(pessoaCanonico.getAtivo());
        popularDocumentoPrincipal(pessoa, pessoaCanonico.getDocumentoPrincipal());
        popularEndereco(pessoa, pessoaCanonico.getEndereco(), enderecoResult);
        telefoneService.popularTelefone(pessoa, pessoaCanonico.getTelefones());
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

        TipoDocumentoEnum tipo = documentoPrincipalCanonico.getTipoDocumentoPrincipal();
        if (tipo.equals(TipoDocumentoEnum.CPF)) {
            popularPessoaFisica(documentoPrincipal, documentoPrincipalCanonico.getPessoaFisica());
        }
        if (tipo.equals(TipoDocumentoEnum.CNPJ)) {
            popularPessoaJuridica(documentoPrincipal, documentoPrincipalCanonico.getPessoaJuridica());
        }
    }

    private void popularPessoaFisica(DocumentoPrincipal documentoPrincipal, PessoaFisicaCanonico pessoaFisicaCanonico) {
        PessoaFisica pessoaFisica = documentoPrincipal.getPessoaFisica();
        if (pessoaFisica == null) {
            pessoaFisica = new PessoaFisica();
            pessoaFisica.setDocumentoPrincipalID(documentoPrincipal.getDocumentoPrincipalID());
            documentoPrincipal.setPessoaFisica(pessoaFisica);
        }
        pessoaFisica.setSobrenome(pessoaFisicaCanonico.getSobrenome());
        pessoaFisica.setDataNascimento(pessoaFisicaCanonico.getDataNascimento());
        pessoaFisica.setSexo(pessoaFisicaCanonico.getSexo());
        pessoaFisica.setNacionalidade(pessoaFisicaCanonico.getNacionalidade());
        pessoaFisica.setNaturalidade(pessoaFisicaCanonico.getNaturalidade());
        //documentosSecundariosService.popularDocumentosSecundarios(pessoaFisica, pessoaFisicaCanonico.getDocumentosSecundarios());
    }


    private void popularPessoaJuridica(DocumentoPrincipal documentoPrincipal, PessoaJuridicaCanonico pessoaJuridicaCanonico) {
        PessoaJuridica pessoaJuridica = documentoPrincipal.getPessoaJuridica();
        if (pessoaJuridica == null) {
            pessoaJuridica = new PessoaJuridica();
            pessoaJuridica.setDocumentoPrincipalID(documentoPrincipal.getDocumentoPrincipalID());
            documentoPrincipal.setPessoaJuridica(pessoaJuridica);
        }
        pessoaJuridica.setNomeComercia(pessoaJuridicaCanonico.getNomeComercia());
        pessoaJuridica.setDataConstituicao(pessoaJuridicaCanonico.getDataConstituicao());
        pessoaJuridica.setTipoInscricao(pessoaJuridicaCanonico.getTipoInscricao());
        pessoaJuridica.setNumeroInscricao(pessoaJuridicaCanonico.getNumeroInscricao());
        pessoaJuridica.setNaturazaJuridica(pessoaJuridicaCanonico.getNaturazaJuridica());
        pessoaJuridica.setRamoAtividade(pessoaJuridicaCanonico.getRamoAtividade());
        pessoaJuridica.setObjetivoSocial(pessoaJuridicaCanonico.getObjetivoSocial());
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
        pessoa.setAtivo(AtivoEnum.NAO);
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
        TipoDocumentoEnum tipo = canonico.getDocumentoPrincipal().getTipoDocumentoPrincipal();
        if (tipo.equals(TipoDocumentoEnum.CPF)) {
            salvaPessoaFisica(canonico, principal);
        }
        if (tipo.equals(TipoDocumentoEnum.CNPJ)) {
            salvaPessoaJuridica(canonico, principal);
        }
    }

    private void salvaPessoaFisica(PessoaCanonico canonico, DocumentoPrincipal principal) {
        PessoaFisica pessoaFisica = new PessoaFisica();
        pessoaFisica.setDocumentoPrincipalID(principal.getDocumentoPrincipalID());
        pessoaFisica.setSobrenome(canonico.getDocumentoPrincipal().getPessoaFisica().getSobrenome());
        pessoaFisica.setDataNascimento(canonico.getDocumentoPrincipal().getPessoaFisica().getDataNascimento());
        pessoaFisica.setSexo(canonico.getDocumentoPrincipal().getPessoaFisica().getSexo());
        pessoaFisica.setNacionalidade(canonico.getDocumentoPrincipal().getPessoaFisica().getNacionalidade());
        pessoaFisica.setNaturalidade(canonico.getDocumentoPrincipal().getPessoaFisica().getNaturalidade());
        pessoaFisicaRepositorio.merge(pessoaFisica);
    }

    private void salvaPessoaJuridica(PessoaCanonico canonico, DocumentoPrincipal principal) {
        PessoaJuridica pessoaJuridica = new PessoaJuridica();
        pessoaJuridica.setDocumentoPrincipalID(principal.getDocumentoPrincipalID());
        pessoaJuridica.setNomeComercia(canonico.getDocumentoPrincipal().getPessoaJuridica().getNomeComercia());
        pessoaJuridica.setDataConstituicao(canonico.getDocumentoPrincipal().getPessoaJuridica().getDataConstituicao());
        pessoaJuridica.setTipoInscricao(canonico.getDocumentoPrincipal().getPessoaJuridica().getTipoInscricao());
        pessoaJuridica.setNumeroInscricao(canonico.getDocumentoPrincipal().getPessoaJuridica().getNumeroInscricao());
        pessoaJuridica.setNaturazaJuridica(canonico.getDocumentoPrincipal().getPessoaJuridica().getNaturazaJuridica());
        pessoaJuridica.setRamoAtividade(canonico.getDocumentoPrincipal().getPessoaJuridica().getRamoAtividade());
        pessoaJuridica.setObjetivoSocial(canonico.getDocumentoPrincipal().getPessoaJuridica().getObjetivoSocial());
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

        if (pessoaCanonico.getNome().length() > 50) {
            throw new ValidacaoException(MapInvestMensagens.ERRO_VALIDACAO_NOME_COMPRIMENTO_MAXIMO.getValor());
        }

        if (Strings.isNullOrEmpty(pessoaCanonico.getEmail())) {
            throw new ValidacaoException(MapInvestMensagens.ERRO_VALIDACAO_EMAIL_OBRIGATORIO.getValor());
        }

        if (pessoaCanonico.getEmail().length() > 50) {
            throw new ValidacaoException(MapInvestMensagens.ERRO_VALIDACAO_EMAIL_COMPRIMENTO_MAXIMO.getValor());
        }

        if (!EMAIL_REGEX.matcher(pessoaCanonico.getEmail()).matches()) {
            throw new ValidacaoException(MapInvestMensagens.ERRO_VALIDACAO_EMAIL_CARACTERES_INVALIDOS.getValor());
        }

        AtivoEnum tipo = pessoaCanonico.getAtivo();

        if (tipo == null) {
            throw new ValidacaoException(MapInvestMensagens.ERRO_VALIDACAO_ATIVO_OBRIGATORIO.getValor());
        } else{
            if(!tipo.equals(AtivoEnum.NAO) && !tipo.equals(AtivoEnum.SIM)){
                throw new ValidacaoException(MapInvestMensagens.ERRO_VALIDACAO_ATIVO_OBRIGATORIO.getValor());
            }
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
