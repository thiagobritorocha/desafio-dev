package com.upload.file.api.adapter.outbound.repository.transactionfile;

import com.upload.file.api.domain.entity.TransactionFile;
import com.upload.file.api.domain.mapper.transactionfile.TransactionFileMapper;
import com.upload.file.api.domain.ports.outbound.transactionfile.FindTransactionFileAdapterPort;
import java.util.Objects;
import org.springframework.stereotype.Component;

@Component
public class FindTransactionFileAdapter implements FindTransactionFileAdapterPort {

    private final TransactionFileRepository transactionFileRepository;

    public FindTransactionFileAdapter(TransactionFileRepository transactionFileRepository) {
        this.transactionFileRepository = transactionFileRepository;
    }

    @Override
    public TransactionFile execute(String name) {
        TransactionFileEntity transactionFileEntityResult =
                transactionFileRepository.findByFileName(name);
        if (Objects.isNull(transactionFileEntityResult)) {
            return null;
        }
        return TransactionFileMapper.execute(transactionFileEntityResult);
    }
}
