package com.map.invest.mapInvest.canonicoFactory;

import com.map.invest.mapInvest.canonico.PessoaJuridicaCanonico;
import com.map.invest.mapInvest.entity.PessoaJuridica;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class PessoaJuridicaCanonicoFactory {

    public PessoaJuridicaCanonico builderPessoaJuridica(PessoaJuridica juridica) {
        return Optional.ofNullable(juridica).map(entidade -> {
            return PessoaJuridicaCanonico.builder()
                    .pessoaJuridicaID(entidade.getPessoaJuridicaID())
                    .documentoPrincipalID(entidade.getDocumentoPrincipalID())
                    .nomeComercia(entidade.getNomeComercia())
                    .dataConstituicao(entidade.getDataConstituicao())
                    .tipoInscricao(entidade.getTipoInscricao())
                    .numeroInscricao(entidade.getNumeroInscricao())
                    .naturazaJuridica(entidade.getNaturazaJuridica())
                    .ramoAtividade(entidade.getRamoAtividade())
                    .objetivoSocial(entidade.getObjetivoSocial())
                    .build();
        }).orElse(null);
    }

    public List<PessoaJuridicaCanonico> pessoaJuridicaCanonico(List<PessoaJuridica> resultList) {
        return Optional.ofNullable(resultList).map(lista -> {
            return lista.stream().map(el -> builderPessoaJuridica(el)).collect(Collectors.toList());
        }).orElse(new ArrayList<>());

    }
}
