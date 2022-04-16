package org.eagleinvsys.test.converters.impl;

import org.eagleinvsys.test.converters.ConvertibleCollection;
import org.eagleinvsys.test.converters.ConvertibleMessage;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class MyConvertibleCollection implements ConvertibleCollection {

    private final List<ConvertibleMessage> messagesList;

    public MyConvertibleCollection(List<ConvertibleMessage> list) {
        this.messagesList = list;
    }

    public static MyConvertibleCollection of(List<Map<String, String>> mapList) {
        List<ConvertibleMessage> records = mapList.stream().map(LinkedHashMap::new)
                .map(MyConvertibleMessage::new).collect(Collectors.toList());
        return new MyConvertibleCollection(records);
    }

    @Override
    public Collection<String> getHeaders() {
        return messagesList.stream().map(message -> (MyConvertibleMessage) message).findFirst().get().getRecord().keySet();
    }

    @Override
    public List<ConvertibleMessage> getRecords() {
        return messagesList;
    }
}
