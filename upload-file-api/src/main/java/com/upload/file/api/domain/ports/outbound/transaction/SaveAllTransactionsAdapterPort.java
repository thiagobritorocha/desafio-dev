package com.upload.file.api.domain.ports.outbound.transaction;

import com.upload.file.api.adapter.outbound.repository.transaction.TransactionEntity;
import java.util.List;

public interface SaveAllTransactionsAdapterPort {

    public void execute(List<TransactionEntity> items);
}
