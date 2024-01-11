package com.map.invest.mapInvest.repository;

import jakarta.annotation.Resource;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;

import javax.sql.DataSource;

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
