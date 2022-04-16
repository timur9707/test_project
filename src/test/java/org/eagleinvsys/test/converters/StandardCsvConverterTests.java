package org.eagleinvsys.test.converters;

import org.eagleinvsys.test.converters.impl.CsvConverter;
import org.eagleinvsys.test.converters.impl.StandardCsvConverter;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import static org.mockito.Mockito.*;

class StandardCsvConverterTests {

    // TODO: implement JUnit 5 tests for StandardCsvConverter

    private final List<Map<String, String>> collectionToConvert = List.of(Collections.singletonMap("a", "b"));

    @Test
    public void standardCsvConverterTest() {
        CsvConverter csvConverterMock = mock(CsvConverter.class);
        StandardCsvConverter standardCsvConverter = new StandardCsvConverter(csvConverterMock);
        standardCsvConverter.convert(collectionToConvert, System.out);
        verify(csvConverterMock, times(1))
                .convert(any(), any());
    }

}