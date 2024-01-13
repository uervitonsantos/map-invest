package com.map.invest.mapInvest.dtoFactory;

import com.map.invest.mapInvest.canonico.AcessoCanonico;
import com.map.invest.mapInvest.canonico.UsuarioCanonico;
import com.map.invest.mapInvest.dto.AcessoDTO;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class AcessoDTOFactory {

    public AcessoDTO acessoDto(AcessoCanonico acesso){
        return Optional.ofNullable(acesso).map(canonico -> {
            return AcessoDTO.builder()
                    .acessoID(canonico.getAcessoID())
                    .usuarioID(canonico.getUsuarioID())
                    .login(canonico.getLogin())
                    .senha(canonico.getSenha())
                    .build();
        }).orElse(null);
    }
}
