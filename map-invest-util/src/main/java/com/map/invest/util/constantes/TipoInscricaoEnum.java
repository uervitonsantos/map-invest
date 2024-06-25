package com.map.invest.util.constantes;

public enum TipoInscricaoEnum {

    MICROEMPREENDEDOR_INDIVIDUAL("MEI"),
    EMPRESARIO_INDIVIDUAL("EI"),
    SOCIEDADE_LTDA("LTDA"),
    SOCIEDADE_LIMITADA_UNIPESSOAL("SLU"),
    SOCIEDADE_SIMPLE("SS"),
    SOCIEDADE_ANONIMA("SA");

    private final String valor;

    private TipoInscricaoEnum(String valor) {
        this.valor = valor;
    }

    public static TipoInscricaoEnum findByCodigo(String valor) {
        for (TipoInscricaoEnum value : TipoInscricaoEnum.values()) {
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
