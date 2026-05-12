package org.example.bankaccount.refimpl;

import org.example.bankaccount.BankAccount;
import org.example.bankaccount.BankAccountTest;

public class BankAccountReferenceImplTest extends BankAccountTest
{
    @Override
    protected BankAccount createBankAccount()
    {
        return new BankAccountReferenceImpl();
    }
}
