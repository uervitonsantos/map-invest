package com.map.invest.mapInvest.canonicoFactory;

import com.map.invest.mapInvest.canonico.UsuarioCanonico;
import com.map.invest.mapInvest.canonicoBuilder.UsuarioCanonicoBuilder;
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

    public UsuarioCanonico builderUsuario(Usuario usuario) {
        return Optional.ofNullable(usuario).map(entidade -> {
            return UsuarioCanonicoBuilder.newInstance()
                    .usuarioID(entidade.getUsuarioID())
                    .perfilID(entidade.getPerfilID())
                    .nome(entidade.getNome())
                    .sobreNome(entidade.getSobreNome())
                    .cpfcnpj(entidade.getCpfcnpj())
                    .email(entidade.getEmail())
                    .login(entidade.getLogin())
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

