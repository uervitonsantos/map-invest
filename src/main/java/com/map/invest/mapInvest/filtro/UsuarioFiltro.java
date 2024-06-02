package com.map.invest.mapInvest.filtro;


import lombok.*;

import java.util.List;


@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioFiltro implements Filtro {

    private List<Long> usuarioID;
    private Long perfilID;
    private String CodPerfil;
    private String nomePerfil;
    private String nome;
    private String sobreNome;
    private String cpfcnpj;
    private String email;
    private String login;

    public Boolean hasUsuarioID() {
        return usuarioID != null;
    }

    public Boolean hasPerfilID() {
        return perfilID != null;
    }

    public Boolean hasCodPerfil() {
        return CodPerfil != null && CodPerfil.isEmpty();
    }

    public Boolean hasNome() {
        return nome != null && nome.isEmpty();
    }

    public Boolean hasSobreNome() {
        return sobreNome != null && sobreNome.isEmpty();
    }

    public Boolean hasCpfcnpj() {
        return cpfcnpj != null && cpfcnpj.isEmpty();
    }

    public Boolean hasEmail() {
        return email != null && email.isEmpty();
    }

    public Boolean hasLogin() {
        return login != null && login.isEmpty();
    }
}
