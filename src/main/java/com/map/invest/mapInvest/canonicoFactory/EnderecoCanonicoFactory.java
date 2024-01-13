package com.map.invest.mapInvest.canonicoFactory;

import com.map.invest.mapInvest.canonico.EnderecoCanonico;
import com.map.invest.mapInvest.entity.Endereco;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class EnderecoCanonicoFactory {

    public EnderecoCanonico builderEndereco(Endereco endereco) {
        return Optional.ofNullable(endereco).map(entidade -> {
            return EnderecoCanonico.builder()
                    .enderecoID(entidade.getEnderecoID())
                    .usuarioID(entidade.getUsuarioID())
                    .tipoEndereco(entidade.getTipoEndereco())
                    .cep(entidade.getCep())
                    .rua(entidade.getRua())
                    .numero(entidade.getNumero())
                    .complemento(entidade.getComplemento())
                    .cidade(entidade.getCidade())
                    .uf(entidade.getUf())
                    .build();
        }).orElse(null);
    }

    public List<EnderecoCanonico> enderecoCanonico(List<Endereco> resultList) {
        return Optional.ofNullable(resultList).map(lista -> {
            return lista.stream().map(el -> builderEndereco(el)).collect(Collectors.toList());
        }).orElse(new ArrayList<>());

    }
}
