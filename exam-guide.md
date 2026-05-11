# Exam Preparation Guide

> A single document that pulls together everything you need to know for the
> oral exam. Read this once a few weeks before the exam, then come back to
> it the week before to drill the things that feel shakiest.

**Hand-in deadline:** 27 May 2026
**Oral exam:** 9 June 2026

## Format Of The Exam

You bring your **project**. The examiner asks you about:
- The design decisions you made
- The interfaces you defined and why
- The data shapes (records) and where they live
- How testing fits in
- Where well-known patterns appear in your code

You should be able to **navigate your own code on a whiteboard** —
sketching the top-level interfaces from memory, naming the patterns, and
defending the choices.

## What You Should Be Able To Do

### 1. The Methodology (the core skill)

**Interface-first, top-down design** — the philosophy of the whole course.

You should be able to:
- Define it in one paragraph from memory.
- Apply it to a **brand-new problem** the examiner picks on the spot.
- Walk through the engine design (Game → GameLoop → Actor → Command) as
  the canonical example.
- Walk through `GuessANumber` as a second example, end-to-end.

**Key reference:** [Week 04 extra reading](Week04-WishfulProgramming/extra-reading.md).

### 2. Records vs Interfaces

The rule: **records for data, interfaces for behaviour**.

You should be able to:
- State the rule.
- Defend it with examples from our code (`Feedback`, `LocationCoordinate`,
  vs `TextUser`, `Guesser`, `Node`).
- Pick correctly between record and interface for a fresh case.
- Know when *neither* fits — when you need a regular class.

**Key reference:** [Week 06 extra reading](Week06-WiringItTogether/extra-reading.md).

### 3. Dependency Injection & Programming To An Interface

The pattern from `Player + TextIn` that the whole course pivots on.

You should be able to:
- Show the broken `Scanner`-hardcoded version, explain why it's broken.
- Show the `TextIn`-injected version, explain why it's better.
- Define **dependency injection** in one sentence.
- Define **programming to an interface, not an implementation** in one
  sentence.
- Recognise the pattern in any code the examiner shows you.

**Key reference:** [Week 02 post-class reading](Week02-DiscoveringInterfaces/post-class/reading.md).

### 4. The Patterns We Actually Used

You don't need all 23 GoF patterns. You need *these*, with our code as
your example:

| Pattern        | Our example                                            | Read it in |
|----------------|--------------------------------------------------------|------------|
| **Adapter**    | `TextAppUserImpl` adapts `TextUser` to `TextAppUser`   | [Week 03](Week03-ReusableTools/extra-reading.md) |
| **Command**    | `Command` + `CommandRegistry`; multilevel menu actions | [Week 04](Week04-WishfulProgramming/extra-reading.md) |
| **Builder**    | `SimpleLocationMapBuilder` with bidirectional wiring   | [Week 05](Week05-DesigningTheEngine/extra-reading.md) |
| **Factory**    | `PlayerFactory` / `PlayerFactoryImpl` in GuessANumber  | [Week 06](Week06-WiringItTogether/extra-reading.md) |
| **Composite**  | `Node` / `FileNode` / `Directory` in FileSystem        | [Week 09](Week09-GenericsAndComposite/extra-reading.md) |
| **Strategy**   | `Search` interface, `DepthFirstSearch` impl            | [Week 10](Week10-TreesAndRecursion/extra-reading.md) |

For each pattern be ready to:
- Define it in one sentence.
- Point at *our* concrete example.
- Sketch a fresh example on the whiteboard.
- Explain what problem it solves (without resorting to "it's just good
  design").

### 5. Data Structures From Scratch

You implemented a list both ways and walked a tree both ways.

You should be able to:
- Write `add`, `get`, `size` for `ArrayList` and `LinkedList` from memory.
- State and justify the **Big-O complexity** of every operation on both.
- Walk a tree depth-first (recursive *and* iterative) and breadth-first.
- Choose the right one given a usage pattern.

**Key references:** [Week 08](Week08-HomeMadeCollections/extra-reading.md),
[Week 09](Week09-GenericsAndComposite/extra-reading.md),
[Week 10](Week10-TreesAndRecursion/extra-reading.md).

### 6. Generics

You should be able to:
- Define a generic class on the whiteboard (`Box<T>` is enough).
- Explain **type erasure** and one consequence (e.g. why
  `GenericArrayList` has an `Object[]` field).
- Read a generic signature and explain what it means.

**Key reference:** [Week 09 extra reading](Week09-GenericsAndComposite/extra-reading.md).

### 7. Testing

You should be able to:
- Write a JUnit 5 test class from memory.
- Implement the **abstract test class pattern** for one of your
  interfaces.
- Explain why this pattern is the natural test strategy for
  interface-first design.
- Critique an AI-generated test class.

**Key reference:** [Week 13 extra reading](Week13-TestingInterfaces/extra-reading.md).

## Your Exam Project — Checklist

Before hand-in (27 May):

- [ ] Project compiles (`mvn clean compile` is green)
- [ ] Tests pass (`mvn test` is green)
- [ ] At least one **abstract test class** + concrete subclasses per
      implementation
- [ ] Top-level interfaces are clearly named and documented
- [ ] You can find at least 2 of the patterns from the table above
      in your own code, intentionally
- [ ] You can draw the top-level design on a whiteboard from memory
- [ ] You can run the project end-to-end and demo it
- [ ] All code, identifiers, and comments are in **English**
- [ ] README explains: what the project does, how to run it, what design
      decisions you made

Before the oral exam (9 June):

- [ ] Re-read this guide
- [ ] Re-read each Week's `README.md` and `extra-reading.md`
- [ ] Walk through GuessANumber and FileSystem out loud — practice
      explaining interface-first design with concrete examples
- [ ] Practice **drawing your own design** without looking at the code
- [ ] Be ready to answer: "Why an interface here and not a class?" for
      every interface in your project

## Common Examiner Questions (Likely)

- *"Walk me through your project's top-level design."*
  → Draw it on the whiteboard. Don't look at the code.
- *"Why is this an interface?"* (pointing at one)
  → Because we expect multiple implementations / we want to test it /
  it separates contract from behaviour.
- *"Why is this a record?"* (pointing at one)
  → It's pure data, no behaviour, immutable, compared by value.
- *"How would you test this interface?"*
  → Abstract test class with `createX()` factory, one subclass per impl.
- *"What design patterns can you point to in your code?"*
  → Walk through 2–3 from the table above, give the name + location +
  problem solved.
- *"What would change if we needed [some new feature]?"*
  → Identify which interface(s) would need to change, which existing
  code would NOT change, and why that's a sign of good design.

## If You Get Stuck

Three sentences worth memorising:

1. **"Let me think about what *uses* this code first."** — top-down.
2. **"What I want to call here is..."** — wishful programming.
3. **"That feels like the X pattern."** — name the shape.

If you can do those three moves under pressure, you've internalised the
methodology, which is what the course is testing.

Good luck.
