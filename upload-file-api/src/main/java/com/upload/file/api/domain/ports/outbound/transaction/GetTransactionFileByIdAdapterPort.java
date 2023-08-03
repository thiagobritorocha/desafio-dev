package com.upload.file.api.domain.ports.outbound.transaction;

import com.upload.file.api.domain.entity.TransactionFile;
import java.util.UUID;

public interface GetTransactionFileByIdAdapterPort {
    TransactionFile execute(UUID id);
}
