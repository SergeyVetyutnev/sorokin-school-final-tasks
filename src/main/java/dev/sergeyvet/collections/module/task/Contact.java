package dev.sergeyvet.collections.module.task;

import java.util.Objects;

public class Contact {
    private String name;
    private String phone;
    private String email;
    private String group;

    public Contact(String name, String phone, String email, String group) {
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.group = group;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj ) return true;

        if (obj == null || getClass() != obj.getClass()) return false;

        Contact contact = (Contact) obj;
        return Objects.equals(phone, contact.phone);
    }

    @Override
    public int hashCode() {
        return Objects.hash(phone, email);
    }

    @Override
    public String toString() {
        return name + " | " + phone + " | " + email + " | " + group;
    }

    public String getPhone() {
        return phone;
    }

    public String getGroup() {
        return group;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }
}
