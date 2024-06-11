package com.map.invest.mapInvest.filtro;


import com.map.invest.mapInvest.util.constantes.SexoEnum;
import com.map.invest.mapInvest.util.constantes.TipoDocumentoEnum;
import com.map.invest.mapInvest.util.constantes.TipoEnderecoEnum;
import com.map.invest.mapInvest.util.constantes.TipoTelefoneEnum;
import lombok.*;

import java.time.LocalDate;
import java.util.List;


@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PessoaFiltro implements Filtro {

    private List<Long> pessoaID;
    private String nome;
    private String sobreNome;
    private LocalDate dataNascimento;
    private SexoEnum sexo;
    private String email;
    private TipoDocumentoEnum tipoDocumento;
    private String numeroDocumento;
    private TipoEnderecoEnum tipoEndereco;
    private String cep;
    private String bairro;
    private String uf;
    private String codigo;
    private TipoTelefoneEnum tipoTelefone;
    private String numeroTelefone;
    private String login;
    private String codPerfil;
    private String nomePerfil;

    public Boolean hasPessoaID() {
        return pessoaID != null;
    }

    public Boolean hasNome() {
        return nome != null && nome.isEmpty();
    }

    public Boolean hasSobreNome() {
        return sobreNome != null && sobreNome.isEmpty();
    }

    public Boolean hasDataNascimento() {
        return dataNascimento != null;
    }

    public Boolean hasSexo() {
        return sexo != null;
    }

    public Boolean hasEmail() {
        return email != null && email.isEmpty();
    }

    public Boolean hasTipoDocumento() {
        return tipoDocumento != null;
    }
    public Boolean hasNumeroDocumento() {
        return numeroDocumento != null && numeroDocumento.isEmpty();
    }

    public Boolean hasTipoEndereco() {
        return tipoEndereco != null;
    }

    public Boolean hasCep() {
        return cep != null && cep.isEmpty();
    }

    public Boolean hasBairro() {
        return bairro != null && bairro.isEmpty();
    }

    public Boolean hasUf() {
        return uf != null && uf.isEmpty();
    }

    public Boolean hasCodigo() {
        return codigo != null && codigo.isEmpty();
    }

    public Boolean hasTipoTelefone() {
        return tipoTelefone != null;
    }

    public Boolean hasNumeroTelefone() {
        return numeroTelefone != null && numeroTelefone.isEmpty();
    }

    public Boolean hasLogin() {
        return login != null && login.isEmpty();
    }

    public Boolean hasCodPerfil() {
        return codPerfil != null && codPerfil.isEmpty();
    }

    public Boolean hasNomePerfil() {
        return nomePerfil != null && nomePerfil.isEmpty();
    }
}
