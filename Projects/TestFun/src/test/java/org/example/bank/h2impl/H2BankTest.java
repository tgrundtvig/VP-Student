package org.example.bank.h2impl;

import org.example.bank.Bank;
import org.example.bank.BankTest;

public class H2BankTest extends BankTest
{
    @Override
    protected Bank createBank()
    {
        return new H2Bank();
    }
}
