package com.map.invest.mapInvest.bean;

import com.map.invest.mapInvest.canonico.UsuarioCanonico;
import com.map.invest.mapInvest.filtro.FiltroWrapper;
import com.map.invest.mapInvest.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UsuarioBean {

    @Autowired
    private UsuarioService service;

    public UsuarioCanonico buscaUsuario(Long usuarioID) {
        return service.buscaUsuario(usuarioID);
    }

    public List<UsuarioCanonico> buscaUsuarios(FiltroWrapper filtro) {
        return service.buscaUsuarios(filtro);
    }

    public UsuarioCanonico criaUsuario(UsuarioCanonico canonico) {
        return service.criaUsuario(canonico);
    }

    public UsuarioCanonico editaUsuario(UsuarioCanonico canonico) {
        Long editaUsuario = service.editaUsuario(canonico);
        return buscaUsuario(editaUsuario);
    }

    public void removeUsuario(Long usuarioID) {
        service.removeUsuario(usuarioID);
    }
}
