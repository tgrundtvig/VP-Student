# Extra Reading: Testing Interfaces in Depth

> Companion to [Week 13's README](README.md). Read this once and come back to
> it as a reference when you write tests for your exam project.

## 1. JUnit 5 — The Bits You'll Actually Use

JUnit 5 is the standard testing framework for Java. You add it as a Maven
dependency (it's already in our projects) and write classes that *contain*
tests.

### The basic skeleton

```java
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CalculatorTest {

    @Test
    void addsTwoNumbers() {
        Calculator c = new Calculator();
        assertEquals(5, c.add(2, 3));
    }
}
```

Three rules:
- Class doesn't need to be `public`. Tests don't either.
- Method name should describe behaviour, not the method being tested.
  `addsTwoNumbers` beats `testAdd`.
- Use `assertEquals(expected, actual)` — expected first, then actual.

### Lifecycle annotations

```java
class CalculatorTest {
    Calculator c;

    @BeforeEach
    void setUp() {
        c = new Calculator();  // fresh instance per test
    }

    @AfterEach
    void tearDown() {
        // close files, kill threads, etc.
    }

    @Test void addsZero() { assertEquals(7, c.add(7, 0)); }
    @Test void addsNegative() { assertEquals(0, c.add(5, -5)); }
}
```

`@BeforeEach` runs before *each* `@Test`. There's also `@BeforeAll` /
`@AfterAll` for once-per-class setup (must be `static`).

### Assertions cheat sheet

| What you mean              | How to write it                                       |
|----------------------------|-------------------------------------------------------|
| Equal values               | `assertEquals(expected, actual)`                      |
| Not equal                  | `assertNotEquals(other, actual)`                      |
| True / false               | `assertTrue(condition)` / `assertFalse(condition)`    |
| Same object reference      | `assertSame(expected, actual)`                        |
| Null / non-null            | `assertNull(x)` / `assertNotNull(x)`                  |
| An exception was thrown    | `assertThrows(IOException.class, () -> doStuff())`    |
| Multiple checks at once    | `assertAll(() -> ..., () -> ..., () -> ...)`          |
| With a custom failure msg  | Add a `String` as the **last** argument               |
| Floating-point tolerance   | `assertEquals(3.14, x, 0.001)`                        |

Most tests only need `assertEquals` and `assertThrows`.

### Helpful extras

- **`@DisplayName("a more readable name")`** — what JUnit prints in reports.
- **`@Disabled("flaky — see ticket #42")`** — skip a test, with reason.
- **`@ParameterizedTest`** + `@ValueSource` / `@CsvSource` — run the same
  test with different inputs.

```java
@ParameterizedTest
@ValueSource(ints = {1, 2, 3, 5, 8, 13})
void allFibonacciNumbersAreFibonacci(int n) {
    assertTrue(Fibonacci.isFibonacci(n));
}
```

## 2. The Abstract Test Class Pattern — In Depth

### The problem it solves

You have an interface, `GenericList<T>`. You have two implementations,
`GenericArrayList<T>` and `GenericLinkedList<T>`. You want to assert that
**both** implementations satisfy the same contract — same behaviour, same
edge cases, same exceptions.

Naïve solution: copy-paste the test class, change `new GenericArrayList<>()`
to `new GenericLinkedList<>()`, run both. Eight tests become sixteen.
Fix a typo, fix it twice. Add a new test, add it twice. Disaster.

Better solution: **write the tests once, against the interface, and let each
subclass supply the implementation.**

### The pattern

```java
// 1. The contract — tests written against the INTERFACE.
abstract class GenericListContractTest {

    // Each subclass provides an empty list of the impl-under-test.
    protected abstract <T> GenericList<T> createEmpty();

    @Test
    void emptyListHasSizeZero() {
        GenericList<String> list = createEmpty();
        assertEquals(0, list.size());
    }

    @Test
    void addIncreasesSize() {
        GenericList<String> list = createEmpty();
        list.add("hello");
        assertEquals(1, list.size());
    }

    @Test
    void getOutOfRangeThrows() {
        GenericList<String> list = createEmpty();
        list.add("x");
        assertThrows(IndexOutOfBoundsException.class, () -> list.get(1));
    }

    @Test
    void containsFindsAddedElement() {
        GenericList<Integer> list = createEmpty();
        list.add(42);
        assertTrue(list.contains(42));
    }
}

// 2. One subclass per implementation. Each one is trivial.
class GenericArrayListTest extends GenericListContractTest {
    @Override
    protected <T> GenericList<T> createEmpty() {
        return new GenericArrayList<>();
    }
}

class GenericLinkedListTest extends GenericListContractTest {
    @Override
    protected <T> GenericList<T> createEmpty() {
        return new GenericLinkedList<>();
    }
}
```

Run the test suite. Each `@Test` method in the parent runs once *per
subclass* — so you get `2 × N` test results from `N` written tests.

### When you need implementation-specific tests

The contract test is for behaviour every implementation must satisfy. If
`GenericArrayList` has its own thing — a `capacity()` method, say — that
test lives in `GenericArrayListTest` directly, not in the contract.

```java
class GenericArrayListTest extends GenericListContractTest {
    @Override
    protected <T> GenericList<T> createEmpty() {
        return new GenericArrayList<>();
    }

    @Test
    void capacityGrowsWhenFull() {
        GenericArrayList<String> list = new GenericArrayList<>();
        // ... implementation-specific assertion
    }
}
```

The rule: **contract tests use the interface type, impl tests can use the
concrete type**.

### Why this is the natural fit for interface-first design

Look at how the pattern mirrors the design:

| Design layer            | Test layer                          |
|-------------------------|-------------------------------------|
| Interface `GenericList` | Abstract `GenericListContractTest`  |
| Impl `GenericArrayList` | `GenericArrayListTest extends ...`  |
| Impl `GenericLinkedList`| `GenericLinkedListTest extends ...` |

When you add a new implementation, you write one trivial subclass and **all
existing tests run against it automatically**. The cost of adding an
implementation drops to near zero.

This is the structural payoff of the whole course. Interface-first design
makes contract testing trivial. Without interfaces you'd be copy-pasting
test classes.

### Alternatives JUnit 5 also supports

- **`@TestFactory` + `DynamicTest`** — generate tests at runtime. More
  flexible than inheritance but less readable.
- **`@ParameterizedTest` with a `MethodSource` returning suppliers** —
  parameterise the implementation. Cleaner in some scenarios, weirder
  syntax.
- **Interface default methods on test interfaces** (JUnit 5 supports
  test methods on interfaces). Useful if you don't want to use inheritance.

For our course **stick with abstract class + subclasses** — it's the
clearest expression of the contract.

## 3. AI-Generated Tests — Trust But Verify

### Why use AI to write tests

Tests are pattern-y. For an interface with N methods you typically want:
- one happy-path test per method
- one or two edge-case tests per method (empty, full, null, boundary)
- tests for documented exceptions

An LLM can produce a plausible first draft of all of that in seconds. That
draft is **not** trustworthy by default — but it's a massive head start.

### A working prompt

> *I have the following Java interface. Write a complete JUnit 5 test class
> that tests its contract. Use an abstract test class with a `createEmpty()`
> factory method, so I can run the same tests against multiple
> implementations. Cover the happy path, edge cases (empty, single element,
> many elements), and documented exceptions. Use `assertEquals`,
> `assertTrue`, and `assertThrows` only. Don't write tests for methods that
> aren't in the interface.*
>
> ```java
> [paste interface here]
> ```

Adjust per project — but be explicit about:
- the assertion library (JUnit 5)
- the pattern (abstract test class)
- coverage expectations (happy / edge / exceptions)
- what *not* to do (don't test private methods, don't invent methods)

### How to review AI output

Walk down every test asking three questions:

1. **Does the test actually exercise the behaviour it claims to test?**
   AI sometimes writes a test named `addReturnsTrue` whose body does
   nothing but `assertNotNull(list)`. Read the asserts.

2. **Are the assertions correct?** AI sometimes inverts the contract:
   asserts `size() == 1` after two adds. Or uses `assertSame` where
   `assertEquals` is meant.

3. **Does the test rely on methods that don't exist?** AI hallucinates.
   Compile the file. If the test calls `list.headOf()` and your interface
   doesn't have `headOf`, delete it.

A test that compiles, passes, and asserts nothing useful is **worse** than
no test — it gives you false confidence.

### Smell checklist

- ✅ Tests that fail when you break the implementation (mutation-test mentally)
- ❌ Tests with no `assert*` call
- ❌ Tests that pass on an empty implementation
- ❌ Tests with the wrong `expected` value baked in
- ❌ Tests that use `Thread.sleep` (flaky)
- ❌ Tests with the comment `// generated by AI — do not modify` (audit it!)

### A pragmatic workflow

1. Hand-write **one** test per method first. Now you know what good looks
   like.
2. Ask AI for the rest, with your hand-written test as the style template.
3. Run them. Delete the broken ones, fix the salvageable ones.
4. Commit the surviving tests with a regular commit message — they're
   yours now.

## 4. For The Exam

You should be able to:
- Write a JUnit 5 test class on the whiteboard from memory.
- **Implement the abstract-test-class pattern** for one of your exam
  project's interfaces.
- Argue that contract tests are the natural test strategy for
  interface-first code.
- Critique an AI-generated test class — find at least one issue per typical
  draft.
- Run `mvn test` and explain the output.
