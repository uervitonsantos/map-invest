package com.map.invest.mapInvest.canonicoFactory;

import com.map.invest.mapInvest.canonico.PessoaCanonico;
import com.map.invest.mapInvest.entity.Pessoa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class PessoaCanonicoFactory {

    @Autowired
    private TelefoneCanonicoFactory telefoneCanonicoFactory;
    @Autowired
    private AcessoCanonicoFactory acessoCanonicoFactory;
    @Autowired
    private DocumentoPrincipalCanonicoFactory documentoPrincipalCanonicoFactory;
    @Autowired
    private EnderecoCanonicoFactory enderecoCanonicoFactory;
    @Autowired
    private AuditoriaCanonicoFactory auditoriaCanonicoFactory;

    public PessoaCanonico builderPessoa(Pessoa pessoa) {
        return Optional.ofNullable(pessoa).map(entidade -> {
            return PessoaCanonico.builder()
                    .pessoaID(entidade.getPessoaID())
                    .nome(entidade.getNome())
                    .email(entidade.getEmail())
                    .ativo(entidade.getAtivo())
                    .documentoPrincipal(documentoPrincipalCanonicoFactory.builderDocumento(entidade.getDocumentoPrincipal()))
                    .endereco(enderecoCanonicoFactory.builderEndereco(entidade.getEndereco()))
                    .telefones(telefoneCanonicoFactory.telefoneCanonico(entidade.getTelefones()))
                    .acesso(acessoCanonicoFactory.builderAcesso(entidade.getAcesso()))
                    .auditoria(auditoriaCanonicoFactory.auditoriasCanonico(entidade.getAuditoria()))
                    .build();
        }).orElse(null);
    }

    public List<PessoaCanonico> pessoasCanonico(List<Pessoa> resultList) {
        return Optional.ofNullable(resultList).map(lista -> {
            return lista.stream().map(el -> builderPessoa(el)).collect(Collectors.toList());
        }).orElse(new ArrayList<>());

    }
}

