package com.map.invest.mapInvest.dtoFactory;

import com.map.invest.mapInvest.canonico.UsuarioCanonico;
import com.map.invest.mapInvest.dto.*;
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

    @Autowired
    private TelefoneDTOFactory telefoneDTOFactory;
    @Autowired
    private AcessoDTOFactory acessoDTOFactory;
    @Autowired
    private DocumentoDTOFactory documentoDTOFactory;
    @Autowired
    private EnderecoDTOFactory enderecoDTOFactory;

    public UsuarioDTO usuarioDto(UsuarioCanonico usuario) {
        return Optional.ofNullable(usuario).map(canonico -> {
            return UsuarioDTO.builder()
                    .usuarioID(canonico.getUsuarioID())
                    .perfilID(canonico.getPerfilID())
                    .nome(canonico.getNome())
                    .sobreNome(canonico.getSobreNome())
                    .email(canonico.getEmail())
                    .documento(documentoDTOFactory.documentoDto(canonico.getDocumento()))
                    .endereco(enderecoDTOFactory.enderecoDto(canonico.getEndereco()))
                    .telefones(telefoneDTOFactory.telefonesDTO(canonico.getTelefones()))
                    .acesso(acessoDTOFactory.acessoDto(canonico.getAcesso()))
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
                    .perfilID(entidade.getPerfilID())
                    .nome(entidade.getNome())
                    .sobreNome(entidade.getSobreNome())
                    .email(entidade.getEmail())
                    .documento(documentoDTOFactory.documentoCanonico(entidade.getDocumento()))
                    .endereco(enderecoDTOFactory.enderecoCanonico(entidade.getEndereco()))
                    .telefones(telefoneDTOFactory.telefonesCanonico(entidade.getTelefones()))
                    .acesso(acessoDTOFactory.acessoCanonico(entidade.getAcesso()))
                    .perfil(perfilDTOFactory.perfilCanonico(entidade.getPerfil()))
                    .build();
        }).orElse(null);
    }
}
