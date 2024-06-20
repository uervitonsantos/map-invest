package com.map.invest.util.constantes;

public enum SexoEnum {

    M("M"), F("F"), O("O");

    private final String valor;

    private SexoEnum(String valor) {
        this.valor = valor;
    }

    public static SexoEnum findByCodigo(String valor) {
        for (SexoEnum value : SexoEnum.values()) {
            if (value.getValor().equalsIgnoreCase(valor.trim())) {
                return value;
            }
        }
        return null;
    }

    public String getValor() {
        return valor;
    }
}
