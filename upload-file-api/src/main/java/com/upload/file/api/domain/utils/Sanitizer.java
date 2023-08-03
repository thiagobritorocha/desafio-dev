package com.upload.file.api.domain.utils;

import com.upload.file.api.domain.exception.Error;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import org.springframework.web.multipart.MultipartFile;

public class Sanitizer {

    public static List<String> sanitizeFile(MultipartFile multipartFile) {
        List<String> lines = new ArrayList<>();
        try (BufferedReader br =
                new BufferedReader(new InputStreamReader(multipartFile.getInputStream()))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (line.length() >= 80) {
                    lines.add(line);
                }
            }
        } catch (IOException e) {
            throw Error.SANITIZER_FILE.toBusinessException();
        }

        return lines;
    }

    public static String sanitize(MultipartFile multipartFile) {
        String lines = "";
        try (BufferedReader br =
                new BufferedReader(new InputStreamReader(multipartFile.getInputStream()))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (line.length() >= 80) {
                    String s = lines + line + ",";
                    lines += s;
                }
            }
        } catch (IOException e) {
            throw Error.SANITIZER_FILE.toBusinessException();
        }

        return lines;
    }
}
