package com.map.invest.mapInvest.usuarioServiceTest;

import com.map.invest.mapInvest.canonico.UsuarioCanonico;
import com.map.invest.mapInvest.canonicoFactory.UsuarioCanonicoFactory;
import com.map.invest.mapInvest.entity.*;
import com.map.invest.mapInvest.repository.PerfilRepositorio;
import com.map.invest.mapInvest.repository.UsuarioRepositorio;
import com.map.invest.mapInvest.service.UsuarioService;
import com.map.invest.mapInvest.util.constantes.TipoDocumentoEnum;
import com.map.invest.mapInvest.util.constantes.TipoEnderecoEnum;
import com.map.invest.mapInvest.util.constantes.TipoTelefoneEnum;
import com.map.invest.mapInvest.util.validacao.exception.ValidacaoException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UsuarioServiceTest {
    @Mock
    private UsuarioRepositorio usuarioRepositorio;

    @Mock
    private PerfilRepositorio perfilRepositorio;

    @InjectMocks
    private UsuarioService usuarioService;

    @InjectMocks
    private UsuarioCanonicoFactory usuarioCanonicoFactory;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    @DisplayName("Deve retornar sucesso ao salvar novo usuário")
    public void deveRetornarSucessoAoSalvarNovoUsuario() {
        // Arrange
        Usuario usuario = criarUsuarioTeste();

        // Configurar o comportamento do mock do repositório
        when(usuarioRepositorio.salvaUsuario(any(Usuario.class)))
                .thenAnswer(invocation -> {
                    Usuario user = invocation.getArgument(0);
                    user.setUsuarioID(99L); // Definindo um UsuarioID fictício
                    return user;
                });

        // Act
        UsuarioCanonico usuarioCanonico = usuarioCanonicoFactory.builderUsuario(usuario);
        UsuarioCanonico usuarioCriado = usuarioService.criaUsuario(usuarioCanonico);

        // Assert
        assertNotNull(usuarioCriado);
        assertEquals(usuarioCanonico.getPerfilID(), usuarioCriado.getPerfilID());
        assertEquals(usuarioCanonico.getNome(), usuarioCriado.getNome());
        assertEquals(usuarioCanonico.getSobreNome(), usuarioCriado.getSobreNome());
        assertEquals(usuarioCanonico.getEmail(), usuarioCriado.getEmail());
    }

    @Test
    @DisplayName("Deve lançar exceção ao tentar criar usuário com perfil inexistente")
    public void deveLancarExcecaoAoCriarUsuarioComPerfilInexistente() {
        // Arrange
        UsuarioCanonico usuarioCanonico = criarUsuarioCanonicoTeste();
        usuarioCanonico.setPerfilID(99L); // Perfil inexistente

        // Simular o comportamento do repositório
        when(perfilRepositorio.buscaPerfil(anyLong())).thenReturn(null);

        // Act & Assert
        assertThrows(ValidacaoException.class, () -> usuarioService.criaUsuario(usuarioCanonico));
    }

    // Métodos auxiliares

    private Usuario criarUsuarioTeste() {
        Usuario usuario = new Usuario();
        usuario.setPerfilID(2L);
        usuario.setNome("Gilberto");
        usuario.setSobreNome("Ferreira");
        usuario.setEmail("gilberto@exemplo.com");
        usuario.setDocumento(criarDocumentoTeste());
        usuario.setTelefones(criarListaTelefonesTeste());
        usuario.setEndereco(criarEnderecoTeste());
        usuario.setAcesso(criarAcessoTeste());
        return usuario;
    }

    private Documento criarDocumentoTeste() {
        Documento documento = new Documento();
        documento.setUsuarioID(99L);
        documento.setTipoDocumento(TipoDocumentoEnum.CPF);
        documento.setNumeroDocumento("123456789");
        return documento;
    }

    private List<Telefone> criarListaTelefonesTeste() {
        List<Telefone> telefones = new ArrayList<>();
        Telefone telefone = new Telefone();
        telefone.setUsuarioID(99L);
        telefone.setCodigo("55");
        telefone.setTipoTelefone(TipoTelefoneEnum.COMERCIAL);
        telefone.setNumeroTelefone("1134567897");
        telefones.add(telefone);
        return telefones;
    }

    private Endereco criarEnderecoTeste() {
        Endereco endereco = new Endereco();
        endereco.setUsuarioID(99L);
        endereco.setTipoEndereco(TipoEnderecoEnum.RESIDENCIAL);
        endereco.setCep("06266-500");
        endereco.setRua("Rua Exemplo");
        endereco.setNumero("123");
        endereco.setComplemento("Rua sem saída");
        endereco.setUf("SP");
        endereco.setCidade("Cidade Exemplo");
        return endereco;
    }

    private Acesso criarAcessoTeste() {
        Acesso acesso = new Acesso();
        acesso.setUsuarioID(99L);
        acesso.setLogin("login");
        acesso.setSenha("senha");
        return acesso;
    }

    private UsuarioCanonico criarUsuarioCanonicoTeste() {
        UsuarioCanonico usuarioCanonico = new UsuarioCanonico();
        usuarioCanonico.setPerfilID(2L);
        usuarioCanonico.setNome("Gilberto");
        usuarioCanonico.setSobreNome("Ferreira");
        usuarioCanonico.setEmail("gilberto@exemplo.com");
        criarDocumentoTeste();
        criarEnderecoTeste();
        criarListaTelefonesTeste();
        criarAcessoTeste();
        return usuarioCanonico;
    }
}
