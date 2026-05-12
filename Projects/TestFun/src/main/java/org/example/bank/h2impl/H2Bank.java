package org.example.bank.h2impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.NoSuchElementException;
import java.util.UUID;

import org.example.bank.Bank;
import org.example.bankaccount.BankAccount;

public class H2Bank implements Bank
{
    private final Connection connection;
    private long nextNumber;

    public H2Bank()
    {
        this("jdbc:h2:mem:h2bank-" + UUID.randomUUID());
    }

    public H2Bank(String jdbcUrl)
    {
        try
        {
            this.connection = DriverManager.getConnection(jdbcUrl);
            try (Statement s = connection.createStatement())
            {
                s.execute("CREATE TABLE IF NOT EXISTS accounts (" +
                          "  number VARCHAR(64) PRIMARY KEY," +
                          "  balance BIGINT NOT NULL DEFAULT 0" +
                          ")");
            }
            this.nextNumber = loadNextNumber();
        }
        catch (SQLException e)
        {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String openAccount()
    {
        String number = String.valueOf(nextNumber++);
        try (PreparedStatement ps = connection.prepareStatement(
                "INSERT INTO accounts (number, balance) VALUES (?, 0)"))
        {
            ps.setString(1, number);
            ps.executeUpdate();
        }
        catch (SQLException e)
        {
            throw new RuntimeException(e);
        }
        return number;
    }

    @Override
    public BankAccount getAccount(String accountNumber)
    {
        try (PreparedStatement ps = connection.prepareStatement(
                "SELECT 1 FROM accounts WHERE number = ?"))
        {
            ps.setString(1, accountNumber);
            try (ResultSet rs = ps.executeQuery())
            {
                if (!rs.next())
                {
                    throw new NoSuchElementException("No account: " + accountNumber);
                }
            }
        }
        catch (SQLException e)
        {
            throw new RuntimeException(e);
        }
        return new H2BankAccount(connection, accountNumber);
    }

    private long loadNextNumber() throws SQLException
    {
        try (Statement s = connection.createStatement();
             ResultSet rs = s.executeQuery(
                "SELECT COALESCE(MAX(CAST(number AS BIGINT)), 0) FROM accounts"))
        {
            rs.next();
            return rs.getLong(1) + 1;
        }
    }
}
