package com.urise.webapp.model;

import java.util.ArrayList;
import java.util.List;

public class Organization {
    private final Link homePage;
    private List<Period> periods;

    public Organization(String name, String url) {
        this.homePage = new Link(name, url);
        this.periods = new ArrayList<>();
    }

    public void addPeriod(Period period) {
        this.periods.add(period);
    }

    public List<Period> getPeriods() {
        return periods;
    }


    @Override
    public String toString() {
        return "Organization{" +
                "homePage=" + homePage +
                ", periods=" + periods +
                '}';
    }
}
