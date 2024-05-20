package com.urise.webapp.model;

import java.util.Objects;

public class TextSection extends AbstractSection {
    private String content;

    public TextSection(String content) {
        Objects.requireNonNull(content, "content mast not be empty");
        this.content = content;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TextSection that = (TextSection) o;

        return content.equals(that.content);
    }

    @Override
    public int hashCode() {
        return content.hashCode();
    }

    @Override
    public String toString() {
        return content;
    }

    public String getContent() {
        return content;
    }

    @Override
    public void print() {
        System.out.println(content);
    }
}
