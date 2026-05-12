package org.example.bank;

import java.util.function.Supplier;
import java.util.stream.Stream;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import org.example.bank.fileimpl.FileBank;
import org.example.bank.h2impl.H2Bank;
import org.example.bank.hashmapimpl.HashMapBank;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Named.named;
import static org.junit.jupiter.params.provider.Arguments.arguments;

class TransfersTest
{
    static Stream<Arguments> bankPairs()
    {
        Supplier<Bank> mem  = HashMapBank::new;
        Supplier<Bank> file = FileBank::new;
        Supplier<Bank> h2   = H2Bank::new;
        return Stream.of(
            arguments(named("mem",  mem),  named("mem",  mem)),
            arguments(named("mem",  mem),  named("file", file)),
            arguments(named("mem",  mem),  named("h2",   h2)),
            arguments(named("file", file), named("mem",  mem)),
            arguments(named("file", file), named("h2",   h2)),
            arguments(named("h2",   h2),   named("file", file)),
            arguments(named("h2",   h2),   named("h2",   h2))
        );
    }

    @ParameterizedTest(name = "{0} -> {1}")
    @MethodSource("bankPairs")
    void moneyMovesBetweenBanks(Supplier<Bank> srcFactory, Supplier<Bank> destFactory)
    {
        Bank src = srcFactory.get();
        Bank dest = destFactory.get();
        String numA = src.openAccount();
        String numB = dest.openAccount();
        src.getAccount(numA).deposit(1_000L);

        src.getAccount(numA).transferTo(dest.getAccount(numB), 300L);

        assertEquals(700L, src.getAccount(numA).getBalance());
        assertEquals(300L, dest.getAccount(numB).getBalance());
    }

    @ParameterizedTest(name = "{0} -> {1}")
    @MethodSource("bankPairs")
    void insufficientFundsLeavesBothAccountsUnchanged(Supplier<Bank> srcFactory, Supplier<Bank> destFactory)
    {
        Bank src = srcFactory.get();
        Bank dest = destFactory.get();
        String numA = src.openAccount();
        String numB = dest.openAccount();
        src.getAccount(numA).deposit(100L);

        assertThrows(IllegalStateException.class,
                     () -> src.getAccount(numA).transferTo(dest.getAccount(numB), 200L));

        assertEquals(100L, src.getAccount(numA).getBalance());
        assertEquals(0L, dest.getAccount(numB).getBalance());
    }

    @ParameterizedTest(name = "{0} -> {1}")
    @MethodSource("bankPairs")
    void canTransferBack(Supplier<Bank> srcFactory, Supplier<Bank> destFactory)
    {
        Bank src = srcFactory.get();
        Bank dest = destFactory.get();
        String numA = src.openAccount();
        String numB = dest.openAccount();
        src.getAccount(numA).deposit(1_000L);
        src.getAccount(numA).transferTo(dest.getAccount(numB), 300L);

        dest.getAccount(numB).transferTo(src.getAccount(numA), 100L);

        assertEquals(800L, src.getAccount(numA).getBalance());
        assertEquals(200L, dest.getAccount(numB).getBalance());
    }
}
