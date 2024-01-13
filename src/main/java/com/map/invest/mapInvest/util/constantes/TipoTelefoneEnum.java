package com.map.invest.mapInvest.util.constantes;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;

public enum TipoTelefoneEnum {

    RESIDENCIAL("res"), CELULAR("cel"), COMERCIAL("com");
    private final String valor;

    private TipoTelefoneEnum(String valor) {
        this.valor = valor;
    }
    public String getValor() {
        return valor;
    }

    @JsonCreator
    public static TipoTelefoneEnum findByCodigo(String valor) {
        for (TipoTelefoneEnum value : TipoTelefoneEnum.values()) {
            if (value.getValor().equalsIgnoreCase(valor.trim())) {
                return value;
            }
        }
        return null;
    }
}



