package com.map.invest.mapInvest.canonicoFactory;

import com.map.invest.mapInvest.canonico.UsuarioCanonico;
import com.map.invest.mapInvest.entity.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class UsuarioCanonicoFactory {

    @Autowired
    private PerfilCanonicoFactory perfilCanonicoFactory;

    @Autowired
    private TelefoneCanonicoFactory telefoneCanonicoFactory;

    @Autowired
    private AcessoCanonicoFactory acessoCanonicoFactory;

    @Autowired
    private DocumentoCanonicoFactory documentoCanonicoFactory;

    @Autowired
    private EnderecoCanonicoFactory enderecoCanonicoFactory;

    public UsuarioCanonico builderUsuario(Usuario usuario) {
        return Optional.ofNullable(usuario).map(entidade -> {
            return UsuarioCanonico.builder()
                    .usuarioID(entidade.getUsuarioID())
                    .perfilID(entidade.getPerfilID())
                    .nome(entidade.getNome())
                    .sobreNome(entidade.getSobreNome())
                    .email(entidade.getEmail())
                    .documento(documentoCanonicoFactory.builderDocumento(entidade.getDocumento()))
                    .endereco(enderecoCanonicoFactory.builderEndereco(entidade.getEndereco()))
                    .telefones(telefoneCanonicoFactory.telefoneCanonico(entidade.getTelefones()))
                    .acesso(acessoCanonicoFactory.builderAcesso(entidade.getAcesso()))
                    .perfil(perfilCanonicoFactory.builderPerfil(entidade.getPerfil()))
                    .build();
        }).orElse(null);
    }

    public List<UsuarioCanonico> usuariosCanonico(List<Usuario> resultList) {
        return Optional.ofNullable(resultList).map(lista -> {
            return lista.stream().map(el -> builderUsuario(el)).collect(Collectors.toList());
        }).orElse(new ArrayList<>());

    }
}

