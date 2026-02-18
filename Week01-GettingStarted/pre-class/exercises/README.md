# Exercise: Waypoint Trail

## Goal

Practice the "objects referencing objects" pattern by building a linked chain of waypoints. This is a simplified version of the room connection pattern you will use in class — instead of four directions, a waypoint links to just one next waypoint.

## What's Provided

- **`Waypoint.java`** (skeleton) — a named point that can link to the next waypoint
- **`Trail.java`** (skeleton) — a trail that starts at a waypoint and can walk the chain
- **`WarmupTest.java`** (complete tests — do not modify)

## What You Need to Do

### 1. Implement `Waypoint`

A Waypoint has a name and an optional link to the next Waypoint.

- The constructor takes a `String name`
- `getName()` returns the name
- `getNext()` returns the next Waypoint (or `null` if there is none)
- `setNext(Waypoint next)` sets the link to the next Waypoint
- `hasNext()` returns `true` if there is a next Waypoint, `false` otherwise

### 2. Implement `Trail`

A Trail starts at a Waypoint and can walk the chain.

- The constructor takes a `Waypoint start`
- `getStart()` returns the first Waypoint
- `countWaypoints()` walks the chain from start to end, counting every waypoint (including start)
- `getEndpoint()` walks the chain and returns the last Waypoint (the one with no next)

## Running the Tests

```bash
mvn test
```

Before you implement anything, all tests will **fail**. After implementing both classes, all tests should **pass**.

## Think About It

- How is a chain of Waypoints similar to a line of Rooms connected north-to-south?
- What would you need to change if a Waypoint could link to *two* next waypoints instead of one?
