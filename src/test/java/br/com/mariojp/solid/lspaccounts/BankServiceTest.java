package br.com.mariojp.solid.lspaccounts;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class BankServiceTest {

    @Test
    void checking_account_allows_withdrawal() {
        var acc = new CheckingAccount();
        acc.deposit(100);
        new BankService().processWithdrawal(acc, 50);
        assertEquals(50, acc.getBalance());
    }

    @Test
    void savings_account_should_not_throw_and_should_not_withdraw() {
        var acc = new SavingsAccount();
        acc.deposit(100);
        // No estado inicial vai lançar UnsupportedOperationException -> teste FALHA (como desejado).
        assertDoesNotThrow(() -> new BankService().processWithdrawal(acc, 50),
                "Após refatoração, processWithdrawal não deve tentar sacar de poupança");
        assertEquals(100, acc.getBalance(), 0.0001,
                "Poupança não deve ter saldo reduzido em operação de saque");
    }
}