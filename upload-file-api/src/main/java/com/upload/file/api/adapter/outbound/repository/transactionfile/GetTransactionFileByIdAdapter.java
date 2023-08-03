package com.upload.file.api.adapter.outbound.repository.transactionfile;

import com.upload.file.api.domain.entity.TransactionFile;
import com.upload.file.api.domain.exception.Error;
import com.upload.file.api.domain.mapper.transactionfile.TransactionFileMapper;
import com.upload.file.api.domain.ports.outbound.transaction.GetTransactionFileByIdAdapterPort;
import java.util.Optional;
import java.util.UUID;
import org.springframework.stereotype.Component;

@Component
public class GetTransactionFileByIdAdapter implements GetTransactionFileByIdAdapterPort {

    private final TransactionFileRepository transactionFileRepository;

    public GetTransactionFileByIdAdapter(TransactionFileRepository transactionFileRepository) {
        this.transactionFileRepository = transactionFileRepository;
    }

    @Override
    public TransactionFile execute(UUID id) {
        Optional<TransactionFileEntity> tfe = transactionFileRepository.findById(id);
        if (tfe.isPresent()) {
            return TransactionFileMapper.execute(tfe.get());
        }
        throw Error.NOT_FOUND.toBusinessException();
    }
}
