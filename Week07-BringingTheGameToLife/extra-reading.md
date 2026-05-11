# Extra Reading: Interface-First on a New Domain

> Companion to [Week 07's README](README.md).

## What This Week Proves

You can build a brand-new project, from a clean slate, using interface-first
design, in one classroom session. That's what `GuessANumber` is for: it's a
*small enough* problem that we can show the whole methodology end-to-end
in 3 hours.

If you can do it for guess-a-number, you can do it for your exam project.

## The Anatomy of `GuessANumber`

### Step 1: Wishful workflow

What does the game *do*?

```java
GuessANumberGame game = factory.makeGame(humanGuesser, computerThinker);
game.play();
```

Two lines. Done. Now we know we need:
- A `GuessANumberGame` with a `play()` method.
- Something that produces a game given a `Guesser` and a `Thinker`.

### Step 2: Promote the wishes to interfaces

```java
public interface GuessANumberGame {
    void play();
}

public interface Guesser {
    int makeGuess();
    void receiveFeedback(Feedback feedback);
}

public interface Thinker {
    Feedback rateGuess(int guess);
}

public record Feedback(boolean isCorrect, boolean isTooHigh) { }

public interface PlayerFactory {
    Guesser humanGuesser();
    Guesser computerGuesser();
    Thinker humanThinker();
    Thinker computerThinker();
}
```

Notice:
- `Feedback` is a record (data, no behaviour).
- Everything else is an interface (behaviour).
- The `PlayerFactory` is a factory because *creating a Guesser depends on
  which kind of guesser you want* — the choice is hidden from the game loop.

### Step 3: Workflow against the interfaces

```java
public class GuessANumberGameImpl implements GuessANumberGame {
    private final Guesser guesser;
    private final Thinker thinker;

    @Override
    public void play() {
        while (true) {
            int guess = guesser.makeGuess();
            Feedback feedback = thinker.rateGuess(guess);
            guesser.receiveFeedback(feedback);
            if (feedback.isCorrect()) {
                break;
            }
        }
    }
}
```

This implementation uses *only* the interfaces. It works for any combination
of guesser and thinker. That's the payoff.

### Step 4: Implement the leaves

`ComputerGuesser` does binary search. `HumanGuesser` asks a `TextAppUser`.
`ComputerThinker` picks a random number. `HumanThinker` asks the user.

Each leaf is small (~20 lines). Each one is independently testable.

## The Pattern

For *any* new problem you face, the same five steps apply:

1. **Write the wishful workflow.** Two or three lines. Made-up types.
2. **Promote to interfaces.** Each made-up type becomes an interface or
   record.
3. **Sort into data and behaviour.** Data → records. Behaviour →
   interfaces.
4. **Write the high-level implementation against the interfaces.** No
   concrete classes yet.
5. **Implement the leaves.** Each one small, each one testable.

You can do this for:
- A todo list (`TaskStore`, `Task` record, `Renderer`, `InputHandler`)
- A chat app (`MessageStore`, `Message` record, `User`, `Channel`,
  `Transport`)
- Tic-tac-toe (`Board`, `Move` record, `Player`, `WinDetector`)
- Your exam project (you tell me)

The exam will test that you *can* do it. The course gave you the methodology.
This week showed it can be done from scratch in 3 hours. You can do the same.

## A Practice Exercise

Pick a problem you haven't seen. Examples:
- Pomodoro timer
- URL shortener
- File-content searcher
- Anagram solver

Spend 30 minutes doing steps 1–3. Don't write any implementation. See if
the design holds up — can you write the high-level workflow in step 4
against just your interfaces, without getting stuck?

If you got stuck, the design has gaps. Go back to step 1, refine, repeat.

## For The Exam

- Walk through `GuessANumber` end-to-end, naming each interface and why
  it exists.
- Repeat the methodology on a new domain the examiner picks on the spot.
- Explain why splitting into `Guesser` + `Thinker` is better than a
  single `Game` class with `play()`.
- Demonstrate that the same game loop works for all four combinations
  (H/H, H/C, C/H, C/C).
