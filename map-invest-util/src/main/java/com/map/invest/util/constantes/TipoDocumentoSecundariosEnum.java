package com.map.invest.util.constantes;

public enum TipoDocumentoSecundariosEnum {

    RG("RG"), CNH("CNH");

    private final String valor;

    private TipoDocumentoSecundariosEnum(String valor) {
        this.valor = valor;
    }

    public static TipoDocumentoSecundariosEnum findByCodigo(String valor) {
        for (TipoDocumentoSecundariosEnum value : TipoDocumentoSecundariosEnum.values()) {
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
