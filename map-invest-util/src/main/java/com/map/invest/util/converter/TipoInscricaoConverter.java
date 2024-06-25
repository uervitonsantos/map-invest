package com.map.invest.util.converter;

import com.map.invest.util.constantes.TipoInscricaoEnum;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.util.Optional;

@Converter(autoApply = true)
public class TipoInscricaoConverter implements AttributeConverter<TipoInscricaoEnum, String> {
    @Override
    public String convertToDatabaseColumn(TipoInscricaoEnum tipoInscricaoEnum) {
        return Optional.ofNullable(tipoInscricaoEnum).map(a -> tipoInscricaoEnum.getValor()).orElse(null);
    }

    @Override
    public TipoInscricaoEnum convertToEntityAttribute(String s) {
        return TipoInscricaoEnum.findByCodigo(s);
    }
}
