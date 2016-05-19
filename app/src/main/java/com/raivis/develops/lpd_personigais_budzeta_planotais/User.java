package com.raivis.develops.lpd_personigais_budzeta_planotais;

import java.text.SimpleDateFormat;

/**
 * Created by raivis on 18/05/2016.
 */
//This class will represent users
public class User {
    private String firstName;
    private String lastName;
    private String birthDay;
    private String eMail;
    private String password;

    //More advance constructor - all fields for registring new user obligatory
    public User(String fName, String lName, String bDay, String email, String pass)
    {
        this.firstName = fName;
        this.lastName = lName;
        this.birthDay = bDay;
        this.eMail = email;
        this.password = pass;
    }

    public String[] getUserInfo()
    {
        return new String[]{this.firstName,this.lastName,
                this.birthDay, this.eMail, this.password};
    }
}
