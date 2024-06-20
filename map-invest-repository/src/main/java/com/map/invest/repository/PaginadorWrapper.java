package com.map.invest.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PaginadorWrapper<ID> {
    private static final Integer LIMITE_REGISTRO_DEFAULT = 500;

    private final Integer limiteRegistro;
    private final int total;
    private final List<ID> ids;
    private int atual = 0;

    PaginadorWrapper(List<ID> ids) {
        this.ids = Optional.ofNullable(ids).orElse(new ArrayList<>());
        this.total = this.ids.size();
        //indica paginacao e nao deve dar problema de stackoverflow e evita a busca do parametro
        if (this.total <= 20) {
            this.limiteRegistro = this.total;

        } else {
            // caso contrario busca o parametro geral
            this.limiteRegistro = Optional.ofNullable(getValorParametrizado()).orElse(LIMITE_REGISTRO_DEFAULT);
        }
    }

    private Integer getValorParametrizado() {
        try {
            return 0;
        } catch (Exception e) {
            return null;
        }
    }

    public boolean hasNext() {
        return total > 0 && atual < total;
    }

    public List<ID> next() {
        int max = atual + limiteRegistro;
        if (max > total) {
            max = total;
        }
        List<ID> subList = ids.subList(atual, max);
        this.atual += subList.size();
        return subList;
    }
}
