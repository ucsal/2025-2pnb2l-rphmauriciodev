package br.com.mariojp.solid.lspaccounts;

public class SavingsAccount extends Account {
    @Override
    public void withdraw(double amount) {
        throw new UnsupportedOperationException("Saque não permitido em poupança");
    }
}