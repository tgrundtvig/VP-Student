package org.example.bankaccount;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public abstract class BankAccountTest
{
    protected abstract BankAccount createBankAccount();

    @Nested
    @DisplayName("Construction")
    class Construction
    {
        @Test
        void newAccountStartsAtZero()
        {
            BankAccount account = createBankAccount();
            assertEquals(0L, account.getBalance());
        }
    }

    @Nested
    @DisplayName("Deposit")
    class Deposit
    {
        @Test
        void depositIncreasesBalance()
        {
            BankAccount account = createBankAccount();
            account.deposit(500L);
            assertEquals(500L, account.getBalance());
        }

        @Test
        void multipleDepositsAccumulate()
        {
            BankAccount account = createBankAccount();
            account.deposit(100L);
            account.deposit(200L);
            account.deposit(50L);
            assertEquals(350L, account.getBalance());
        }

        @Test
        void smallestValidDepositIsOne()
        {
            BankAccount account = createBankAccount();
            account.deposit(1L);
            assertEquals(1L, account.getBalance());
        }

        @Test
        void largeDepositWorks()
        {
            BankAccount account = createBankAccount();
            account.deposit(Long.MAX_VALUE);
            assertEquals(Long.MAX_VALUE, account.getBalance());
        }

        @Test
        void zeroDepositThrows()
        {
            BankAccount account = createBankAccount();
            assertThrows(IllegalArgumentException.class, () -> account.deposit(0L));
        }

        @Test
        void negativeDepositThrows()
        {
            BankAccount account = createBankAccount();
            assertThrows(IllegalArgumentException.class, () -> account.deposit(-100L));
        }

        @Test
        void failedDepositDoesNotChangeBalance()
        {
            BankAccount account = createBankAccount();
            account.deposit(500L);
            assertThrows(IllegalArgumentException.class, () -> account.deposit(-100L));
            assertEquals(500L, account.getBalance());
        }
    }

    @Nested
    @DisplayName("Withdraw")
    class Withdraw
    {
        @Test
        void withdrawDecreasesBalance()
        {
            BankAccount account = createBankAccount();
            account.deposit(1_000L);
            account.withdraw(300L);
            assertEquals(700L, account.getBalance());
        }

        @Test
        void canWithdrawExactBalance()
        {
            BankAccount account = createBankAccount();
            account.deposit(500L);
            account.withdraw(500L);
            assertEquals(0L, account.getBalance());
        }

        @Test
        void withdrawingOneMoreThanBalanceThrows()
        {
            BankAccount account = createBankAccount();
            account.deposit(1_000L);
            assertThrows(IllegalStateException.class, () -> account.withdraw(1_001L));
        }

        @Test
        void cannotWithdrawAnythingFromEmptyAccount()
        {
            BankAccount account = createBankAccount();
            assertThrows(IllegalStateException.class, () -> account.withdraw(1L));
        }

        @Test
        void zeroWithdrawThrows()
        {
            BankAccount account = createBankAccount();
            account.deposit(1_000L);
            assertThrows(IllegalArgumentException.class, () -> account.withdraw(0L));
        }

        @Test
        void negativeWithdrawThrows()
        {
            BankAccount account = createBankAccount();
            account.deposit(1_000L);
            assertThrows(IllegalArgumentException.class, () -> account.withdraw(-100L));
        }

        @Test
        void negativeWithdrawIsArgumentErrorNotStateError()
        {
            BankAccount account = createBankAccount();
            assertThrows(IllegalArgumentException.class, () -> account.withdraw(-100L));
        }

        @Test
        void failedWithdrawDoesNotChangeBalance()
        {
            BankAccount account = createBankAccount();
            account.deposit(500L);
            assertThrows(IllegalStateException.class, () -> account.withdraw(1_000L));
            assertEquals(500L, account.getBalance());
        }
    }

    @Nested
    @DisplayName("Transfer")
    class Transfer
    {
        @Test
        void transferMovesMoneyToDestination()
        {
            BankAccount src = createBankAccount();
            BankAccount dest = createBankAccount();
            src.deposit(1_000L);
            src.transferTo(dest, 300L);
            assertEquals(700L, src.getBalance());
            assertEquals(300L, dest.getBalance());
        }

        @Test
        void selfTransferLeavesBalanceUnchanged()
        {
            BankAccount account = createBankAccount();
            account.deposit(500L);
            account.transferTo(account, 100L);
            assertEquals(500L, account.getBalance());
        }

        @Test
        void insufficientFundsLeavesBothAccountsUnchanged()
        {
            BankAccount src = createBankAccount();
            BankAccount dest = createBankAccount();
            src.deposit(100L);
            assertThrows(IllegalStateException.class, () -> src.transferTo(dest, 200L));
            assertEquals(100L, src.getBalance());
            assertEquals(0L, dest.getBalance());
        }

        @Test
        void zeroAmountThrows()
        {
            BankAccount src = createBankAccount();
            BankAccount dest = createBankAccount();
            src.deposit(100L);
            assertThrows(IllegalArgumentException.class, () -> src.transferTo(dest, 0L));
        }

        @Test
        void negativeAmountThrows()
        {
            BankAccount src = createBankAccount();
            BankAccount dest = createBankAccount();
            src.deposit(100L);
            assertThrows(IllegalArgumentException.class, () -> src.transferTo(dest, -10L));
        }
    }

    @Nested
    @DisplayName("Mixed scenarios")
    class Scenarios
    {
        @Test
        void depositThenWithdrawLeavesCorrectBalance()
        {
            BankAccount account = createBankAccount();
            account.deposit(1_000L);
            account.withdraw(300L);
            assertEquals(700L, account.getBalance());
        }

        @Test
        void getBalanceIsReadOnly()
        {
            BankAccount account = createBankAccount();
            account.deposit(500L);
            account.getBalance();
            account.getBalance();
            assertEquals(500L, account.getBalance());
        }

        @Test
        void twoAccountsAreIndependent()
        {
            BankAccount a = createBankAccount();
            BankAccount b = createBankAccount();
            a.deposit(1_000L);
            b.deposit(500L);
            a.deposit(100L);
            b.withdraw(200L);
            assertEquals(1_100L, a.getBalance());
            assertEquals(300L, b.getBalance());
        }
    }
}
