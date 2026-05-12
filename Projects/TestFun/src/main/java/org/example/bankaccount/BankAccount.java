package org.example.bankaccount;

public interface BankAccount
{
    void deposit(long amount);

    void withdraw(long amount);

    long getBalance();

    default void transferTo(BankAccount other, long amount)
    {
        this.withdraw(amount);
        other.deposit(amount);
    }
}
