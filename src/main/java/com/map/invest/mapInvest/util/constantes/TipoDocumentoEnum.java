package com.map.invest.mapInvest.util.constantes;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum TipoDocumentoEnum {

    CPF("CPF"), CNPJ("CNPJ");

    private final String valor;
    private TipoDocumentoEnum(String valor) {
        this.valor = valor;
    }

    public String getValor() {
        return valor;
    }

    @JsonCreator
    public static TipoDocumentoEnum findByCodigo(String valor) {
        for (TipoDocumentoEnum value : TipoDocumentoEnum.values()) {
            if (value.getValor().equalsIgnoreCase(valor.trim())) {
                return value;
            }
        }
        return null;
    }
}
