package com.map.invest.mapInvest.dtoFactory;

import com.map.invest.mapInvest.canonico.EnderecoCanonico;
import com.map.invest.mapInvest.dto.EnderecoDTO;
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
                    .tipoEndereco(canonico.getTipoEndereco())
                    .cep(canonico.getCep())
                    .logradouro(canonico.getLogradouro())
                    .numero(canonico.getNumero())
                    .complemento(canonico.getComplemento())
                    .bairro(canonico.getBairro())
                    .localidade(canonico.getLocalidade())
                    .uf(canonico.getUf())
                    .build();
        }).orElse(null);
    }

    public List<EnderecoDTO> enderecosDTO(List<EnderecoCanonico> resultList) {
        return Optional.ofNullable(resultList).map(lista -> {
            return lista.stream().map(el -> enderecoDto(el)).collect(Collectors.toList());
        }).orElse(new ArrayList<>());
    }

    public EnderecoCanonico enderecoCanonico(EnderecoDTO dto) {
        return Optional.ofNullable(dto).map(entidade -> {
            return EnderecoCanonico.builder()
                    .enderecoID(entidade.getEnderecoID())
                    .tipoEndereco(entidade.getTipoEndereco())
                    .cep(entidade.getCep())
                    .numero(entidade.getNumero())
                    .complemento(entidade.getComplemento())
                    .build();
        }).orElse(null);
    }

    public List<EnderecoCanonico> enderecosCanonico(List<EnderecoDTO> resultList) {
        return Optional.ofNullable(resultList).map(lista -> {
            return lista.stream().map(el -> enderecoCanonico(el)).collect(Collectors.toList());
        }).orElse(new ArrayList<>());
    }
}
