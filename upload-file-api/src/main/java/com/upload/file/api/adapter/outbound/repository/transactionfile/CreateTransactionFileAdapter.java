package com.upload.file.api.adapter.outbound.repository.transactionfile;

import com.upload.file.api.domain.entity.TransactionFile;
import com.upload.file.api.domain.mapper.transactionfile.TransactionFileEntityMapper;
import com.upload.file.api.domain.mapper.transactionfile.TransactionFileMapper;
import com.upload.file.api.domain.ports.outbound.transactionfile.CreateTransactionFileAdapterPort;
import javax.transaction.Transactional;
import org.springframework.stereotype.Component;

@Component
public class CreateTransactionFileAdapter implements CreateTransactionFileAdapterPort {

    private final TransactionFileRepository transactionFileRepository;

    public CreateTransactionFileAdapter(TransactionFileRepository transactionFileRepository) {
        this.transactionFileRepository = transactionFileRepository;
    }

    @Override
    @Transactional
    public TransactionFile execute(TransactionFile transactionFile) {
        return TransactionFileMapper.execute(
                transactionFileRepository.save(
                        TransactionFileEntityMapper.execute(transactionFile)));
    }
}
