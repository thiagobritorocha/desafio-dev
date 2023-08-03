package com.upload.file.api.adapter.outbound.repository.transaction;

import com.upload.file.api.domain.ports.outbound.transaction.SaveAllTransactionsAdapterPort;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class SaveAllTransactionsAdapter implements SaveAllTransactionsAdapterPort {

    private final TransactionRepository transactionRepository;

    public SaveAllTransactionsAdapter(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    public void execute(List<TransactionEntity> items) {
        log.info("SaveAllTransactions - {} ", items);
        transactionRepository.saveAll(items);
    }
}
