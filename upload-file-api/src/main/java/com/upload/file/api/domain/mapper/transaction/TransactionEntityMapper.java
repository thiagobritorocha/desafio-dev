package com.upload.file.api.domain.mapper.transaction;

import com.upload.file.api.adapter.outbound.repository.transaction.TransactionEntity;
import com.upload.file.api.adapter.outbound.repository.transaction.TransactionTypeEntity;
import com.upload.file.api.domain.entity.enums.TransactionType;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;

public class TransactionEntityMapper {

    private static DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyyMMdd");
    private static DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HHmmss");

    public static TransactionEntity execute(String line) {
        Double value = Double.parseDouble(line.substring(9, 19)) / 100.00;
        return TransactionEntity.builder()
                .cpf(line.substring(19, 30))
                .value(value)
                .date(LocalDate.parse(line.substring(1, 9), dateFormatter))
                .hour(LocalTime.parse(line.substring(42, 48), timeFormatter))
                .cardNumber(line.substring(30, 42))
                .storeOwner(line.substring(48, 62).trim())
                .storeName(line.substring(62).trim())
                .type(getTransactionTypeByKey(line.substring(0, 1)))
                .build();
    }

    private static HashMap<String, TransactionTypeEntity> allTypes() {
        HashMap<String, TransactionTypeEntity> types = new HashMap<>();

        types.put("1", new TransactionTypeEntity(1L, "Débito", "Entrada", "+"));
        types.put("2", new TransactionTypeEntity(2L, "Boleto", "Saída", "-"));
        types.put("3", new TransactionTypeEntity(3L, "Financiamento", "Saída", "-"));
        types.put("4", new TransactionTypeEntity(4L, "Crédito", "Entrada", "+"));
        types.put("5", new TransactionTypeEntity(5L, "Recebimento Empréstimo", "Entrada", "+"));
        types.put("6", new TransactionTypeEntity(6L, "Vendas", "Entrada", "+"));
        types.put("7", new TransactionTypeEntity(7L, "Recebimento TED", "Entrada", "+"));
        types.put("8", new TransactionTypeEntity(8L, "Recebimento DOC", "Entrada", "+"));
        types.put("9", new TransactionTypeEntity(9L, "Aluguel", "Saída", "-"));

        return types;
    }

    public static TransactionTypeEntity getTransactionTypeByKey(String key) {
        return allTypes().get(key);
    }
}
