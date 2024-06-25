package com.map.invest.util.converter;

import com.map.invest.util.constantes.AtivoEnum;
import com.map.invest.util.constantes.SexoEnum;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.util.Optional;

@Converter(autoApply = true)
public class AtivoConverter implements AttributeConverter<AtivoEnum, String> {
    @Override
    public String convertToDatabaseColumn(AtivoEnum ativoEnum) {
        return Optional.ofNullable(ativoEnum).map(a -> ativoEnum.getValor()).orElse(null);
    }

    @Override
    public AtivoEnum convertToEntityAttribute(String s) {
        return AtivoEnum.findByCodigo(s);
    }
}
