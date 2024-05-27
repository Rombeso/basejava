package com.urise.webapp.model;

import java.util.EnumMap;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

/**
 * Initial resume class
 */
public class Resume implements Comparable<Resume> {
    private final String uuid;
    private final String fullName;
    private EnumMap<ContactType, String> contacts;
    private EnumMap<SectionType, AbstractSection> sections;

    public Resume(String fullName) {
        this(UUID.randomUUID().toString(), fullName);
    }

    public Resume(String uuid, String fullName) {
        Objects.requireNonNull(uuid, "uuid mast not be null");
        Objects.requireNonNull(fullName, "fullName mast not be null");
        this.uuid = uuid;
        this.fullName = fullName;
        this.contacts = new EnumMap(ContactType.class);
        this.sections = new EnumMap(SectionType.class);
    }

    public String getUuid() {
        return uuid;
    }

    public String getFullName() {
        return fullName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Resume resume = (Resume) o;

        if (!Objects.equals(uuid, resume.uuid)) return false;
        return Objects.equals(fullName, resume.fullName);
    }

    @Override
    public int hashCode() {
        int result = uuid.hashCode();
        result = 31 * result + fullName.hashCode();
        return result;
    }


    @Override
    public String toString() {
        return uuid + '(' + fullName + ')';
    }

    public int compareTo(Resume o) {
        return uuid.compareTo(o.uuid);
    }

    public Map<ContactType, String> getContacts() {
        return contacts.clone();
    }

    public void setContact(ContactType type, String content) {
        contacts.put(type, content);
    }

    public Map<SectionType, AbstractSection> getSections() {
        return sections.clone();
    }

    public AbstractSection getSection(SectionType sectionType) {
        return sections.get(sectionType);
    }

    public void setSection(SectionType type, AbstractSection section) {
        sections.put(type, section);
    }
}
