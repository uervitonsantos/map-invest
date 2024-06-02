package com.map.invest.mapInvest.util.constantes;

public enum TipoTelefoneEnum {

    RESIDENCIAL("RES"), CELULAR("CEL"), COMERCIAL("COM");
    private final String valor;

    private TipoTelefoneEnum(String valor) {
        this.valor = valor;
    }

    public String getValor() {
        return valor;
    }

    public static TipoTelefoneEnum findByCodigo(String valor) {
        for (TipoTelefoneEnum value : TipoTelefoneEnum.values()) {
            if (value.getValor().equalsIgnoreCase(valor.trim())) {
                return value;
            }
        }
        return null;
    }
}



