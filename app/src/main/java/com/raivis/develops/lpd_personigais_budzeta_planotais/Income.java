package com.raivis.develops.lpd_personigais_budzeta_planotais;

/*
 * Created by raivis on 19/05/2016.
 */
public class Income {

    private String incomeName;
    private Double incomeAmount;
    private String incomeDate;
    private String incomeSource;
    private String incomeUser;

    public Income(String name, Double amount, String date, String source, String user)
    {
        this.incomeName = name;
        this.incomeAmount = amount;
        this.incomeDate = date;
        this.incomeSource = source;
        this.incomeUser = user;
    }

    public String[] getIncomeInfo()
    {
        return new String[]{this.incomeName, Double.toString(this.incomeAmount),
        this.incomeDate, this.incomeSource, this.incomeUser};
    }

}
