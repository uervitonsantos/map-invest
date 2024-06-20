package com.map.invest.util.converter;

import com.map.invest.util.constantes.TipoPerfilEnum;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.util.Optional;

@Converter(autoApply = true)
public class TipoPerfilConverter implements AttributeConverter<TipoPerfilEnum, String> {
    @Override
    public String convertToDatabaseColumn(TipoPerfilEnum tipoPerfilEnum) {
        return Optional.ofNullable(tipoPerfilEnum).map(a -> tipoPerfilEnum.getValor()).orElse(null);
    }

    @Override
    public TipoPerfilEnum convertToEntityAttribute(String s) {
        return TipoPerfilEnum.findByCodigo(s);
    }
}
