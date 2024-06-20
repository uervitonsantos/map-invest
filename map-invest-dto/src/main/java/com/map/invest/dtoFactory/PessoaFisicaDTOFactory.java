package com.map.invest.dtoFactory;

import com.map.invest.canonico.PessoaFisicaCanonico;
import com.map.invest.dto.PessoaFisicaDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class PessoaFisicaDTOFactory {

    @Autowired
    private DocumentosSecundariosDTOFactory documentosSecundariosDTOFactory;

    public PessoaFisicaDTO pessoaFisicaDto(PessoaFisicaCanonico fisica) {
        return Optional.ofNullable(fisica).map(canonico -> {
            return PessoaFisicaDTO.builder()
                    .pessoaFisicaID(canonico.getPessoaFisicaID())
                    .sobrenome(canonico.getSobrenome())
                    .dataNascimento(canonico.getDataNascimento())
                    .sexo(canonico.getSexo())
                    .nacionalidade(canonico.getNacionalidade())
                    .naturalidade(canonico.getNaturalidade())
                    .documentosSecundarios(documentosSecundariosDTOFactory.documentosSecundariosDto(canonico.getDocumentosSecundarios()))
                    .build();
        }).orElse(null);
    }

    public List<PessoaFisicaDTO> pessoasFisicaDto(List<PessoaFisicaCanonico> resultList) {
        return Optional.ofNullable(resultList).map(lista -> {
            return lista.stream().map(el -> pessoaFisicaDto(el)).collect(Collectors.toList());
        }).orElse(new ArrayList<>());
    }

    public PessoaFisicaCanonico pessoaFisicaCanonico(PessoaFisicaDTO dto) {
        return Optional.ofNullable(dto).map(canonico -> {
            return PessoaFisicaCanonico.builder()
                    .pessoaFisicaID(canonico.getPessoaFisicaID())
                    .sobrenome(canonico.getSobrenome())
                    .dataNascimento(canonico.getDataNascimento())
                    .sexo(canonico.getSexo())
                    .nacionalidade(canonico.getNacionalidade())
                    .naturalidade(canonico.getNaturalidade())
                    .documentosSecundarios(documentosSecundariosDTOFactory.documentosSecundariosCanonico(canonico.getDocumentosSecundarios()))
                    .build();
        }).orElse(null);
    }
}
