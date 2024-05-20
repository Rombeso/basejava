package com.urise.webapp.model;

import java.util.List;

public class OrganizationSection extends AbstractSection {
    private List<Organization> organizations;

    public OrganizationSection(List organizations) {
        this.organizations = organizations;
    }

    public void addOrganization(Organization organization) {
        organizations.add(organization);
    }

    @Override
    public void print() {
        organizations.stream().forEach(organization -> {
            System.out.println();
            System.out.println(organization.getName());
            System.out.println(organization.getWebsite());
            organization.getPeriods().stream().forEach(date -> {
                System.out.println(date.getDateTo() + " - " + date.getDateFrom());
                System.out.println(date.getTitle());
                System.out.println(date.getDescription());
            });
        });
    }
}
