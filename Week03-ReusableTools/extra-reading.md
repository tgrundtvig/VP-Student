# Extra Reading: Adapter & Composition Over Inheritance

> Companion to [Week 03's README](README.md).

## The Adapter Pattern

An **adapter** wraps an object of one type and exposes it as another type.
Our example:

```java
class TextAppUserImpl implements TextAppUser {
    private final TextUser underlying;

    TextAppUserImpl(TextUser underlying) {
        this.underlying = underlying;
    }

    @Override
    public int readInt(String prompt) {
        underlying.print(prompt);
        String line = underlying.readLine();
        return Integer.parseInt(line);   // simplified
    }

    @Override
    public int chooseFromMenu(String title, List<String> options) {
        underlying.println(title);
        for (int i = 0; i < options.size(); i++) {
            underlying.println((i + 1) + ". " + options.get(i));
        }
        return readInt("Choice: ");
    }
}
```

What's happening:
- `TextAppUserImpl` doesn't know how to read or print on its own.
- It's *given* a `TextUser` (via the constructor — that's dependency injection
  from Week 2).
- It uses that `TextUser` to build higher-level operations like menus.

A different `TextUser` (terminal, scripted, web) — `TextAppUserImpl` doesn't
care. The adapter shape lets one set of high-level tools work on top of any
low-level I/O.

### Where else you see Adapter

- `InputStreamReader` adapts a byte-`InputStream` to a char-`Reader`.
- `Collections.list(enumeration)` adapts a legacy `Enumeration` to a `List`.
- Any class whose constructor takes the thing it adapts and whose methods
  delegate to it.

## Composition Over Inheritance

`TextAppUserImpl` *has a* `TextUser`. It does **not** *extend* a `TextUser`.

Why does that matter?

### Inheritance approach (don't do this)

```java
class TextAppUser extends TerminalUser {     // BAD
    public int readInt(String prompt) {
        print(prompt);
        return Integer.parseInt(readLine());
    }
}
```

Problems:
1. You're now locked to `TerminalUser` forever. Want a scripted version of
   `TextAppUser`? You have to write another subclass.
2. You inherit *everything* `TerminalUser` does — including methods you
   didn't want to expose.
3. Changes to `TerminalUser` ripple into `TextAppUser` whether you like
   it or not.

### Composition approach (what we did)

```java
class TextAppUserImpl implements TextAppUser {
    private final TextUser underlying;
    // ...
}
```

Benefits:
1. Works with **any** `TextUser`. Want a scripted `TextAppUser`? Pass it a
   scripted `TextUser`.
2. You only expose what `TextAppUser` declares — nothing leaks from below.
3. Changes to `TerminalUser` don't affect `TextAppUserImpl` unless they
   change the `TextUser` contract.

### The rule

> **Favour composition over inheritance.** Use inheritance only when you
> have a genuine *is-a* relationship and need polymorphism. For everything
> else, *has-a* (composition) is more flexible and safer.

Effective Java's Item 18 makes the full case if you want to read further.

## For The Exam

- Define "adapter" in one sentence.
- Identify the adapter in `TextAppUserImpl`.
- Explain "composition over inheritance" with our `TextUser` /
  `TextAppUserImpl` example.
- Sketch what would happen if you tried to do it with inheritance instead,
  and why that's worse.
