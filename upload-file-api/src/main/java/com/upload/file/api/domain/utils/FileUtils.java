package com.upload.file.api.domain.utils;

import com.upload.file.api.domain.exception.Error;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class FileUtils {

    public static String readContent(byte[] file) {
        StringBuilder contentBuilder = new StringBuilder();
        try (BufferedReader br =
                new BufferedReader(new InputStreamReader(new ByteArrayInputStream(file)))) {
            String line;
            while ((line = br.readLine()) != null) {
                contentBuilder.append(line).append("\n");
            }
        } catch (IOException e) {
            throw Error.CONTENT_FILE.toBusinessException();
        }
        return contentBuilder.toString();
    }
}
