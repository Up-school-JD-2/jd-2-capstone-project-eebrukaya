package io.upschool.enums;

public enum Gender {

    FEMALE("Kadın"),
    MALE("Erkek");

    private String gender;

    Gender(String gender) {
        this.gender = gender;
    }

    public String toString() {
        return gender;
    }

}
