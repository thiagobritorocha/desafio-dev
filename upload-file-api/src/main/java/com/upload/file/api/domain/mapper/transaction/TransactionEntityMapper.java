package com.upload.file.api.domain.mapper.transaction;

import com.upload.file.api.adapter.outbound.repository.transaction.TransactionEntity;
import com.upload.file.api.adapter.outbound.repository.transaction.TransactionTypeEntity;
import com.upload.file.api.domain.entity.enums.TransactionType;

public class TransactionEntityMapper {

    public static TransactionEntity execute(String line) {
        TransactionType type = TransactionType.getType(Integer.parseInt(line.substring(0, 1)));
        Double value = Double.parseDouble(line.substring(9, 19)) / 100.00;
        return TransactionEntity.builder()
                .cpf(line.substring(19, 30))
                .value(value)
                .date(line.substring(1, 9))
                .hour(line.substring(42, 48))
                .cardNumber(line.substring(30, 42))
                .storeOwner(line.substring(48, 62).trim())
                .storeName(line.substring(62).trim())
                .type(TransactionTypeEntity.getTransactionType(line.substring(0, 1)))
                .build();
    }
}
