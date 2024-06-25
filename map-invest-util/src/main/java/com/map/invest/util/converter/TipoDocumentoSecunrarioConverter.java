package com.map.invest.util.converter;

import com.map.invest.util.constantes.TipoDocumentoSecundariosEnum;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.util.Optional;

@Converter(autoApply = true)
public class TipoDocumentoSecunrarioConverter implements AttributeConverter<TipoDocumentoSecundariosEnum, String> {
    @Override
    public String convertToDatabaseColumn(TipoDocumentoSecundariosEnum secundariosEnum) {
        return Optional.ofNullable(secundariosEnum).map(a -> secundariosEnum.getValor()).orElse(null);
    }

    @Override
    public TipoDocumentoSecundariosEnum convertToEntityAttribute(String s) {
        return TipoDocumentoSecundariosEnum.findByCodigo(s);
    }
}
