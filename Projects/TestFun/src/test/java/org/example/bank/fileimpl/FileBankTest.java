package org.example.bank.fileimpl;

import org.example.bank.Bank;
import org.example.bank.BankTest;

public class FileBankTest extends BankTest
{
    @Override
    protected Bank createBank()
    {
        return new FileBank();
    }
}
