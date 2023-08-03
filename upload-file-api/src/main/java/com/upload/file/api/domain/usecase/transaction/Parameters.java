package com.upload.file.api.domain.usecase.transaction;

import lombok.Getter;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Getter
@Component
@StepScope
public class Parameters {

    @Value("#{jobParameters['fileUuid']}")
    private String fileUuid;
}
