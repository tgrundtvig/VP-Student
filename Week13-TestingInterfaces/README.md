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

## Code From This Session

To be added after class. Will live in
`Projects/HomeMadeCollections/src/test/java/...`

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
