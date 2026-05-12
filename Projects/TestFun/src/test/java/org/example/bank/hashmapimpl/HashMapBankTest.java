package org.example.bank.hashmapimpl;

import org.example.bank.Bank;
import org.example.bank.BankTest;

public class HashMapBankTest extends BankTest
{
    @Override
    protected Bank createBank()
    {
        return new HashMapBank();
    }
}
