package br.com.mariojp.solid.lspaccounts;

public class CheckingAccount extends Account {
    @Override
    public void withdraw(double amount) {
        if(balance < amount) throw new IllegalArgumentException("Saldo insuficiente");
        super.withdraw(amount);
    }
}