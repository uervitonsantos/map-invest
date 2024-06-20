package com.map.invest.bean;

import com.map.invest.entity.Endereco;
import com.map.invest.service.EnderecoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EnderecoBean {

    @Autowired
    private EnderecoService service;

    public Endereco buscarEnderecoPorCep(String cep) {
        return service.buscarEnderecoPorCep(cep);
    }
}
