package com.flight.advisor.service.upload;

import com.opencsv.CSVReader;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStreamReader;
import java.io.Reader;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class GetDataFromFile {

    @SneakyThrows
    public List<String[]> execute(MultipartFile file) {
        try (
                Reader reader = new InputStreamReader(file.getInputStream());
                CSVReader csvReader = new CSVReader(reader)
        ) {

            return csvReader.readAll();
        }
    }
}
