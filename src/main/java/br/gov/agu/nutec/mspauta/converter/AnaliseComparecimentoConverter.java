package br.gov.agu.nutec.mspauta.converter;

import br.gov.agu.nutec.mspauta.enums.AnaliseComparecimento;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class AnaliseComparecimentoConverter implements AttributeConverter<AnaliseComparecimento, String> {

    @Override
    public String convertToDatabaseColumn(AnaliseComparecimento attribute) {
        if (attribute == null) {
            return null;
        }
        return attribute.getDescricao();
    }

    @Override
    public AnaliseComparecimento convertToEntityAttribute(String dbData) {
        if (dbData == null || dbData.trim().isEmpty()) {
            return null;
        }
        
        for (AnaliseComparecimento status : AnaliseComparecimento.values()) {
            if (status.getDescricao().equals(dbData)) {
                return status;
            }
        }
        
        // Fallback to enum name matching for backward compatibility
        try {
            return AnaliseComparecimento.valueOf(dbData);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Valor inválido no banco de dados para AnaliseComparecimento: " + dbData);
        }
    }
}
