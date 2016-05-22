package com.raivis.develops.lpd_personigais_budzeta_planotais;

/*
 * Created by raivis on 18/05/2016.
 */
public class Outcome {
    private String name;
    private double amount;
    private String date;
    private String userEmail;

    public Outcome(String Name, Double Amount, String Date, String Email)
    {
        this.name = Name;
        this.amount = Amount;
        this.date = Date;
        this.userEmail = Email;
    }

    public String[] getOutcomeInfo()
    {
        return new String[]{this.name, Double.toString(this.amount), this.date, this.userEmail};
    }
}
