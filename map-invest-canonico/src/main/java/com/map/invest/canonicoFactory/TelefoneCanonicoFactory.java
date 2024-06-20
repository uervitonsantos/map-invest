package com.map.invest.canonicoFactory;

import com.map.invest.canonico.TelefoneCanonico;
import com.map.invest.entity.Telefone;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class TelefoneCanonicoFactory {
    public TelefoneCanonico builderTelefone(Telefone telefone) {
        return Optional.ofNullable(telefone).map(entidade -> {
            return TelefoneCanonico.builder()
                    .telefoneID(entidade.getTelefoneID())
                    .pessoaID(entidade.getPessoaID())
                    .codigo(entidade.getCodigo())
                    .tipoTelefone(entidade.getTipoTelefone())
                    .numeroTelefone(entidade.getNumeroTelefone())
                    .build();
        }).orElse(null);
    }

    public List<TelefoneCanonico> telefoneCanonico(List<Telefone> resultList) {
        return Optional.ofNullable(resultList).map(lista -> {
            return lista.stream().map(el -> builderTelefone(el)).collect(Collectors.toList());
        }).orElse(new ArrayList<>());
    }
}
