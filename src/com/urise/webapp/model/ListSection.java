package com.urise.webapp.model;

import java.util.List;

public class ListSection extends AbstractSection {
    private List<String> strings;

    public ListSection(List<String> strings) {
        this.strings = strings;
    }

    public List<String> getStrings() {
        return strings;
    }

    public void addString(String string) {
        strings.add(string);
    }

    @Override
    public void print() {
        strings.forEach(item -> System.out.println(item));
    }
}