package com.urise.webapp.model;

import java.time.LocalDate;

public class Period {
    private LocalDate dateTo;
    private LocalDate dateFrom;

    private String title;
    private String description;


    public Period(LocalDate dateTo, LocalDate dateFrom, String title, String description) {
        this.dateTo = dateTo;
        this.dateFrom = dateFrom;
        this.title = title;
        this.description = description;
    }

    public LocalDate getDateTo() {
        return dateTo;
    }

    public LocalDate getDateFrom() {
        return dateFrom;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }
}
