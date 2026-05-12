package org.example.bank;

import java.util.NoSuchElementException;

import org.example.bankaccount.BankAccount;

public interface Bank
{
    String openAccount();

    /**
     * @throws NoSuchElementException if no account exists with the given number
     */
    BankAccount getAccount(String accountNumber);
}
