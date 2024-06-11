package com.map.invest.mapInvest.repository;

import com.google.common.collect.Lists;
import com.map.invest.mapInvest.canonico.PessoaCanonico;
import com.map.invest.mapInvest.canonicoFactory.PessoaCanonicoFactory;
import com.map.invest.mapInvest.entity.Pessoa;
import com.map.invest.mapInvest.filtro.FiltroWrapper;
import com.map.invest.mapInvest.filtro.PessoaFiltro;
import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class PessoaRepositorio extends MapInvestRepositorio {

    @Autowired
    private PessoaCanonicoFactory pessoaCanonicoFactory;

    public Pessoa busca(Long pessoaID) {
        return busca(Pessoa.class, pessoaID);
    }

    public PessoaCanonico buscaPessoa(Long pessoaID) {
        Pessoa pessoa = busca(pessoaID);
        return Optional.ofNullable(pessoa).map(u -> {
            return pessoaCanonicoFactory.builderPessoa(u);
        }).orElse(null);
    }

    public List<PessoaCanonico> buscaPessoas(FiltroWrapper filtro) {
        PessoaFiltro pessoaFiltro = (PessoaFiltro) filtro.getFiltro();
        if (filtro.hasPaginacao()) {
            Long qtdRegistros = countRegistros(pessoaFiltro);
            if (qtdRegistros == 0) {
                return Lists.newArrayList();
            }
            filtro.getPaginacao().setTotalRegistros(qtdRegistros);
            CriteriaQuery<Long> criteria = criteriaQuery(Long.class);
            Root<Pessoa> root = criteria.from(Pessoa.class);
            criteria.distinct(true).select(root.get("pessoaID"))
                    .where(aplicaFiltros(root, pessoaFiltro, false)).orderBy(asc(root.get("pessoaID")));
            List<Long> resultado = paginarResultado(criteria, filtro);
            if (resultado.isEmpty()) {
                return Lists.newArrayList();
            }
            pessoaFiltro.setPessoaID(resultado);
        }
        return buscaPassoas(pessoaFiltro);
    }

    private List<PessoaCanonico> buscaPassoas(PessoaFiltro pessoaFiltro) {
        CriteriaQuery<Pessoa> criteria = criteriaQuery(Pessoa.class);
        Root<Pessoa> root = criteria.from(Pessoa.class);
        criteria.distinct(true).select(root).where(aplicaFiltros(root, pessoaFiltro, true))
                .orderBy(asc(root.get("pessoaID")));
        TypedQuery<Pessoa> query = typedQuery(criteria);
        return pessoaCanonicoFactory.pessoasCanonico(query.getResultList());
    }

    private Long countRegistros(PessoaFiltro pessoaFiltro) {
        CriteriaQuery<Long> criteria = criteriaQuery(Long.class);
        Root<Pessoa> root = criteria.from(Pessoa.class);
        criteria.select(criteriaBuilder().countDistinct(root.get("pessoaID")))
                .where(aplicaFiltros(root, pessoaFiltro, false));
        return typedQuery(criteria).getSingleResult();
    }

    private Predicate[] aplicaFiltros(Root<Pessoa> root, PessoaFiltro pessoaFiltro, boolean fetch) {
        CriteriaBuilder builder = criteriaBuilder();
        List<Predicate> predicates = Lists.newArrayList();


        return predicates.stream().toArray(Predicate[]::new);
    }

    public Pessoa salvaPessoa(Pessoa pessoa) {
        return merge(pessoa);
    }

    public Pessoa buscarPorEmail(String email) {
        TypedQuery<Pessoa> query = getEntityManager().createNamedQuery("Pessoa.buscaPorEmail", Pessoa.class);
        query.setParameter("pemail", email);
        try {
            return query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

}
