package org.example.bank.h2impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.example.bankaccount.BankAccount;

class H2BankAccount implements BankAccount
{
    private final Connection connection;
    private final String number;

    H2BankAccount(Connection connection, String number)
    {
        this.connection = connection;
        this.number = number;
    }

    @Override
    public void deposit(long amount)
    {
        if (amount <= 0)
        {
            throw new IllegalArgumentException("Deposit amount must be positive.");
        }
        applyDelta(amount);
    }

    @Override
    public void withdraw(long amount)
    {
        if (amount <= 0)
        {
            throw new IllegalArgumentException("Withdraw amount must be positive.");
        }
        if (amount > getBalance())
        {
            throw new IllegalStateException("Insufficient funds.");
        }
        applyDelta(-amount);
    }

    @Override
    public long getBalance()
    {
        try (PreparedStatement ps = connection.prepareStatement(
                "SELECT balance FROM accounts WHERE number = ?"))
        {
            ps.setString(1, number);
            try (ResultSet rs = ps.executeQuery())
            {
                rs.next();
                return rs.getLong(1);
            }
        }
        catch (SQLException e)
        {
            throw new RuntimeException(e);
        }
    }

    private void applyDelta(long delta)
    {
        try (PreparedStatement ps = connection.prepareStatement(
                "UPDATE accounts SET balance = balance + ? WHERE number = ?"))
        {
            ps.setLong(1, delta);
            ps.setString(2, number);
            ps.executeUpdate();
        }
        catch (SQLException e)
        {
            throw new RuntimeException(e);
        }
    }
}
