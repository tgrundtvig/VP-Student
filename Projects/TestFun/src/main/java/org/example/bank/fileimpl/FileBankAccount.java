package org.example.bank.fileimpl;

import org.example.bankaccount.BankAccount;

class FileBankAccount implements BankAccount
{
    private final FileBank bank;
    private long balance;

    FileBankAccount(FileBank bank)
    {
        this(bank, 0L);
    }

    FileBankAccount(FileBank bank, long initialBalance)
    {
        this.bank = bank;
        this.balance = initialBalance;
    }

    @Override
    public void deposit(long amount)
    {
        if (amount <= 0)
        {
            throw new IllegalArgumentException("Deposit amount must be positive.");
        }
        balance += amount;
        bank.flush();
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
        bank.flush();
    }

    @Override
    public long getBalance()
    {
        return balance;
    }
}
