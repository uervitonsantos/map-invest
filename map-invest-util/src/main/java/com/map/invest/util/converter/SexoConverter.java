package com.map.invest.util.converter;

import com.map.invest.util.constantes.SexoEnum;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.util.Optional;

@Converter(autoApply = true)
public class SexoConverter implements AttributeConverter<SexoEnum, String> {
    @Override
    public String convertToDatabaseColumn(SexoEnum sexoEnum) {
        return Optional.ofNullable(sexoEnum).map(a -> sexoEnum.getValor()).orElse(null);
    }

    @Override
    public SexoEnum convertToEntityAttribute(String s) {
        return SexoEnum.findByCodigo(s);
    }
}
