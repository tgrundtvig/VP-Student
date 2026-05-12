package org.example.bank;

import java.util.NoSuchElementException;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import org.example.bankaccount.BankAccount;
import org.example.bankaccount.BankAccountTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public abstract class BankTest
{
    protected abstract Bank createBank();

    @Test
    void openAccountReturnsDifferentNumbersOnRepeatedCalls()
    {
        Bank bank = createBank();
        String a = bank.openAccount();
        String b = bank.openAccount();
        assertNotEquals(a, b);
    }

    @Test
    void newlyOpenedAccountHasZeroBalance()
    {
        Bank bank = createBank();
        BankAccount account = bank.getAccount(bank.openAccount());
        assertEquals(0L, account.getBalance());
    }

    @Test
    void getAccountThrowsForUnknownNumber()
    {
        Bank bank = createBank();
        assertThrows(NoSuchElementException.class, () -> bank.getAccount("does-not-exist"));
    }

    @Test
    void getAccountReturnsTheSameAccountState()
    {
        Bank bank = createBank();
        String number = bank.openAccount();
        bank.getAccount(number).deposit(100L);
        assertEquals(100L, bank.getAccount(number).getBalance());
    }

    @Test
    void differentAccountsInSameBankAreIndependent()
    {
        Bank bank = createBank();
        String a = bank.openAccount();
        String b = bank.openAccount();
        bank.getAccount(a).deposit(100L);
        bank.getAccount(b).deposit(200L);
        assertEquals(100L, bank.getAccount(a).getBalance());
        assertEquals(200L, bank.getAccount(b).getBalance());
    }

    @Nested
    @DisplayName("Accounts produced by this bank")
    class ProducedAccounts extends BankAccountTest
    {
        @Override
        protected BankAccount createBankAccount()
        {
            Bank bank = createBank();
            return bank.getAccount(bank.openAccount());
        }
    }
}
