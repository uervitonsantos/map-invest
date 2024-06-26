package com.map.invest.util.constantes;

public enum TipoPerfilEnum {

    ADMINISTRADOR("Administrador"),
    MASTER("Master"),
    MEDIO("Medio"),
    BASICO("Basico");
    private final String valor;

    private TipoPerfilEnum(String valor) {
        this.valor = valor;
    }

    public String getValor() {
        return valor;
    }

    public static TipoPerfilEnum findByCodigo(String valor) {
        for (TipoPerfilEnum value : TipoPerfilEnum.values()) {
            if (value.getValor().equalsIgnoreCase(valor.trim())) {
                return value;
            }
        }
        return null;
    }
}



