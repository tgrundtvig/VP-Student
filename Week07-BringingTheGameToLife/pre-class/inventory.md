# Exercise: The Implementation Inventory

**Estimated time: ~10 minutes**

---

## What You'll Do

Go through every interface in the engine and check its status. For each one, verify whether an implementation exists and identify what's still missing.

---

## The Inventory

Here is a table of all the interfaces and key types in the project. Your job is to verify this against the actual code.

Open the project in IntelliJ (or your file browser) and check each package.

### Engine Layer (`dk.ek.evu.vpf26.txtadv.engine`)

| Interface | Has Implementation? | Implementation Class |
|-----------|:-------------------:|---------------------|
| `Location` | Yes | `SimpleLocation` |
| `LocationMap` | Yes | `SimpleLocationMap` |
| `LocationMapBuilder` | Yes | `SimpleLocationMapBuilder` |
| `GameLoop` | Yes | `SimpleGameLoop` |
| `Game` | No | — |
| `Actor` | No | — |
| `Player` | No | — |
| `NPC` | No | — |
| `Item` | No | (empty interface) |

### User Layer (`dk.ek.evu.vpf26.txtadv.user`)

| Interface | Has Implementation? | Implementation Class |
|-----------|:-------------------:|---------------------|
| `TextUser` | Yes | `TerminalUser` |
| `TextAppUser` | Yes | `TextAppUserImpl` |

### Command Layer (`dk.ek.evu.vpf26.txtadv.user.command`)

| Interface | Has Implementation? | Implementation Class |
|-----------|:-------------------:|---------------------|
| `Command` | Demo only | `WalkCommand`, `RunCommand`, `JumpCommand` (demos, not game commands) |
| `CommandRegistry` | Yes | `ReferenceCommandRegistry` |

---

## Your Tasks

### Task 1: Verify

Open the project and check each package. Does the table above match what you see? If anything has changed since this was written (maybe you implemented something in the post-class exercises), update your copy.

### Task 2: Describe What's Missing

For each interface marked "No" in the table above, write **one sentence** describing what its implementation needs to do. Be specific.

For example:
- `Game` implementation: *"Needs to build a small world of locations in initialize(), create a player, register commands, and track whether the game is over."*

Write your own sentence for each:

- **`Actor` implementation**: ___
- **`Player` implementation**: ___
- **`NPC` implementation**: ___
- **`Item` implementation**: ___
- **`Game` implementation**: ___
- **Game commands** (LookCommand, MoveCommand, etc.): ___

### Task 3: Star the Essentials

Which of these missing implementations are needed for the **minimum playable game**? Put a star next to the ones that must exist for a player to start the game, look around, move between rooms, and quit.

Think about it: do you need `NPC` for the minimum game? Do you need `Item`? Or can those wait?

---

## Checkpoint

When you're done, you should have:
- Verified the inventory against the actual codebase
- One sentence per missing implementation
- A clear picture of what's essential vs. what can wait
