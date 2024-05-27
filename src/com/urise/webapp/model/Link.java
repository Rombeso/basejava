package com.urise.webapp.model;

import java.util.Objects;

public class Link {
    private final String name;
    private String url;
    public Link(String name) {
        Objects.requireNonNull(name, "name must not be null");
        this.name = name;
    }
    public Link(String name, String url) {
        Objects.requireNonNull(name, "name must not be null");
        this.name = name;
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public String getUrl() {
        return url;
    }

    @Override
    public String toString() {
        return "Link(" + name + ',' + url + ')';
    }


}