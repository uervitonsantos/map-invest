package com.map.invest.mapInvest.util.constantes;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum TipoEnderecoEnum {
    COMERCIAL("com"), RESIDENCIAL("res");

    private final String valor;

    private TipoEnderecoEnum(String valor) {
        this.valor = valor;
    }
    public String getValor() {
        return valor;
    }

    public static TipoEnderecoEnum findByCodigo(String valor) {
        for (TipoEnderecoEnum value : TipoEnderecoEnum.values()) {
            if (value.getValor().equalsIgnoreCase(valor.trim())) {
                return value;
            }
        }
        return null;
    }
}

