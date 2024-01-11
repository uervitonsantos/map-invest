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

    public UsuarioDTO usuarioDto(UsuarioCanonico canonico) {
        if (canonico == null) {
            return null;
        }
        UsuarioDTO dto = new UsuarioDTO();
        dto.setUsuarioID(canonico.getUsuarioID());
        dto.setNome(canonico.getNome());
        dto.setSobreNome(canonico.getSobreNome());
        dto.setCpfcnpj(canonico.getCpfcnpj());
        dto.setEmail(canonico.getEmail());
        dto.setLogin(canonico.getLogin());
        dto.setPerfil(perfilDTOFactory.perfilDto(canonico.getPerfil()));
        return dto;
    }
    public List<UsuarioDTO> usuariosDto(List<UsuarioCanonico> resultList) {
        return Optional.ofNullable(resultList).map(lista -> {
            return lista.stream().map(el -> usuarioDto(el)).collect(Collectors.toList());
        }).orElse(new ArrayList<>());
    }

    public UsuarioCanonico usuarioCanonico(UsuarioDTO dto) {
        return Optional.ofNullable(dto).map(usuario -> {
            UsuarioCanonico canonico = new UsuarioCanonico();
            canonico.setUsuarioID(usuario.getUsuarioID());
            canonico.setNome(usuario.getNome());
            canonico.setSobreNome(usuario.getSobreNome());
            canonico.setCpfcnpj(usuario.getCpfcnpj());
            canonico.setEmail(usuario.getEmail());
            canonico.setLogin(usuario.getLogin());
            return canonico;
        }).orElse(null);
    }
}
