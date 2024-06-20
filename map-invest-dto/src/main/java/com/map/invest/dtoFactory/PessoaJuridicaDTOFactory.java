package com.map.invest.dtoFactory;

import com.map.invest.canonico.PessoaJuridicaCanonico;
import com.map.invest.dto.PessoaJuridicaDTO;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class PessoaJuridicaDTOFactory {

    public PessoaJuridicaDTO pessoaJuridicaDto(PessoaJuridicaCanonico juridica) {
        return Optional.ofNullable(juridica).map(canonico -> {
            return PessoaJuridicaDTO.builder()
                    .pessoaJuridicaID(canonico.getPessoaJuridicaID())
                    .nomeComercia(canonico.getNomeComercia())
                    .dataConstituicao(canonico.getDataConstituicao())
                    .tipoInscricao(canonico.getTipoInscricao())
                    .numeroInscricao(canonico.getNumeroInscricao())
                    .naturazaJuridica(canonico.getNaturazaJuridica())
                    .ramoAtividade(canonico.getRamoAtividade())
                    .objetivoSocial(canonico.getObjetivoSocial())
                    .build();
        }).orElse(null);
    }

    public List<PessoaJuridicaDTO> pessoasJuridicasDto(List<PessoaJuridicaCanonico> resultList) {
        return Optional.ofNullable(resultList).map(lista -> {
            return lista.stream().map(el -> pessoaJuridicaDto(el)).collect(Collectors.toList());
        }).orElse(new ArrayList<>());
    }

    public PessoaJuridicaCanonico pessoaJuridicaCanonica(PessoaJuridicaDTO dto) {
        return Optional.ofNullable(dto).map(canonico -> {
            return PessoaJuridicaCanonico.builder()
                    .pessoaJuridicaID(canonico.getPessoaJuridicaID())
                    .nomeComercia(canonico.getNomeComercia())
                    .dataConstituicao(canonico.getDataConstituicao())
                    .tipoInscricao(canonico.getTipoInscricao())
                    .numeroInscricao(canonico.getNumeroInscricao())
                    .naturazaJuridica(canonico.getNaturazaJuridica())
                    .ramoAtividade(canonico.getRamoAtividade())
                    .objetivoSocial(canonico.getObjetivoSocial())
                    .build();
        }).orElse(null);
    }

}
