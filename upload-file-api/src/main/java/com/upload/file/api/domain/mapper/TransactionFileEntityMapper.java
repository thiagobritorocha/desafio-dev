package com.upload.file.api.domain.mapper;

import com.upload.file.api.adapter.outbound.repository.transactionfile.TransactionFileEntity;
import com.upload.file.api.domain.entity.TransactionFile;

public class TransactionFileEntityMapper {

    public static TransactionFileEntity execute(TransactionFile transactionFile) {
        return TransactionFileEntity.builder()
                .fileName(transactionFile.getFileName())
                .transactionFileStatus(transactionFile.getTransactionFileStatus())
                .startProcessTime(transactionFile.getStartProcessTime())
                .endProcessTime(transactionFile.getEndProcessTime())
                .fileData(transactionFile.getFileData())
                .build();
    }
}
