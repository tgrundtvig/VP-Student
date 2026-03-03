# Verification Checklist

Use this to confirm you're ready for class.

## Reading Comprehension

- [ ] I understand that the code which *uses* an interface reveals what the interface needs
- [ ] I can find missing methods by trying to write a workflow against the current interfaces
- [ ] I understand the difference between adding a method every implementation needs vs. creating a new interface for some implementations

## Engine Understanding

- [ ] I can explain what `SimpleGameLoop` does and what it does NOT know about
- [ ] I understand how `GameLoop`, `Game`, and `Actor` relate to each other
- [ ] I can trace the flow: `GameLoop.run()` calls `Game` methods, which provide `Actor`s, which call `takeTurn()`

## Design Proposals

- [ ] I have written proposed interface changes for at least 5 of the 7 design questions
- [ ] For each proposal, I have a reason — not just what to change, but WHY
- [ ] I have checked that my proposals don't contradict each other

## Self-Test Questions

**Q1: SimpleGameLoop calls `actor.takeTurn()` with no arguments. How does the actor know where it is and what it can do?**

<details>
<summary>Answer</summary>

The actor must already have this information inside itself. `getCurrentLocation()` exists on Actor, so the actor knows its location. But the question of how it accesses I/O (for a player) or AI logic (for an NPC) is one of the open design questions. The actor's implementation must hold references to whatever it needs — the engine doesn't pass it in at turn time.
</details>

**Q2: Why is Location an interface and not a record?**

<details>
<summary>Answer</summary>

This is actually a valid design question. Right now, Location only has getter methods (`getActors()`, `getItems()`), which looks like data. But locations may need behavior that changes over time — a description that updates after events, a list of actors that changes as they move in and out, or exit connections that could be locked or unlocked. An interface allows each theme to implement locations differently. A record would force all locations to be static and identical in structure.

That said, if you argued for a record with some fields, that's a reasonable starting position too — as long as you can explain what you'd do when a location needs dynamic behavior.
</details>

**Q3: If you remove the Player and NPC interfaces and keep only Actor, what problem might you run into?**

<details>
<summary>Answer</summary>

There are valid arguments on both sides. The potential problem: the engine might need to treat players and NPCs differently somewhere (e.g., prompting for input vs. running AI). Without separate types, you'd need another way to distinguish them — a boolean `isPlayer()` method, or a check like `instanceof`, or a completely different approach.

The counter-argument: if `Actor.takeTurn()` is polymorphic (each implementation decides what to do), then the engine never needs to know the difference. A player's `takeTurn()` reads from the terminal; an NPC's `takeTurn()` runs AI logic. The engine just calls `takeTurn()` and doesn't care.

Both positions are defensible. What matters is your reasoning.
</details>

## Ready for Class?

If you checked all the boxes above and thought through the design questions, you're ready. Come to class prepared to share your proposals and hear different perspectives. We'll converge on a design together and start building.
