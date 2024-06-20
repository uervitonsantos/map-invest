package com.map.invest.dtoFactory;

import com.map.invest.canonico.DocumentoPrincipalCanonico;
import com.map.invest.dto.DocumentoPrincipalDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class DocumentoPrincipalDTOFactory {

    @Autowired
    private PessoaFisicaDTOFactory pessoaFisicaDTOFactory;

    @Autowired
    private PessoaJuridicaDTOFactory pessoaJuridicaDTOFactory;

    public DocumentoPrincipalDTO documentoDto(DocumentoPrincipalCanonico documento) {
        return Optional.ofNullable(documento).map(canonico -> {
            return DocumentoPrincipalDTO.builder()
                    .documentoPrincipalID(canonico.getDocumentoPrincipalID())
                    .tipoDocumentoPrincipal(canonico.getTipoDocumentoPrincipal())
                    .numeroDocumentoPrincipal(canonico.getNumeroDocumentoPrincipal())
                    .pessoaFisica(pessoaFisicaDTOFactory.pessoaFisicaDto(canonico.getPessoaFisica()))
                    .pessoaJuridica(pessoaJuridicaDTOFactory.pessoaJuridicaDto(canonico.getPessoaJuridica()))
                    .build();
        }).orElse(null);
    }

    public List<DocumentoPrincipalDTO> documentosDto(List<DocumentoPrincipalCanonico> resultList) {
        return Optional.ofNullable(resultList).map(lista -> {
            return lista.stream().map(el -> documentoDto(el)).collect(Collectors.toList());
        }).orElse(new ArrayList<>());
    }

    public DocumentoPrincipalCanonico documentoCanonico(DocumentoPrincipalDTO dto) {
        return Optional.ofNullable(dto).map(entidade -> {
            return DocumentoPrincipalCanonico.builder()
                    .documentoPrincipalID(entidade.getDocumentoPrincipalID())
                    .tipoDocumentoPrincipal(entidade.getTipoDocumentoPrincipal())
                    .numeroDocumentoPrincipal(entidade.getNumeroDocumentoPrincipal())
                    .pessoaFisica(pessoaFisicaDTOFactory.pessoaFisicaCanonico(entidade.getPessoaFisica()))
                    .pessoaJuridica(pessoaJuridicaDTOFactory.pessoaJuridicaCanonica(entidade.getPessoaJuridica()))
                    .build();
        }).orElse(null);
    }
}
