package com.urise.webapp.model;

import java.util.ArrayList;
import java.util.List;

public class Organization {
    private String name;
    private String website;
    private List<Period> periods;


    public Organization(String name, String website) {
        this.name = name;
        this.website = website;
        this.periods = new ArrayList<>();
    }

    public void addPeriod(Period period) {
        this.periods.add(period);
    }

    public List<Period> getPeriods() {
        return periods;
    }

    public String getName() {
        return name;
    }

    public String getWebsite() {
        return website;
    }


}
