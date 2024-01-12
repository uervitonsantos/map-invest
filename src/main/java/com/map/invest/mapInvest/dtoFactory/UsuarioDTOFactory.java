package com.map.invest.mapInvest.dtoFactory;

import com.map.invest.mapInvest.canonico.UsuarioCanonico;
import com.map.invest.mapInvest.dto.UsuarioDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class UsuarioDTOFactory {

    @Autowired
    private PerfilDTOFactory perfilDTOFactory;

    public UsuarioDTO usuarioDto(UsuarioCanonico usuario) {
        return Optional.ofNullable(usuario).map(canonico -> {
            return UsuarioDTO.builder()
                    .usuarioID(canonico.getUsuarioID())
                    .nome(canonico.getNome())
                    .sobreNome(canonico.getSobreNome())
                    .cpfcnpj(canonico.getCpfcnpj())
                    .email(canonico.getEmail())
                    .login(canonico.getLogin())
                    .perfil(perfilDTOFactory.perfilDto(canonico.getPerfil()))
                    .build();
        }).orElse(null);
    }

    public List<UsuarioDTO> usuariosDto(List<UsuarioCanonico> resultList) {
        return Optional.ofNullable(resultList).map(lista -> {
            return lista.stream().map(el -> usuarioDto(el)).collect(Collectors.toList());
        }).orElse(new ArrayList<>());
    }

    public UsuarioCanonico usuarioCanonico(UsuarioDTO dto) {
        return Optional.ofNullable(dto).map(entidade -> {
            return UsuarioCanonico.builder()
                    .usuarioID(entidade.getUsuarioID())
                    .nome(entidade.getNome())
                    .sobreNome(entidade.getSobreNome())
                    .cpfcnpj(entidade.getCpfcnpj())
                    .email(entidade.getEmail())
                    .login(entidade.getLogin())
                    .build();
        }).orElse(null);
    }
}
