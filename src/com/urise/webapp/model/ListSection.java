package com.urise.webapp.model;

import java.util.List;
import java.util.Objects;

public class ListSection extends AbstractSection {
    private List<String> strings;

    public ListSection(List<String> strings) {
        Objects.requireNonNull(strings, "List must not be null");
        this.strings = strings;
    }

    public List<String> getStrings() {
        return strings;
    }

    public void addString(String string) {
        strings.add(string);
    }

    @Override
    public String toString() {
        return strings.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ListSection that = (ListSection) o;

        return strings.equals(that.strings);
    }

    @Override
    public int hashCode() {
        return strings.hashCode();
    }
}