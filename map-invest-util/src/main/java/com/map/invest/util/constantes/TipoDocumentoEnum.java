package com.map.invest.util.constantes;

public enum TipoDocumentoEnum {

    CPF("CPF"), CNPJ("CNPJ");

    private final String valor;

    private TipoDocumentoEnum(String valor) {
        this.valor = valor;
    }

    public static TipoDocumentoEnum findByCodigo(String valor) {
        for (TipoDocumentoEnum value : TipoDocumentoEnum.values()) {
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
