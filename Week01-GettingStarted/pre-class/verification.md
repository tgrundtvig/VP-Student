# Verification Checklist

Use this to confirm you're ready for class.

## Reading Comprehension

- [ ] I can explain what a text adventure game is (rooms, connections, player movement)
- [ ] I understand that a `Room` object can hold references to other `Room` objects
- [ ] I understand that connecting two rooms bidirectionally means each room remembers the other
- [ ] I know that the player moves by following references from one room to the next

## Exercises

- [ ] I implemented `Waypoint` with name, next, and the required methods
- [ ] I implemented `Trail` with countWaypoints and getEndpoint that walk the chain
- [ ] Running `mvn test` shows all tests passing

## Self-Test Questions

**If a Waypoint has no next waypoint, what should `hasNext()` return?**

<details><summary>Answer</summary>

It should return `false`. A Waypoint with no next is the end of the chain — just like a Room with no north exit returns `null` for `getNorth()`. The `hasNext()` method lets you check before following the link.

</details>

**How does `countWaypoints()` know when to stop counting?**

<details><summary>Answer</summary>

It walks the chain from the start, following each `getNext()` link, and stops when `getNext()` returns `null` (or equivalently, when `hasNext()` returns `false`). This is the same pattern the player uses when moving through rooms — you follow links until there are no more to follow.

</details>

**What is the difference between `getEndpoint()` and `getStart()`?**

<details><summary>Answer</summary>

`getStart()` simply returns the first Waypoint in the trail — it is just a getter. `getEndpoint()` must **walk the entire chain** to find the last Waypoint (the one where `hasNext()` is false). This distinction matters: one is instant, the other requires traversal. In class, you will see a similar distinction when navigating room connections.

</details>

## Ready for Class?

If you completed the reading and all tests pass, you are ready. In class we will use the same "objects linking to objects" pattern to build rooms that connect in four directions instead of just one.
