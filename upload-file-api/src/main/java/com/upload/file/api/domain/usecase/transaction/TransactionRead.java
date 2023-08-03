package com.upload.file.api.domain.usecase.transaction;

import com.upload.file.api.domain.entity.TransactionFile;
import com.upload.file.api.domain.ports.outbound.transaction.GetTransactionFileByIdAdapterPort;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.InputStreamReader;
import java.util.UUID;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@StepScope
@Slf4j
public class TransactionRead implements ItemReader<String> {

    @Autowired private Parameters parameters;

    @Autowired private GetTransactionFileByIdAdapterPort getTransactionFileByIdAdapterPort;

    private boolean processed;

    @Override
    public String read()
            throws Exception, UnexpectedInputException, ParseException,
                    NonTransientResourceException {
        if (!processed) {
            log.info("TransactionsRead - Start read() with filename {} ", parameters.getFileName());

            TransactionFile transactionFile =
                    getTransactionFileByIdAdapterPort.execute(
                            UUID.fromString(parameters.getFileUuid()));

            log.info(
                    "TransactionsRead - Finalized read() with filename {} ",
                    parameters.getFileName());

            processed = true;

            return readFileContent(transactionFile.getFileData());
        }
        return null;
    }

    private String readFileContent(byte[] file) {
        BufferedReader br =
                new BufferedReader(new InputStreamReader(new ByteArrayInputStream(file)));
        StringBuilder contentBuilder = new StringBuilder();
        try {
            String line;
            while ((line = br.readLine()) != null) {
                contentBuilder.append(line).append("\n");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return contentBuilder.toString();
    }
}
