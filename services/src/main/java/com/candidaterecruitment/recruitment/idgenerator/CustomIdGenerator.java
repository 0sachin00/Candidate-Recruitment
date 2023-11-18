package com.candidaterecruitment.recruitment.idgenerator;

import lombok.NoArgsConstructor;
import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;

import java.io.Serializable;
import java.time.LocalDateTime;

import java.time.format.DateTimeFormatter;
public class CustomIdGenerator {

    public static String generateId(String prefix) {
        String year = getCurrentYear();
        String timestamp = getCurrentTimestamp();
        return prefix + year + timestamp;
    }

    private static String getCurrentYear() {
        return String.valueOf(LocalDateTime.now().getYear());
    }

    private static String getCurrentTimestamp() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
        return LocalDateTime.now().format(formatter);
    }
}
