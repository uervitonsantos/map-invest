package com.map.invest.mapInvest.security;

import com.map.invest.mapInvest.entity.Acesso;
import com.map.invest.mapInvest.entity.Usuario;
import com.map.invest.mapInvest.repository.AcessoRepositorio;
import com.map.invest.mapInvest.repository.UsuarioRepositorio;
import com.map.invest.mapInvest.util.constantes.SexoEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class AdminUserInitializer implements CommandLineRunner {

    @Autowired
    private AcessoRepositorio acessoRepositorio;

    @Autowired
    private UsuarioRepositorio usuarioRepositorio;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        // Verifica se já existe um usuário admin
        Acesso buscaAcesso = acessoRepositorio.buscarAcessoPorLogin("administrador");
        if (buscaAcesso == null) {// Cria um novo usuário admin
            LocalDate dataAtual = LocalDate.now();
            Usuario usuario = new Usuario();
            usuario.setNome("Administrador");
            usuario.setSobreNome("Sistema MapInvest");
            usuario.setDataNascimento(dataAtual);
            usuario.setSexo(SexoEnum.valueOf("O"));
            usuario.setEmail("administrador@mapinvest.com.br");

            // Salva o usuário admin no banco de dados
            usuario = usuarioRepositorio.salvaUsuario(usuario);

            // Cria um novo acesso para o usuário admin
            Acesso acesso = new Acesso();
            acesso.setUsuarioID(usuario.getUsuarioID());
            acesso.setPerfilID(1L);
            acesso.setLogin("administrador");
            acesso.setSenha(passwordEncoder.encode("Adm1n1str4dor#")); // Criptografa a senha

            // Salva o acesso admin no banco de dados
            acessoRepositorio.merge(acesso);

            System.out.println("Usuário admin criado com sucesso.");
        } else {
            System.out.println("Usuário admin já existe. Nenhuma ação necessária.");
            return;
        }
    }
}
