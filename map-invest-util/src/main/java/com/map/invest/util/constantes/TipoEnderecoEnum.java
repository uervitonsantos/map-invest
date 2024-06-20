package com.map.invest.util.constantes;

public enum TipoEnderecoEnum {
    COMERCIAL("COM"), RESIDENCIAL("RES");

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

