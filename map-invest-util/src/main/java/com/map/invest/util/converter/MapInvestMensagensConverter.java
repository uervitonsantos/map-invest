package com.map.invest.util.converter;

import com.map.invest.util.validacao.MapInvestMensagens;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.util.Optional;

@Converter(autoApply = true)
public class MapInvestMensagensConverter implements AttributeConverter<MapInvestMensagens, String> {
    @Override
    public String convertToDatabaseColumn(MapInvestMensagens mapInvestMensagens) {
        return Optional.ofNullable(mapInvestMensagens).map(a -> mapInvestMensagens.getValor()).orElse(null);
    }

    @Override
    public MapInvestMensagens convertToEntityAttribute(String s) {
        return MapInvestMensagens.findByCodigo(s);
    }
}
