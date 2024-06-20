package com.map.invest.repository;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;

import javax.sql.DataSource;

@Transactional
public class MapInvestRepositorio extends AbstractRepositorio {

    @Autowired
    private EntityManager em;

    @Autowired
    private DataSource dataSource;

    @Override
    public EntityManager getEntityManager() {
        return em;
    }

    @Override
    public DataSource getDataSource() {
        return dataSource;
    }
}
