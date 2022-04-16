package org.eagleinvsys.test.converters.impl;

import com.opencsv.CSVWriter;
import org.eagleinvsys.test.converters.Converter;
import org.eagleinvsys.test.converters.ConvertibleCollection;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;

public class CsvConverter implements Converter {

    /**
     * Converts given {@link ConvertibleCollection} to CSV and outputs result as a text to the provided {@link OutputStream}
     *
     * @param collectionToConvert collection to convert to CSV format
     * @param outputStream        output stream to write CSV conversion result as text to
     */
    @Override
    public void convert(ConvertibleCollection collectionToConvert, OutputStream outputStream) {
        try (
                CSVWriter csvWriter = new CSVWriter(new BufferedWriter(new OutputStreamWriter(outputStream, StandardCharsets.UTF_8)));
        ) {
            String[] headers = collectionToConvert.getHeaders().toArray(new String[collectionToConvert.getHeaders().size()]);
            csvWriter.writeNext(headers, false);
            collectionToConvert.getRecords().forEach(record -> {
                MyConvertibleMessage message = (MyConvertibleMessage) record;
                csvWriter.writeNext(message.toStringArray(), false);
            });
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}