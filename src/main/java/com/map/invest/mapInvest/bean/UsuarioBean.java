package com.map.invest.mapInvest.bean;

import com.map.invest.mapInvest.canonico.PessoaCanonico;
import com.map.invest.mapInvest.entity.Endereco;
import com.map.invest.mapInvest.filtro.FiltroWrapper;
import com.map.invest.mapInvest.service.PessoaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UsuarioBean {

    @Autowired
    private PessoaService service;

    public PessoaCanonico buscaPessoa(Long pessoaID) {
        return service.buscaPessoa(pessoaID);
    }

    public List<PessoaCanonico> buscaPessoas(FiltroWrapper filtro) {
        return service.buscaPessoas(filtro);
    }

    public PessoaCanonico criaPessoa(PessoaCanonico canonico, Endereco enderecoResult) {
        return service.criaPessoa(canonico, enderecoResult);
    }

    public PessoaCanonico editaPessoa(PessoaCanonico canonico, Endereco enderecoResult) {
        Long editaUsuario = service.editaPessoa(canonico, enderecoResult);
        return buscaPessoa(editaUsuario);
    }

    public void removePessoa(Long pessoaID) {
        service.removePessoa(pessoaID);
    }
}
