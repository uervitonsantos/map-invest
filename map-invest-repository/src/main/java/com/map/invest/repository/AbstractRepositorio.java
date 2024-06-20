package com.map.invest.repository;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.map.invest.entity.MapInvestEntity;
import com.map.invest.filtro.*;
import com.map.invest.util.constantes.TipoOrdenacaoEnum;
import jakarta.persistence.*;
import jakarta.persistence.criteria.*;
import jakarta.persistence.metamodel.SingularAttribute;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Transactional
public abstract class AbstractRepositorio {

    public static final String JAVAX_PERSISTENCE_FETCHGRAPH = "javax.persistence.fetchgraph";

    String QUERY_SEQUENCE_CURRVAL = " SELECT %s.CURRVAL FROM dual ";
    String QUERY_SEQUENCE = " SELECT %s.NEXTVAL FROM dual ";

    public abstract EntityManager getEntityManager();

    public abstract DataSource getDataSource();

    public CriteriaBuilder criteriaBuilder() {
        return getEntityManager().getCriteriaBuilder();
    }

    public <T> T singleResult(CriteriaQuery<T> criteria) {
        try {
            return getEntityManager().createQuery(criteria).getSingleResult();
        } catch (NoResultException e) {
        }
        return null;
    }

    public <T> List<T> resultList(CriteriaQuery<T> criteria) {
        return removeDuplicados(typedQuery(criteria).getResultList());
    }

    public <T extends MapInvestEntity, ID> List<T> buscaPorIDs(CriteriaQuery<T> criteria, Root<T> root, Path<ID> pathID, List<ID> ids, Order... order) {
        if (ids.isEmpty()) {
            return Lists.newArrayList();
        }

        CriteriaBuilder cb = criteriaBuilder();
        List<T> result = new LinkedList<>();
        PaginadorWrapper<ID> wrapper = new PaginadorWrapper<>(ids);
        while (wrapper.hasNext()) {
            List<ID> next = wrapper.next();
            List<Predicate> predicates = new LinkedList<>();
            for (ID id : next) {
                predicates.add(cb.equal(pathID, id));
            }
            Predicate[] array = predicates.stream().toArray(Predicate[]::new);
            criteria.select(root).where(cb.or(array)).orderBy(order);
            result.addAll(resultList(criteria));
        }
        return result;
    }

    public <T extends MapInvestEntity, ID> Long count(Class<T> classRoot, Class<ID> classID, SingularAttribute<T, ID> pathID, Function<Root<T>, Predicate[]> function) {
        CriteriaBuilder builder = criteriaBuilder();
        CriteriaQuery<Long> criteriaRoot = criteriaBuilder().createQuery(Long.class);
        Root<T> root = criteriaRoot.from(classRoot);

        Subquery<ID> subQuery = criteriaRoot.subquery(classID);
        Root<T> subQueryRoot = subQuery.from(classRoot);

        List<Predicate> filtros = Lists.newArrayList(function.apply(subQueryRoot));

        filtros.add(builder.equal(root.get(pathID), subQueryRoot.get(pathID)));
        subQuery.distinct(true).select(subQueryRoot.get(pathID)).where(filtros.stream().toArray(Predicate[]::new));

        criteriaRoot.select(builder.count(builder.literal(1))).where(builder.exists(subQuery));

        return singleResult(criteriaRoot);
    }

    public <T extends MapInvestEntity, ID> CriteriaQuery<ID> retornaIDs(Class<T> classRoot, Class<ID> classID, SingularAttribute<T, ID> pathID, Function<Root<T>, Predicate[]> function, List<Ordenacao> order) {
        CriteriaQuery<ID> criteriaRoot = criteriaBuilder().createQuery(classID);
        Root<T> root = criteriaRoot.from(classRoot);

        List<Predicate> filtros = Lists.newArrayList(function.apply(root));
        Order[] ordenacao = getOrdenacaoComGroupBy(order, root);

        Path<ID> selectionPathID = root.get(pathID);

        return criteriaRoot.select(selectionPathID).where(filtros.stream().toArray(Predicate[]::new)).groupBy(selectionPathID).orderBy(ordenacao);
    }

    public <T> List<T> ordenarEPaginarResultado(FiltroWrapper filtroWrapper, CriteriaQuery<T> criteria, Root<T> root) {
        ordenar(filtroWrapper, criteria, root);
        return paginarResultado(criteria, filtroWrapper);
    }

    public <T> List<T> paginarResultado(CriteriaQuery<T> criteria, Paginador paginador) {
        TypedQuery<T> query = getEntityManager().createQuery(criteria);
        if (paginador.hasPaginacao()) {
            Paginacao paginacao = paginador.getPaginacao();
            query.setFirstResult(paginacao.getPrimeiroRegistro());
            query.setMaxResults(paginacao.getQuantidadeRegistros());
        }
        return query.getResultList();
    }

    public <T> void ordenar(Ordenador ordenador, CriteriaQuery<T> criteria, Path<T> path) {
        if (ordenador.hasOrdenacao()) {
            CriteriaBuilder builder = criteriaBuilder();
            criteria.orderBy(ordenador.getOrdenacao().stream().map(o -> {
                Path<Object> campo = path.get(o.getCampo());
                if (Ordenacao.Ordem.ASC.equals(o.getOrdem())) {
                    return builder.asc(campo);
                } else {
                    return builder.desc(campo);
                }
            }).collect(Collectors.toList()));

        }
    }

    public <T> T singleResult(TypedQuery<T> createQuery) {
        try {
            return createQuery.getSingleResult();
        } catch (NoResultException e) {
        }
        return null;
    }

    public <T> CriteriaQuery<T> criteriaQuery(Class<T> clazz) {
        return criteriaBuilder().createQuery(clazz);
    }

    public Order asc(Expression<?> path) {
        return criteriaBuilder().asc(path);
    }

    public Order desc(Expression<?> path) {
        return criteriaBuilder().desc(path);
    }

    public List<Order> orders(Order... ordens) {
        return Arrays.asList(ordens);
    }

    public <T> TypedQuery<T> typedQuery(CriteriaQuery<T> criteria) {
        return getEntityManager().createQuery(criteria);
    }

    public <T> TypedQuery<T> typedQuery(String jpql, Class<T> clazz) {
        return getEntityManager().createQuery(jpql, clazz);
    }

    public <T> TypedQuery<T> namedQuery(String jpql, Class<T> clazz) {
        return getEntityManager().createNamedQuery(jpql, clazz);
    }

    public Query namedQuery(String namedQuery) {
        return getEntityManager().createNamedQuery(namedQuery);
    }

    public StoredProcedureQuery procedureQuery(String procedureName) {
        return getEntityManager().createStoredProcedureQuery(procedureName);
    }

    public StoredProcedureQuery namedStoredProcedureQuery(String namedStoredProcedure) {
        return getEntityManager().createNamedStoredProcedureQuery(namedStoredProcedure);
    }

    public StoredProcedureQuery storedProcedureQuery(String storedProcedure) {
        return getEntityManager().createStoredProcedureQuery(storedProcedure);
    }

    public <T, ID> T busca(Class<T> clazz, ID id) {
        if (id == null) {
            return null;
        }
        getEntityManager().flush();
        T t = getEntityManager().find(clazz, id);
        if (t == null) {
            return null;
        }
        getEntityManager().refresh(t);
        return t;
    }

    public <T> List<T> removeDuplicados(List<T> list) {
        return Sets.newLinkedHashSet(list).stream().collect(Collectors.toList());
    }

    public Long buscaSequence(String sequence) {
        Number resultado = (Number) getEntityManager().createNativeQuery(String.format(QUERY_SEQUENCE, sequence)).getSingleResult();
        return resultado.longValue();
    }

    public Long buscaSequenceAtual(String sequence) {
        Number resultado = (Number) getEntityManager().createNativeQuery(String.format(QUERY_SEQUENCE_CURRVAL, sequence)).getSingleResult();
        return resultado.longValue();
    }

    public <T> void persist(T t) {
        getEntityManager().persist(t);
        getEntityManager().flush();
    }

    public <T> T merge(T t) {
        T merge = getEntityManager().merge(t);
        getEntityManager().flush();
        getEntityManager().refresh(merge);
        return merge;
    }

    public <T> void remove(T t) {
        getEntityManager().remove(t);
    }

    public <T> List<T> executeNamedQuery(Class<T> clazz, String namedQuery, List<?> parametros, String... atributosGraph) {

        EntityGraph<T> graph = getEntityManager().createEntityGraph(clazz);
        graph.addAttributeNodes(atributosGraph);

        TypedQuery<T> query = namedQuery(namedQuery, clazz);
        query.setHint(JAVAX_PERSISTENCE_FETCHGRAPH, graph);

        for (int i = 0; i < parametros.size(); i++) {
            query.setParameter(i + 1, parametros.get(i));
        }
        return query.getResultList();

    }

    public Query criaQuery(String sqlQuery) {
        return getEntityManager().createQuery(sqlQuery);
    }

    public Query criaQuery(String sqlQuery, FiltroWrapper filtro) {
        Query query = criaQuery(sqlQuery);
        if (filtro.hasPaginacao()) {
            Paginacao paginacao = filtro.getPaginacao();
            query.setFirstResult(paginacao.getPrimeiroRegistro());
            query.setMaxResults(paginacao.getQuantidadeRegistros());
        }
        return query;
    }

    public Query criaQueryNativa(String sqlQuery, FiltroWrapper filtro) {
        Query query = criaQueryNativa(sqlQuery);
        if (filtro.hasPaginacao()) {
            Paginacao paginacao = filtro.getPaginacao();
            query.setFirstResult(paginacao.getPrimeiroRegistro());
            query.setMaxResults(paginacao.getQuantidadeRegistros());
        }
        return query;
    }

    public Query criaQueryNativa(String sqlQuery) {
        return getEntityManager().createNativeQuery(sqlQuery);
    }

    public <T> Query criaQueryNativa(String sqlQuery, Class<T> clazz) {
        return getEntityManager().createNativeQuery(sqlQuery, clazz);
    }

    public <T, ID> T find(Class<T> clazz, ID id) {
        if (id == null) {
            return null;
        }
        return getEntityManager().find(clazz, id);
    }

    public Order[] getOrdenacao(List<Ordenacao> ordenacao, Root<?> root) {
        return trataOrdenacao(ordenacao, root, TipoOrdenacaoEnum.NORMAL);
    }

    public Order[] getOrdenacaoComGroupBy(List<Ordenacao> ordenacao, Root<?> root) {
        return trataOrdenacao(ordenacao, root, TipoOrdenacaoEnum.ID_GROUPBY);
    }

    private Order[] trataOrdenacao(List<Ordenacao> ordenacao, Root<?> root, TipoOrdenacaoEnum tipoOrdenacao) {
        Order[] orders = new Order[]{};
        if (ordenacao == null || ordenacao.isEmpty()) {
            return orders;
        }
        List<Order> listOrderBy = Lists.newArrayList();
        for (Ordenacao ordem : ordenacao) {
            if (Ordenacao.Ordem.ASC.equals(ordem.getOrdem())) {
                listOrderBy.add(adicionaOrdenacaoASC(root, ordem, tipoOrdenacao));
            } else {
                listOrderBy.add(adicionaOrdenacaoDESC(root, ordem, tipoOrdenacao));
            }
        }
        return listOrderBy.toArray(orders);
    }

    private Order adicionaOrdenacaoASC(Root<?> root, Ordenacao ordem, TipoOrdenacaoEnum tipoOrdenacao) {
        switch (tipoOrdenacao) {
            case NORMAL:
                return asc(root.get(ordem.getCampo()));
            case ID_GROUPBY:
                return asc(criteriaBuilder().max(root.get(ordem.getCampo())));
            default:
                return null;
        }
    }

    private Order adicionaOrdenacaoDESC(Root<?> root, Ordenacao ordem, TipoOrdenacaoEnum tipoOrdenacao) {
        switch (tipoOrdenacao) {
            case NORMAL:
                return desc(root.get(ordem.getCampo()));
            case ID_GROUPBY:
                return desc(criteriaBuilder().max(root.get(ordem.getCampo())));
            default:
                return null;
        }
    }

    public void flush() {
        getEntityManager().flush();
    }

    public void clear() {
        getEntityManager().clear();
    }

    public void flushAndClear() {
        flush();
        clear();
    }

    public void refresh(MapInvestEntity entidade) {
        getEntityManager().refresh(entidade);
    }

    public void evict(Class<?> clazz, Object ID) {
        getEntityManager().getEntityManagerFactory().getCache().evict(clazz, ID);
    }

}
