# LSP — Contas Bancárias (com testes que **falham** no estado inicial)

**Estado inicial (violando LSP):**  
`SavingsAccount` herda de `Account`, mas sobrescreve `withdraw()` lançando exceção (não permite saque).  
`BankService.processWithdrawal(Account,double)` chama `withdraw()` indiscriminadamente.  
Isso quebra o **Princípio da Substituição de Liskov (LSP)**: clientes que tratam tudo como `Account` quebram com `SavingsAccount`.

## Objetivo
Refatore para que o contrato seja respeitado **sem exceções em fluxo normal**:
- Somente contas que suportam saque devem ser usadas para saque.
- O saque **não** deve ser tentado em contas de depósito apenas.

### Caminhos possíveis (você escolhe)
- Introduzir `interface Withdrawable { void withdraw(double amount); }` e fazer `CheckingAccount` implementá-la.  
  `SavingsAccount` **não** implementa.  
  `BankService` deixa de aceitar `Account` e passa a aceitar `Withdrawable` para saques, ou faz uma checagem segura e ignora `SavingsAccount`.
- Ou separar hierarquia (`WithdrawableAccount` vs `DepositOnlyAccount`).

## Critérios (testes)
- `CheckingAccount` permite saque e atualiza o saldo.
- `SavingsAccount` **não** deve causar exceção em `processWithdrawal` e **não** deve ter saldo reduzido.
- Os testes **falham** no estado inicial e ficam **verdes** após a refatoração.

## Como rodar
```bash
mvn -q test
```

## Execução manual
Há uma classe `Main` (sem pacote) que executa uma simulação.  
No estado inicial, ela provocará uma **exceção em tempo de execução** ao tentar sacar da poupança.  
Após refatorar, ajuste a `Main` para não explodir (ex.: usando `Withdrawable`).