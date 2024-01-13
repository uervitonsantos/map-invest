package com.map.invest.mapInvest.util.converter;

import com.map.invest.mapInvest.util.constantes.TipoDocumentoEnum;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.util.Optional;

@Converter(autoApply = true)
public class TipoDocumentoConverter implements AttributeConverter<TipoDocumentoEnum, String> {
    @Override
    public String convertToDatabaseColumn(TipoDocumentoEnum tipoDocumentoEnum) {
        return Optional.ofNullable(tipoDocumentoEnum).map(a -> tipoDocumentoEnum.getValor()).orElse(null);
    }

    @Override
    public TipoDocumentoEnum convertToEntityAttribute(String s) {
        return TipoDocumentoEnum.findByCodigo(s);
    }
}
