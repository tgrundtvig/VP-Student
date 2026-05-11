# Week 07: Guess-a-Number — Interface-First in Small Scope

**Date:** 24 March 2026

> Folder name is "BringingTheGameToLife" because that was the *planned* topic.
> With only 3 students in class we pivoted to a smaller, more focused exercise.
> Pre-class material in this folder reflects the original wiring plan and is
> still useful reference for the exam projects — but **what we actually did in
> class was build a guess-a-number game**.

## What We Did In Class

With a small group present we did the methodology of the whole course
in miniature, on a fresh problem domain: a number-guessing game.

What we designed (interface-first, top-down):
- **`GuessANumberGame`** — the top-level game
- **`Guesser`** — anything that produces guesses
- **`Thinker`** — anything that thinks of a number and rates guesses
- **`Feedback`** — record returned per guess (higher / lower / correct)
- **`PlayerFactory`** — factory for making `Guesser`/`Thinker` pairs

What we implemented:
- **`HumanGuesser`**, **`HumanThinker`** — driven via `TextAppUser`
- **`ComputerGuesser`**, **`ComputerThinker`** — automatic binary-search
  guesser, random number thinker
- **`GuessANumberGameImpl`** — runs the game loop
- **`PlayerFactoryImpl`** — produces the right combination

Then we played human-vs-computer, computer-vs-human, computer-vs-computer
to show that *the game loop doesn't care* which combination it gets.

That is the punchline of interface-first design. Once the interfaces are
right, the implementations become interchangeable.

## Code From This Session

📂 [`Projects/GuessANumber/`](../Projects/GuessANumber/) — the whole project

Key files:
- `Guesser.java`, `Thinker.java`, `Feedback.java`, `PlayerFactory.java`,
  `GuessANumberGame.java` — interfaces
- `impl/HumanGuesser.java`, `impl/HumanThinker.java`,
  `impl/ComputerGuesser.java`, `impl/ComputerThinker.java` — implementations
- `impl/GuessANumberGameImpl.java`, `impl/PlayerFactoryImpl.java`
- `RunDemo.java` — main entry point
- The shared `user/` package: `TextUser`, `TextAppUser`, `TerminalUser`,
  `TextAppUserImpl` (reused from Week 03's design)

## Material — Original Wiring Plan (still useful)

These describe the *planned* "bring the text adventure to life" content. We
deferred that to later sessions, but the reading is still valuable.

- **[Pre-class reading: From Design to Implementation](pre-class/reading.md)**
- **[Pre-class: Implementation Inventory](pre-class/inventory.md)**
- **[Pre-class: Mapping Dependencies](pre-class/dependencies.md)**
- **[Pre-class: Planning the First Playable](pre-class/first-playable.md)**

## Material — What We Actually Built

- **[Post-class: Guess-a-Number exercise](post-class/)** — extend the project
- **[Extra reading: Interface-First on a New Domain](extra-reading.md)**

## For The Exam

Be able to:
- Walk through the **`GuessANumber` interface design** and explain *why*
  splitting into `Guesser` + `Thinker` + `Feedback` is better than one
  `Game` class with everything inside.
- Show that **the same game loop runs all four combinations** (H-vs-H,
  H-vs-C, C-vs-H, C-vs-C) without changing `GuessANumberGameImpl`. Explain
  why that's the payoff of interface-first design.
- Apply the same exercise to a brand-new problem domain (e.g. tic-tac-toe,
  rock-paper-scissors) on the spot.
