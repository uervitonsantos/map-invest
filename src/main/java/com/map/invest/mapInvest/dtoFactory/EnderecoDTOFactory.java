package com.map.invest.mapInvest.dtoFactory;

import com.map.invest.mapInvest.canonico.EnderecoCanonico;
import com.map.invest.mapInvest.canonico.TelefoneCanonico;
import com.map.invest.mapInvest.dto.EnderecoDTO;
import com.map.invest.mapInvest.dto.TelefoneDTO;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class EnderecoDTOFactory {

    public EnderecoDTO enderecoDto(EnderecoCanonico endereco) {
        return Optional.ofNullable(endereco).map(canonico -> {
            return EnderecoDTO.builder()
                    .enderecoID(canonico.getEnderecoID())
                    .usuarioID(canonico.getUsuarioID())
                    .tipoEndereco(canonico.getTipoEndereco())
                    .cep(canonico.getCep())
                    .rua(canonico.getRua())
                    .numero(canonico.getNumero())
                    .complemento(canonico.getComplemento())
                    .cidade(canonico.getCidade())
                    .uf(canonico.getUf())
                    .build();
        }).orElse(null);
    }

    public List<EnderecoDTO> enderecosDTO(List<EnderecoCanonico> resultList) {
        return Optional.ofNullable(resultList).map(lista -> {
            return lista.stream().map(el -> enderecoDto(el)).collect(Collectors.toList());
        }).orElse(new ArrayList<>());
    }
}
