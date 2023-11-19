package com.candidaterecruitment.recruitment.idgenerator;

import lombok.NoArgsConstructor;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
@NoArgsConstructor
public class CustomIdGenerator implements IdentifierGenerator {

    @Override
    public Serializable generate(SharedSessionContractImplementor sharedSessionContractImplementor, Object obj) {
        if (obj instanceof IdentifiableEntity) {
            IdentifiableEntity identifiableEntity = (IdentifiableEntity) obj;
            String prefix = identifiableEntity.getIdPrefix();
            String timestamp = getCurrentTimestamp();
            return prefix + timestamp;
        } else {
            throw new IllegalArgumentException("Object does not implement IdentifiableEntity");
        }
    }

    private String getCurrentTimestamp() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyMMddHHmmssSSS");
        return LocalDateTime.now().format(formatter);
    }

}
