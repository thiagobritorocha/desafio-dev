package com.upload.file.api.domain.usecase.transactionfile;

import com.upload.file.api.adapter.outbound.producer.FileContentKafkaProducer;
import com.upload.file.api.domain.entity.TransactionFile;
import com.upload.file.api.domain.exception.Error;
import com.upload.file.api.domain.ports.inbound.transactionfile.UploadTransactionFileUseCasePort;
import com.upload.file.api.domain.ports.outbound.transactionfile.CreateTransactionFileAdapterPort;
import com.upload.file.api.domain.ports.outbound.transactionfile.FindTransactionFileAdapterPort;
import java.util.Objects;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class UploadTransactionFileUseCase implements UploadTransactionFileUseCasePort {

    private final FindTransactionFileAdapterPort findTransactionFileAdapterPort;
    private final CreateTransactionFileAdapterPort createTransactionFileAdapterPort;

    private final FileContentKafkaProducer fileContentKafkaProducer;

    private final String topicName;

    public UploadTransactionFileUseCase(
            FindTransactionFileAdapterPort findTransactionFileAdapterPort,
            CreateTransactionFileAdapterPort createTransactionFileAdapterPort,
            FileContentKafkaProducer fileContentKafkaProducer,
            @Value("${kafka.topic.transactionfile-saved}") String topicName) {
        this.findTransactionFileAdapterPort = findTransactionFileAdapterPort;
        this.createTransactionFileAdapterPort = createTransactionFileAdapterPort;
        this.fileContentKafkaProducer = fileContentKafkaProducer;
        this.topicName = topicName;
    }

    @Override
    @Transactional
    public TransactionFile execute(TransactionFile transactionFile) {
        TransactionFile transactionFileResult =
                findTransactionFileAdapterPort.execute(transactionFile.getFileName());
        if (Objects.nonNull(transactionFileResult)) {
            throw Error.DATA_INTEGRITY_VIOLATION.toBusinessException();
        }

        TransactionFile transactionFileSaved =
                createTransactionFileAdapterPort.execute(transactionFile);

        fileContentKafkaProducer.sendMessage(topicName, transactionFileSaved.getId().toString());

        return transactionFileSaved;
    }
}
