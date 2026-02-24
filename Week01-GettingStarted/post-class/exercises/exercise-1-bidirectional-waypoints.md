# Exercise 1: Bidirectional Waypoints

## Goal

Extend your `Waypoint` class from the pre-class exercise so each waypoint links both forward (`next`) and backward (`prev`), just like rooms in the maze connect in both directions.

**Estimated time: 20 minutes.**

---

## Background

In the pre-class exercise, your `Waypoint` had a `next` field — you could walk forward through the trail. But you couldn't walk backward. The `Room` class from class solves this with bidirectional links: `connectNorth` sets both `this.north` and `other.south` in one call.

Apply the same idea to `Waypoint`.

---

## Tasks

### 1. Add a `prev` field

Add a `private Waypoint prev` field and a `public Waypoint getPrev()` getter.

### 2. Create a `connect` method

Write a method that links two waypoints bidirectionally:

```java
public void connect(Waypoint next) {
    this.next = next;
    next.prev = this;
}
```

After `a.connect(b)`, both `a.getNext() == b` and `b.getPrev() == a` should be true.

### 3. Walk backward

In a `main` method, create a trail of 3-4 waypoints using `connect`. Then:

1. Walk forward from the first to the last (using `getNext()`)
2. Walk backward from the last to the first (using `getPrev()`)
3. Print each waypoint's name as you go

Your output should look something like:

```
Forward:  Camp → River → Summit
Backward: Summit → River → Camp
```

---

## Hints

- The structure is the same as `Room.connectNorth()` — one method, two links.
- To walk backward, start at the last waypoint and keep calling `getPrev()` until you reach `null`.
- You already know how to walk forward from the pre-class exercise. Walking backward is the same loop, just with `getPrev()` instead of `getNext()`.

---

## Self-Check

- Calling `getPrev()` on the first waypoint returns `null` (no previous).
- Calling `getNext()` on the last waypoint returns `null` (no next).
- Walking forward then backward produces the same waypoints in reverse order.
