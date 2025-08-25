package br.com.mariojp.solid.lspaccounts;

public class Account {
    protected double balance;

    public void deposit(double amount){
        this.balance += amount;
    }

    public double getBalance(){
        return balance;
    }
}