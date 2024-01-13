package com.map.invest.mapInvest.util.converter;

import com.map.invest.mapInvest.util.constantes.TipoTelefoneEnum;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.util.Optional;

@Converter(autoApply = true)
public class TipoTelefoneConverter implements AttributeConverter<TipoTelefoneEnum, String> {
    @Override
    public String convertToDatabaseColumn(TipoTelefoneEnum tipoTelefoneEnum) {
        return Optional.ofNullable(tipoTelefoneEnum).map(a -> tipoTelefoneEnum.getValor()).orElse(null);
    }

    @Override
    public TipoTelefoneEnum convertToEntityAttribute(String s) {
        return TipoTelefoneEnum.findByCodigo(s);
    }
}
