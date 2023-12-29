package kr.co.studyhubinu.studyhubserver.apply.domain.insepection;

import kr.co.studyhubinu.studyhubserver.exception.apply.InspectionNotFoundException;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class InspectionConverter implements AttributeConverter<Inspection, String> {

    @Override
    public String convertToDatabaseColumn(Inspection inspection) {
        if(inspection == null) {
            return null;
        }
        return inspection.getValue();
    }

    @Override
    public Inspection convertToEntityAttribute(String dbData) {
        if(dbData == null) {
            return null;
        }
        try {
            return Inspection.fromCode(dbData);
        } catch (IllegalArgumentException e) {
            throw new InspectionNotFoundException();
        }
    }
}
