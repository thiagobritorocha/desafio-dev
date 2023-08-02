package com.upload.file.api.domain.entity;

import com.upload.file.api.domain.entity.enums.TransactionFileStatus;
import java.time.LocalDateTime;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class TransactionFile {

    private UUID id;

    private String fileName;

    private TransactionFileStatus transactionFileStatus;

    private LocalDateTime startProcessTime;

    private LocalDateTime endProcessTime;
    //TODO Salvar arquivo no bucket S3
    private byte[] fileData;
}
