package com.upload.file.api.domain.usecase.transaction;

import com.upload.file.api.adapter.outbound.repository.transaction.TransactionEntity;
import com.upload.file.api.domain.ports.outbound.transaction.SaveAllTransactionsAdapterPort;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@StepScope
@Component
@Slf4j
public class TransactionWriter implements ItemWriter<List<TransactionEntity>> {

    @Autowired private SaveAllTransactionsAdapterPort saveAllTransactionsAdapterPort;

    @Autowired private Parameters parameters;

    @Override
    public void write(List<? extends List<TransactionEntity>> items) throws Exception {
        log.info("TransactionsWriter - {} ", items);

        saveAllTransactionsAdapterPort.execute(items.get(0));

        log.info("TransactionsWriter - finish with {} insertions ", items.get(0).size());
    }
}
