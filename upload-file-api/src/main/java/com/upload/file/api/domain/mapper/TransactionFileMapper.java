package com.upload.file.api.domain.mapper;

import com.upload.file.api.adapter.outbound.repository.transactionfile.TransactionFileEntity;
import com.upload.file.api.domain.entity.TransactionFile;
import com.upload.file.api.domain.entity.enums.TransactionFileStatus;
import com.upload.file.api.domain.exception.Error;
import java.io.IOException;
import org.springframework.web.multipart.MultipartFile;

public class TransactionFileMapper {

    public static TransactionFile execute(TransactionFileEntity transactionFileEntity) {
        return TransactionFile.builder()
                .id(transactionFileEntity.getId())
                .fileName(transactionFileEntity.getFileName())
                .transactionFileStatus(transactionFileEntity.getTransactionFileStatus())
                .startProcessTime(transactionFileEntity.getStartProcessTime())
                .endProcessTime(transactionFileEntity.getEndProcessTime())
                .fileData(transactionFileEntity.getFileData())
                .build();
    }

    public static TransactionFile execute(MultipartFile file) throws IOException {
        if (file.isEmpty()) {
            throw Error.NOT_EMPTY.toBusinessException();
        }
        return TransactionFile.builder()
                .fileName(file.getOriginalFilename())
                .transactionFileStatus(TransactionFileStatus.WAITING)
                .fileData(file.getBytes())
                .build();
    }
}
