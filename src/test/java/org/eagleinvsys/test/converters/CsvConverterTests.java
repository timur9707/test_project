package org.eagleinvsys.test.converters;

import org.eagleinvsys.test.converters.impl.CsvConverter;
import org.eagleinvsys.test.converters.impl.MyConvertibleCollection;
import org.eagleinvsys.test.converters.impl.MyConvertibleMessage;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CsvConverterTests {

    // TODO: implement JUnit 5 tests for CsvConverter

    private static ConvertibleCollection collection;
    private final CsvConverter csvConverter = new CsvConverter();

    @BeforeAll
    public static void setUpTestData() {
        LinkedHashMap<String, String> linkedHashMap1 = new LinkedHashMap<>() {{
            put("header 1", "value 1");
            put("header 2", "value 2");
            put("header 3", "value 3");
        }};
        LinkedHashMap<String, String> linkedHashMap2 = new LinkedHashMap<>() {{
            put("header 1", "value 21");
            put("header 2", "value 22");
            put("header 3", "value 23");
        }};
        LinkedHashMap<String, String> linkedHashMap3 = new LinkedHashMap<>() {{
            put("header 1", "value 31");
            put("header 2", "value 32");
            put("header 3", "value 33");
        }};

        List<ConvertibleMessage> messageList = Arrays.asList(new MyConvertibleMessage(linkedHashMap1),
                new MyConvertibleMessage(linkedHashMap2), new MyConvertibleMessage(linkedHashMap3));

        collection = new MyConvertibleCollection(messageList);
    }

    @Test
    public void csvConverterTest() {
        File file = new File("src/test/resources/csvTestFile.csv");
        try (
                OutputStream fileOutputStream = new FileOutputStream(file);
        ) {
            csvConverter.convert(collection, fileOutputStream);
            List<String> fileLines = Files.readAllLines(file.toPath());
            assertEquals(collection.getHeaders().size(), fileLines.get(0).split(",").length);
            assertEquals(3, fileLines.get(1).split(",").length);
            assertEquals(4, fileLines.size());
            assertEquals("value 21,value 22,value 23", fileLines.get(2));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}