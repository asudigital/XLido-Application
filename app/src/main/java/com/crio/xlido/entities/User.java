package com.crio.xlido.entities;

public class User {
    private  String email;
    private String password;
    private Long id;

    public User(Long id , User user) {
        this.id = id ;
        this.email = user.email;
        this.password = user.password;
    }
    public User(String email , String password){
       
        this.email = email;
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "User [email=" + email + ", password=" + password + "]";
    }

    
}
