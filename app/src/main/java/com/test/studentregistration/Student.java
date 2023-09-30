package com.test.studentregistration;

public class Student {
    private String name;
    private String email;
    private String dob;
    private String phone;

    public Student(String name, String email, String dob, String phone) {
        this.name = name;
        this.email = email;
        this.dob = dob;
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getDob() {
        return dob;
    }

    public String getPhone() {
        return phone;
    }
}
