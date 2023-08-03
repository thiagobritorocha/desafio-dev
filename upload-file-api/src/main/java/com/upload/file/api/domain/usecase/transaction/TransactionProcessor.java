package com.upload.file.api.domain.usecase.transaction;

import com.upload.file.api.adapter.outbound.repository.transaction.TransactionEntity;
import com.upload.file.api.domain.mapper.transaction.TransactionEntityMapper;
import java.util.List;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class TransactionProcessor implements ItemProcessor<String, List<TransactionEntity>> {

    @Override
    public List<TransactionEntity> process(String item) {
        log.info("TransactionsProcessor - process item with lines {} ", item.lines().count());

        List<TransactionEntity> transactions =
                item.lines().map(TransactionEntityMapper::execute).collect(Collectors.toList());

        log.info("TransactionsProcessor - process item with {} transactions", transactions.size());

        return transactions;
    }
}
