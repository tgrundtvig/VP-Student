# Week 13: Testing Interfaces — JUnit, Abstract Test Classes, AI-Generated Tests

**Date:** 12 May 2026 *(planned)*

## What We Will Do In Class

The whole course has been about programming to interfaces. This week we
finally close the loop and **test those interfaces**.

The three topics for the evening:

### 1. JUnit basics

A quick tour of JUnit 5: `@Test`, `@BeforeEach`, `@AfterEach`,
`assertEquals`, `assertThrows`, `assertAll`. We've used these in the
`Projects/TextAdventure/` test suite — now we'll write some from scratch.

### 2. The Abstract Test Class Pattern

The real reason interfaces are testable: **you write the test once, against
the interface, and run it against every implementation.**

The pattern in shape:

```java
abstract class GenericListContractTest {
    abstract <T> GenericList<T> createEmpty();   // each subclass picks an impl

    @Test
    void emptyListHasSizeZero() {
        GenericList<String> list = createEmpty();
        assertEquals(0, list.size());
    }

    @Test
    void addThenGetReturnsSameValue() {
        GenericList<String> list = createEmpty();
        list.add("hello");
        assertEquals("hello", list.get(0));
    }

    // ... more contract tests
}

class GenericArrayListTest extends GenericListContractTest {
    @Override <T> GenericList<T> createEmpty() { return new GenericArrayList<>(); }
}

class GenericLinkedListTest extends GenericListContractTest {
    @Override <T> GenericList<T> createEmpty() { return new GenericLinkedList<>(); }
}
```

Two concrete subclasses, one shared contract. Every test runs against every
implementation. If `GenericLinkedList` doesn't satisfy the same contract as
`GenericArrayList`, JUnit shouts at you. This is **the** way to enforce that
your implementations all keep the same promise.

### 3. AI-Generated Tests

Modern AI tools (Claude, Copilot, etc.) are very good at writing test cases
for a given interface. We'll look at:
- How to **prompt** for tests (paste the interface, ask for contract tests)
- How to **review** generated tests — they can hallucinate methods, miss
  edge cases, or assert the wrong thing
- The "trust but verify" workflow: AI writes, you read every assertion,
  you run them, you fix or delete the broken ones

The point isn't to outsource testing. It's to use the AI as a fast first
draft and then *understand* what's there.

### Going further — patterns we used in TestFun

Three extensions to the abstract-test-class pattern that came out of the
in-class session, all visible in `Projects/TestFun/`:

**`@Nested` to group tests.** Inner classes annotated `@Nested` group
related tests into a tree. `BankAccountTest` uses `Construction`,
`Deposit`, `Withdraw`, `Transfer`, and `Scenarios` — the IntelliJ runner
shows each as its own folder.

**`@Nested` + inheritance to *compose* contract tests across layers.**
`BankTest` has bank-level tests of its own *and* a `@Nested` inner class
that extends `BankAccountTest`:

```java
public abstract class BankTest {
    protected abstract Bank createBank();

    @Test void openAccountReturnsDifferentNumbersOnRepeatedCalls() { ... }
    // ...other bank-level tests

    @Nested
    @DisplayName("Accounts produced by this bank")
    class ProducedAccounts extends BankAccountTest {
        @Override
        protected BankAccount createBankAccount() {
            Bank bank = createBank();
            return bank.getAccount(bank.openAccount());
        }
    }
}
```

Every `Bank` implementation that extends `BankTest` automatically runs the
**full `BankAccount` contract** against the accounts it produces. The
HashMap, file, and H2 banks each inherit ~24 contract tests for free.
Layered interfaces produce layered tests.

**`@ParameterizedTest` for cross-impl matrix testing.** `TransfersTest`
enumerates pairs of bank implementations and runs the same scenario
against each pair:

```java
@ParameterizedTest(name = "{0} -> {1}")
@MethodSource("bankPairs")
void moneyMovesBetweenBanks(Supplier<Bank> src, Supplier<Bank> dest) { ... }
```

Three transfer scenarios × seven bank pairs = 21 test runs from three
methods. Each pair shows up as its own row in the test runner.

## Code From This Session

The full project lives at `Projects/TestFun/`:

- `BankAccount` interface with `default transferTo(...)`
- `BankAccountReferenceImpl` — in-memory reference implementation
- `Bank` interface — open accounts, retrieve by number
- Three `Bank` implementations: `HashMapBank`, `FileBank` (Java
  `Properties` file), `H2Bank` (in-memory H2 via JDBC)
- Abstract `BankAccountTest` with `@Nested` groups
- Abstract `BankTest` with `@Nested ProducedAccounts extends BankAccountTest`
- `TransfersTest` — `@ParameterizedTest` matrix across cross-impl bank pairs

`mvn test` (under JDK ≥ 25) runs **132 tests**.

## Material

- **[Extra reading: Testing Interfaces in Depth](extra-reading.md)** —
  the abstract-test-class pattern, JUnit 5 cheat sheet, prompts for AI tests

## For The Exam

Be able to:
- Write a JUnit 5 test class from memory with at least 3 different assertions.
- Explain and **implement** the abstract-test-class pattern: contract test
  in a parent class, concrete subclasses that supply implementations.
- Argue *why* this pattern is the natural fit for interface-first design.
- Critically review an AI-generated test: spot missing edge cases, wrong
  assertions, or tests that don't actually test what they claim.
- Apply the pattern to your own exam project: write a contract test for at
  least one of your interfaces and run it against each implementation.
- Use `@Nested` to organize a long test class into logical groups.
- Apply the **layered contract test** pattern: a `@Nested` inner class
  inside one contract test that extends another (e.g.,
  `BankTest.ProducedAccounts extends BankAccountTest`) when one interface
  produces instances of another.
- Read a `@ParameterizedTest` with `@MethodSource` and explain how it
  scales testing across multiple implementation combinations.
