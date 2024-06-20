package com.map.invest.security;

import com.map.invest.entity.*;
import com.map.invest.repository.*;
import com.map.invest.util.constantes.TipoDocumentoEnum;
import com.map.invest.util.constantes.TipoEnderecoEnum;
import com.map.invest.util.constantes.TipoTelefoneEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
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
    private EnderecoRepositorio enderecoRepositorio;

    @Autowired
    private TelefoneRepositorio telefoneRepositorio;

    @Autowired
    private AcessoRepositorio acessoRepositorio;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {

        // Verifica se já existe um usuário admin
        Acesso buscaAcessoUsuarioAdmin = acessoRepositorio.buscarAcessoPorLogin(LOGIN_USUARIO_ADMIN);
        if (buscaAcessoUsuarioAdmin == null) {

            LocalDate dataAtual = LocalDate.now();
            Pessoa usuario = new Pessoa();
            usuario.setNome("Administrador");
            usuario.setEmail("administrador@mapinvest.com.br");
            usuario.setAtivo("1");
            usuario = pessoaRepositorio.salvaPessoa(usuario);

            DocumentoPrincipal documentoPrincipal = new DocumentoPrincipal();
            documentoPrincipal.setPessoaID(usuario.getPessoaID());
            documentoPrincipal.setTipoDocumentoPrincipal(TipoDocumentoEnum.valueOf("CNPJ"));
            documentoPrincipal.setNumeroDocumentoPrincipal("73263618000118");
            documentoPrincipalRepositorio.merge(documentoPrincipal);

            Endereco endereco = new Endereco();
            endereco.setPessoaID(usuario.getPessoaID());
            endereco.setTipoEndereco(TipoEnderecoEnum.valueOf("COMERCIAL"));
            endereco.setCep("05426200");
            endereco.setLogradouro("Avenida Brigadeiro Faria Lima");
            endereco.setNumero("1000");
            endereco.setComplemento("até 1016 - lado par");
            endereco.setBairro("Pinheiros");
            endereco.setLocalidade("São Paulo");
            endereco.setUf("UF");
            enderecoRepositorio.merge(endereco);

            Telefone telefone1 = new Telefone();
            telefone1.setPessoaID(usuario.getPessoaID());
            telefone1.setCodigo("55");
            telefone1.setTipoTelefone(TipoTelefoneEnum.valueOf("COMERCIAL"));
            telefone1.setNumeroTelefone("1136034766");

            List<Telefone> telefones = new ArrayList<>();
            telefones.add(telefone1);
            usuario.setTelefones(telefones);
            pessoaRepositorio.merge(usuario);

            Acesso acesso = new Acesso();
            acesso.setPessoaID(usuario.getPessoaID());
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
