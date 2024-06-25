package com.map.invest.util.constantes;

public enum AtivoEnum {

    SIM("S"), NAO("N");

    private final String valor;

    private AtivoEnum(String valor) {
        this.valor = valor;
    }

    public static AtivoEnum findByCodigo(String valor) {
        for (AtivoEnum value : AtivoEnum.values()) {
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
