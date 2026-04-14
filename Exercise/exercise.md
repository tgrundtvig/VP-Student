# Exercise: Multilevel Menu System

## Overview

Your task is to design and implement a **multilevel (nested) menu system** — the kind
of menu you might see in an old text-based application or in a configuration tool.
A menu contains menu items. Some menu items are *actions* that do something when
chosen. Others are *submenus* that open up another menu containing yet more items.
Submenus can be nested arbitrarily deep.

You must build this system using **two** design patterns working together:

1. The **Composite pattern** — to represent the tree of menus and menu items.
2. The **Command pattern** — to represent the actions that menu items perform
   when the user chooses them.

## Learning goals

By finishing this exercise you should be able to:

- Recognise when a problem has a naturally *recursive, tree-shaped* structure and
  model it with the Composite pattern.
- Separate *what gets executed* (the Command) from *where it sits in the menu
  structure* (the menu item), and understand why that separation is useful.
- Design an interface hierarchy top-down before writing any implementation.
- Write workflow code that walks a tree of objects without knowing or caring which
  concrete classes it is dealing with.

## Inspiration: the FileSystem project

Before you start, open `Projects/FileSystem` and study it carefully. It is a very
small example of the Composite pattern in action:

- `Node` is the common abstraction — everything in the tree is a `Node`.
- `FileNode` is a *leaf*: it has no children, it just carries content.
- `Directory` is a *composite*: it has a name **and** contains other `Node`s.
- `NodePrinter` is a workflow that walks the tree recursively. Notice how it only
  depends on the `Node` abstraction — it switches on what kind of node it
  encounters but does not care about concrete implementation classes.

Your menu system has the same shape:

| FileSystem concept | Menu system concept          |
|--------------------|------------------------------|
| `Node`             | A menu item (common type)    |
| `FileNode` (leaf)  | An action item               |
| `Directory`        | A submenu (contains items)   |
| `NodePrinter`      | The menu runner / UI         |

The big *new* thing in this exercise is that the leaf is no longer passive data
(like a file's content) — it must *do something*. That "something" is where the
**Command pattern** enters the picture.

## What the system must do

From the user's point of view, when the program runs:

1. The top-level menu is displayed. It shows a numbered list of its items, each
   with a human-readable title.
2. The user types the number of an item and presses Enter.
3. If the chosen item is an **action**, the action runs (it prints something,
   changes some state, asks for more input — whatever the action wants to do).
   Afterwards the same menu is shown again.
4. If the chosen item is a **submenu**, that submenu is displayed instead. The
   user can now choose items from the submenu in the same way.
5. Every menu (except the top-level one) must offer a way to go **back** to its
   parent menu.
6. The top-level menu must offer a way to **quit** the program.
7. Invalid input (a letter, a number out of range, an empty line) must be
   handled gracefully — the user should be told it was invalid and be asked
   again. The program must not crash.

The system must support menus nested **to any depth** — 1 level, 3 levels, 10
levels. Nothing in your design may assume a particular depth.

## Design constraints (this is the important part)

You are learning *interface-first* design. Therefore:

- **Design the interfaces and records before writing any implementation.**
  Sketch them on paper or in your editor. Show them to a classmate. Convince
  yourself the design makes sense before you implement anything.
- Every piece of behaviour must sit behind an **interface**. Plain data objects
  (if you have any) must be **records**.
- Your "menu runner" — the loop that reads input from the user and dispatches
  choices — must be written against your abstractions only. It must *not*
  switch on concrete classes, except where the language forces you to (for
  example, when distinguishing an action item from a submenu item at the moment
  of dispatch).
- Your action items must not contain the logic they execute. Each action item
  *has* a command; it does not *be* a command's logic. This is what makes the
  Command pattern valuable: you can combine any title with any command, and
  you can build commands independently of the menu that shows them.

## Suggested abstractions (names are up to you)

Think about (at least) the following roles. You decide whether each is an
interface, a record, or a class — and what it is called:

- A common supertype for everything that can appear as an entry in a menu.
- A leaf type that represents an action the user can pick.
- A composite type that represents a submenu (it has a title and contains
  entries).
- A type that represents "something the user can execute" — the Command. It
  should be trivially small; think about what its single method should be
  called and what it should return (if anything).
- A type that represents input from the user and a type that represents output
  to the user. Keeping these behind interfaces lets you swap `System.in`/
  `System.out` for scripted test input later, exactly like we did with
  `TextIn` in the VP_Project.
- A "menu runner" / "menu engine" that, given a top-level submenu, drives the
  whole interaction until the user quits.

Do not feel obliged to use exactly these names or exactly this set of types.
Use them as a starting point for your own design.

## Building a sample menu

To demonstrate your system, build a menu tree that looks something like this
(you may invent your own actions — be creative):

```
Main menu
  1. Greetings
       1. Say hello
       2. Say goodbye
       3. Back
  2. Math
       1. Add two numbers
       2. Multiply two numbers
       3. Advanced
            1. Square a number
            2. Compute factorial
            3. Back
       4. Back
  3. About
  4. Quit
```

Notice:

- "Greetings" and "Math" are submenus.
- "Advanced" is a submenu nested inside "Math" — so at least three levels deep.
- "Say hello", "Say goodbye", "Add two numbers", "Square a number", "About"
  are action items. Each is implemented as a separate command.
- "Back" appears in every non-top-level menu. "Quit" appears only in the top
  menu.

When you assemble the tree in your `main`/demo method, it should read almost
like a description of the menu structure — because you are simply building up
a Composite tree node by node, just like `Demo.java` does for the file system.

## Suggested incremental approach

1. **Model the tree first.** Before anything else, get the Composite part
   working: a submenu that can contain entries, where entries are either
   actions or other submenus. Write a small "printer" that prints the menu
   tree (much like `NodePrinter`). This proves the tree structure works.
2. **Introduce the Command abstraction.** Give your action items a command.
   Write two or three trivial commands (e.g. print "hello").
3. **Write the runner.** Now write the loop that shows a menu, reads a choice,
   and either runs the command or descends into the chosen submenu.
4. **Add Back and Quit.** Decide how to model these. They can be ordinary
   commands, or they can be a built-in feature of the runner — discuss the
   trade-offs with a classmate.
5. **Add a couple of commands that ask the user for input** (e.g. "add two
   numbers"). This is where it pays off that your commands can use the same
   input/output abstractions the runner uses.
6. **Polish input handling.** Invalid choices must never crash the program.

## Reflection questions (bring answers to class)

Think about these while you work. We will discuss them next week:

1. If someone asked you to add a new action (say, "Tell a joke"), how much of
   your existing code would you have to change? Why?
2. If someone asked you to add a new *kind* of menu entry (something that is
   neither a plain action nor a submenu), how much would you have to change?
3. Where did you put the logic for "Back" and "Quit", and why? Would another
   placement be equally valid?
4. Your runner prints menus, reads input and executes commands. Commands
   themselves may also print and read input. Did this lead to duplication or
   awkward coupling? How did you solve it?
5. Which parts of your code did the Composite pattern make easier? Which parts
   did the Command pattern make easier? Would the system be harder to change
   without either of them?

## Hints (only if you get stuck)

- If your runner is full of `if`/`instanceof` checks, step back: you are
  probably missing an abstraction. Ask yourself what operation both action
  items and submenus support, and lift it onto the common supertype.
- A command that does *nothing* is a perfectly valid command and is sometimes
  useful (for example, as a default or a placeholder).
- "Back" does not necessarily have to be a command. But it *can* be — and
  thinking about both options will sharpen your understanding of the pattern.
- The FileSystem project uses a `sealed` interface with `permits`. That is one
  way to model a closed set of node kinds. You may use this or the classical
  non-sealed approach — be prepared to defend your choice.

Good luck — and remember: design first, implement second.
