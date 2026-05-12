package org.example.bank.hashmapimpl;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

import org.example.bank.Bank;
import org.example.bankaccount.BankAccount;
import org.example.bankaccount.refimpl.BankAccountReferenceImpl;

public class HashMapBank implements Bank
{
    private final Map<String, BankAccount> accounts = new HashMap<>();
    private long nextNumber = 1L;

    @Override
    public String openAccount()
    {
        String number = String.valueOf(nextNumber++);
        accounts.put(number, new BankAccountReferenceImpl());
        return number;
    }

    @Override
    public BankAccount getAccount(String accountNumber)
    {
        BankAccount account = accounts.get(accountNumber);
        if (account == null)
        {
            throw new NoSuchElementException("No account: " + accountNumber);
        }
        return account;
    }
}
