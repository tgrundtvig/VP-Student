package org.example.bank.fileimpl;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Properties;

import org.example.bank.Bank;
import org.example.bankaccount.BankAccount;

public class FileBank implements Bank
{
    private static final String ACCOUNT_PREFIX = "account.";
    private static final String NEXT_NUMBER_KEY = "nextNumber";

    private final Path file;
    private final Map<String, FileBankAccount> accounts = new HashMap<>();
    private long nextNumber = 1L;

    public FileBank()
    {
        this(createTempPath());
    }

    public FileBank(Path file)
    {
        this.file = file;
        load();
    }

    @Override
    public String openAccount()
    {
        String number = String.valueOf(nextNumber++);
        accounts.put(number, new FileBankAccount(this));
        flush();
        return number;
    }

    @Override
    public BankAccount getAccount(String accountNumber)
    {
        FileBankAccount account = accounts.get(accountNumber);
        if (account == null)
        {
            throw new NoSuchElementException("No account: " + accountNumber);
        }
        return account;
    }

    void flush()
    {
        Properties props = new Properties();
        props.setProperty(NEXT_NUMBER_KEY, String.valueOf(nextNumber));
        for (Map.Entry<String, FileBankAccount> e : accounts.entrySet())
        {
            props.setProperty(ACCOUNT_PREFIX + e.getKey(), String.valueOf(e.getValue().getBalance()));
        }
        try (BufferedWriter out = Files.newBufferedWriter(file))
        {
            props.store(out, "FileBank state");
        }
        catch (IOException e)
        {
            throw new UncheckedIOException(e);
        }
    }

    private void load()
    {
        if (!Files.exists(file) || isEmpty(file))
        {
            return;
        }
        Properties props = new Properties();
        try (BufferedReader in = Files.newBufferedReader(file))
        {
            props.load(in);
        }
        catch (IOException e)
        {
            throw new UncheckedIOException(e);
        }
        nextNumber = Long.parseLong(props.getProperty(NEXT_NUMBER_KEY, "1"));
        for (String key : props.stringPropertyNames())
        {
            if (key.startsWith(ACCOUNT_PREFIX))
            {
                String number = key.substring(ACCOUNT_PREFIX.length());
                long balance = Long.parseLong(props.getProperty(key));
                accounts.put(number, new FileBankAccount(this, balance));
            }
        }
    }

    private static boolean isEmpty(Path file)
    {
        try
        {
            return Files.size(file) == 0;
        }
        catch (IOException e)
        {
            throw new UncheckedIOException(e);
        }
    }

    private static Path createTempPath()
    {
        try
        {
            Path path = Files.createTempFile("filebank-", ".properties");
            path.toFile().deleteOnExit();
            return path;
        }
        catch (IOException e)
        {
            throw new UncheckedIOException(e);
        }
    }
}
