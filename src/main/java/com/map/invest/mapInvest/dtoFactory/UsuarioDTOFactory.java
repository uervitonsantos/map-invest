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
                    .nome(canonico.getNome())
                    .sobreNome(canonico.getSobreNome())
                    .dataNascimento(canonico.getDataNascimento())
                    .sexo(canonico.getSexo())
                    .email(canonico.getEmail())
                    .documento(documentoDTOFactory.documentoDto(canonico.getDocumento()))
                    .endereco(enderecoDTOFactory.enderecoDto(canonico.getEndereco()))
                    .telefones(telefoneDTOFactory.telefonesDTO(canonico.getTelefones()))
                    .acesso(acessoDTOFactory.acessoDto(canonico.getAcesso()))
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
                    .dataNascimento(entidade.getDataNascimento())
                    .sexo(entidade.getSexo())
                    .email(entidade.getEmail())
                    .documento(documentoDTOFactory.documentoCanonico(entidade.getDocumento()))
                    .endereco(enderecoDTOFactory.enderecoCanonico(entidade.getEndereco()))
                    .telefones(telefoneDTOFactory.telefonesCanonico(entidade.getTelefones()))
                    .acesso(acessoDTOFactory.acessoCanonico(entidade.getAcesso()))
                    .build();
        }).orElse(null);
    }
}
