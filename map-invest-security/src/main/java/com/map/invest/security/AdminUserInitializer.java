package com.map.invest.security;

import com.map.invest.entity.*;
import com.map.invest.repository.*;
import com.map.invest.util.constantes.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Component
public class AdminUserInitializer implements CommandLineRunner {

    private static final String LOGIN_USUARIO_ADMIN = "administrador";

    @Autowired
    private PessoaRepositorio pessoaRepositorio;

    @Autowired
    private DocumentoPrincipalRepositorio documentoPrincipalRepositorio;

    @Autowired
    private PessoaJuridicaRepositorio pessoaJuridicaRepositorio;

    @Autowired
    private EnderecoRepositorio enderecoRepositorio;

    @Autowired
    private TelefoneRepositorio telefoneRepositorio;

    @Autowired
    private AcessoRepositorio acessoRepositorio;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public void run(String... args) throws Exception {

        // Verifica se já existe um usuário admin
        Acesso buscaAcessoUsuarioAdmin = acessoRepositorio.buscarAcessoPorLogin(LOGIN_USUARIO_ADMIN);
        if (buscaAcessoUsuarioAdmin == null) {

            LocalDate dataAtual = LocalDate.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

            Pessoa usuario = new Pessoa();
            usuario.setNome("Administrador");
            usuario.setEmail("administrador@mapinvest.com.br");
            usuario.setAtivo(AtivoEnum.SIM);
            Pessoa usuarioSalvo = pessoaRepositorio.salvaPessoa(usuario);

            DocumentoPrincipal documentoPrincipal = new DocumentoPrincipal();
            documentoPrincipal.setPessoaID(usuarioSalvo.getPessoaID());
            documentoPrincipal.setTipoDocumentoPrincipal(TipoDocumentoEnum.CNPJ);
            documentoPrincipal.setNumeroDocumentoPrincipal("73263618000118");
            documentoPrincipal = documentoPrincipalRepositorio.merge(documentoPrincipal);

            PessoaJuridica pessoaJuridica = new PessoaJuridica();
            pessoaJuridica.setDocumentoPrincipalID(documentoPrincipal.getDocumentoPrincipalID());
            pessoaJuridica.setNomeComercia("Map Invest Serviços Financeiros");
            String dataConstituicaoString = "22-06-2024";
            LocalDate dataConstituicao = LocalDate.parse(dataConstituicaoString, formatter);
            pessoaJuridica.setDataConstituicao(dataConstituicao);
            pessoaJuridica.setTipoInscricao(TipoInscricaoEnum.SOCIEDADE_LTDA);
            pessoaJuridica.setNumeroInscricao("574436574523");
            pessoaJuridica.setNaturazaJuridica("Serviços");
            pessoaJuridica.setRamoAtividade("Financeiro");
            pessoaJuridica.setObjetivoSocial("Fornecimento de Serviços Financeiros");
            pessoaJuridicaRepositorio.merge(pessoaJuridica);

            Endereco endereco = new Endereco();
            endereco.setPessoaID(usuarioSalvo.getPessoaID());
            endereco.setTipoEndereco(TipoEnderecoEnum.COMERCIAL);
            endereco.setCep("05426200");
            endereco.setLogradouro("Avenida Brigadeiro Faria Lima");
            endereco.setNumero("1000");
            endereco.setComplemento("até 1016 - lado par");
            endereco.setBairro("Pinheiros");
            endereco.setLocalidade("São Paulo");
            endereco.setUf("UF");
            enderecoRepositorio.merge(endereco);

            // Criação e associação dos telefones
            Telefone telefone1 = new Telefone();
            telefone1.setCodigo("55");
            telefone1.setTipoTelefone(TipoTelefoneEnum.COMERCIAL);
            telefone1.setNumeroTelefone("1136034766");

            if (usuarioSalvo.getTelefones() == null) {
                usuarioSalvo.setTelefones(new ArrayList<>());
            }
            usuarioSalvo.getTelefones().add(telefone1);
            pessoaRepositorio.merge(usuarioSalvo);


            Acesso acesso = new Acesso();
            acesso.setPessoaID(usuarioSalvo.getPessoaID());
            acesso.setPerfilID(1L);
            acesso.setLogin("administrador");
            acesso.setSenha(passwordEncoder.encode("Adm1n1str4dor#"));
            acessoRepositorio.merge(acesso);

            System.out.println("Usuário administrador criado com sucesso.");
        } else {
            System.out.println("Usuário administrador já existe. Nenhuma ação necessária.");
            return;
        }
    }
}
