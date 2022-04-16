package org.eagleinvsys.test.converters.impl;

import org.eagleinvsys.test.converters.ConvertibleMessage;

import java.util.LinkedHashMap;

public class MyConvertibleMessage implements ConvertibleMessage {

    private final LinkedHashMap<String, String> record;

    public MyConvertibleMessage(LinkedHashMap<String, String> record) {
        this.record = record;
    }

    @Override
    public String getElement(String header) {
        return record.get(header);
    }

    public String[] toStringArray() {
        return this.record.values().toArray(new String[record.values().size()]);
    }

    public LinkedHashMap<String, String> getRecord() {
        return this.record;
    }

}
