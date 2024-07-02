package com.test.task;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class test3 {
    private static final ObjectMapper objectMapper = new ObjectMapper();
    static {
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
    }

    public static void main(String[] args) throws IOException {
        Values values = readListObjectFromFile(Values.class, args[0]);
        Tests tests = readListObjectFromFile(Tests.class, args[1]);
        Map<Integer, String> valueMap = values.getValues().stream().collect(Collectors.toMap(Value::getId, Value::getValue));
        fillTests(tests.getTests(), valueMap);
        writeToFile(args[2], tests);
    }

    private static void writeToFile(String path, Tests tests) throws IOException {
        objectMapper.writeValue(new File(path), tests);
    }

    private static void fillTests(List<Test> tests, Map<Integer, String> valueMap) {
        if (tests == null) {
            return;
        }
        for (Test test : tests) {
            fillTests(test.getValues(), valueMap);
            test.setValue(valueMap.get(test.getId()));
        }
    }

    private static <T> T readListObjectFromFile(Class<T> clazz, String path) throws IOException {
        return objectMapper.readValue(new File(path), clazz);
    }


}

class Tests {
    List<Test> tests;

    public List<Test> getTests() {
        return tests;
    }

    public void setTests(List<Test> tests) {
        this.tests = tests;
    }
}

class Values {
    List<Value> values;

    public List<Value> getValues() {
        return values;
    }

    public void setValues(List<Value> values) {
        this.values = values;
    }
}
class Value {
    private Integer id;
    private String value;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}

class Test {
    private Integer id;
    private String title;
    private String value;
    private List<Test> values;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public List<Test> getValues() {
        return values;
    }

    public void setValues(List<Test> values) {
        this.values = values;
    }
}