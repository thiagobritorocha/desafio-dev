package com.upload.file.api.domain.helper;

import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;

public class JobParameterBuilderHelper {

    public static final String FILE_PATH = "filePath";
    public static final String FILE_UUID = "fileUuid";
    public static final String FILE_NAME = "fileName";

    public static JobParameters create(final String id) {
        return new JobParametersBuilder().addString(FILE_UUID, id.toString()).toJobParameters();
    }
}
