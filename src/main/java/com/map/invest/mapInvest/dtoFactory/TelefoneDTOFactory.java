package com.map.invest.mapInvest.dtoFactory;

import com.map.invest.mapInvest.canonico.TelefoneCanonico;
import com.map.invest.mapInvest.dto.TelefoneDTO;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class TelefoneDTOFactory {
    public TelefoneDTO telefoneDto(TelefoneCanonico telefone) {
        return Optional.ofNullable(telefone).map(canonico -> {
            return TelefoneDTO.builder()
                    .telefoneID(canonico.getTelefoneID())
                    .codigo(canonico.getCodigo())
                    .tipoTelefone(canonico.getTipoTelefone())
                    .numeroTelefone(canonico.getNumeroTelefone())
                    .build();
        }).orElse(null);
    }

    public List<TelefoneDTO> telefonesDTO(List<TelefoneCanonico> resultList) {
        return Optional.ofNullable(resultList).map(lista -> {
            return lista.stream().map(el -> telefoneDto(el)).collect(Collectors.toList());
        }).orElse(new ArrayList<>());
    }

    public TelefoneCanonico telefoneCanonico(TelefoneDTO dto) {
        return Optional.ofNullable(dto).map(entidade -> {
            return TelefoneCanonico.builder()
                    .telefoneID(entidade.getTelefoneID())
                    .codigo(entidade.getCodigo())
                    .tipoTelefone(entidade.getTipoTelefone())
                    .numeroTelefone(entidade.getNumeroTelefone())
                    .build();
        }).orElse(null);
    }

    public List<TelefoneCanonico> telefonesCanonico(List<TelefoneDTO> resultList) {
        return Optional.ofNullable(resultList).map(lista -> {
            return lista.stream().map(el -> telefoneCanonico(el)).collect(Collectors.toList());
        }).orElse(new ArrayList<>());
    }
}
