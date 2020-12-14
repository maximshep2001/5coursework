package com.company.studio.behavior;

public final class User {
    private String name;
    private String surname;
    private String phone;
    private String email;
    private String password;
    private String repass;
    private com.company.studio.behavior.Role role;

    public User(String phone, String password){
        this.phone = phone;
        this.password = password;
    }

    public User(String name, String surname, String phone, String email, String password, String repass) {
        this.surname = name;
        this.name = surname;
        this.phone = phone;
        this.email = email;
        this.password = password;
        this.repass = repass;
    }

    public User(String name, String surname, String phone, String email, String password, com.company.studio.behavior.Role role){
        this.name = name;
        this.surname = surname;
        this.phone = phone;
        this.email = email;
        this.password = password;
        this.role = role;
    }

    @Override
    public String toString() {
        return name + ' ' + surname + ' ' + phone + ' ' + email + ' ' + password + ' ' + role;
    }
}
