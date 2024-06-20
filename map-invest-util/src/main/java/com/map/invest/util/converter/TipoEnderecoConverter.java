package com.map.invest.util.converter;

import com.map.invest.util.constantes.TipoEnderecoEnum;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.util.Optional;

@Converter(autoApply = true)
public class TipoEnderecoConverter implements AttributeConverter<TipoEnderecoEnum, String> {
    @Override
    public String convertToDatabaseColumn(TipoEnderecoEnum tipoEnderecoEnum) {
        return Optional.ofNullable(tipoEnderecoEnum).map(a -> tipoEnderecoEnum.getValor()).orElse(null);
    }

    @Override
    public TipoEnderecoEnum convertToEntityAttribute(String s) {
        return TipoEnderecoEnum.findByCodigo(s);
    }
}
