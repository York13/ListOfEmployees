package ru.york13.llistofemployees;

public class Person {

    private int id;
    private String name;
    private String surname;
    private String birthday;
    private String avatar;
    private int specialty_id;
    private String specialty;

    public Person(int id, String name, String surname, String birthday, String avatar, int specialty_id, String specialty) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.birthday = birthday;
        this.avatar = avatar;
        this.specialty_id = specialty_id;
        this.specialty = specialty;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public int getSpecialty_id() {
        return specialty_id;
    }

    public void setSpecialty_id(int specialty_id) {
        this.specialty_id = specialty_id;
    }

    public String getSpecialty() {
        return specialty;
    }

    public void setSpecialty(String specialty) {
        this.specialty = specialty;
    }
}
