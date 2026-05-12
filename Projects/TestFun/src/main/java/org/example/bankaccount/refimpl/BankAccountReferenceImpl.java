package org.example.bankaccount.refimpl;

import org.example.bankaccount.BankAccount;

public class BankAccountReferenceImpl implements BankAccount
{
    private long balance;

    public BankAccountReferenceImpl()
    {
        this.balance = 0L;
    }

    @Override
    public void deposit(long amount)
    {
        if (amount <= 0)
        {
            throw new IllegalArgumentException("Deposit amount must be positive.");
        }
        balance += amount;
    }

    @Override
    public void withdraw(long amount)
    {
        if (amount <= 0)
        {
            throw new IllegalArgumentException("Withdraw amount must be positive.");
        }
        if (amount > balance)
        {
            throw new IllegalStateException("Insufficient funds.");
        }
        balance -= amount;
    }

    @Override
    public long getBalance()
    {
        return balance;
    }
}
