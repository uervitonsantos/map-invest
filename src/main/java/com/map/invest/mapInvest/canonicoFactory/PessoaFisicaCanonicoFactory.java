package com.map.invest.mapInvest.canonicoFactory;

import com.map.invest.mapInvest.canonico.PessoaFisicaCanonico;
import com.map.invest.mapInvest.entity.PessoaFisica;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class PessoaFisicaCanonicoFactory {

    @Autowired
    private DocumentosSecundariosCanonicoFactory documentosSecundariosCanonicoFactory;

    public PessoaFisicaCanonico builderPessoaFisica(PessoaFisica fisica) {
        return Optional.ofNullable(fisica).map(entidade -> {
            return PessoaFisicaCanonico.builder()
                    .pessoaFisicaID(entidade.getPessoaFisicaID())
                    .documentoPrincipalID(entidade.getDocumentoPrincipalID())
                    .sobrenome(entidade.getSobrenome())
                    .dataNascimento(entidade.getDataNascimento())
                    .sexo(entidade.getSexo())
                    .nacionalidade(entidade.getNacionalidade())
                    .naturalidade(entidade.getNaturalidade())
                    .documentosSecundarios(documentosSecundariosCanonicoFactory.documentosSecundariosCanonico(entidade.getDocumentosSecundarios()))
                    .build();
        }).orElse(null);
    }

    public List<PessoaFisicaCanonico> pessoasFisicaCanonico(List<PessoaFisica> resultList) {
        return Optional.ofNullable(resultList).map(lista -> {
            return lista.stream().map(el -> builderPessoaFisica(el)).collect(Collectors.toList());
        }).orElse(new ArrayList<>());
    }

}
