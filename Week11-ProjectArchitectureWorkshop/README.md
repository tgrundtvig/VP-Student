# Week 11: Project Architecture Workshop

## Overview

This week marks the beginning of Phase 3: Application. You have spent ten weeks learning design
principles and patterns --- now it is time to apply them to your own exam project.

In this session you will present your project idea, receive structured peer feedback, and begin
refining your architecture into a complete interface hierarchy. By the end of the evening you will
have a clear picture of what you are building and how all the pieces fit together.

**This is a collaborative workshop.** You will learn as much from reviewing others' designs as from
working on your own.

## Learning Objectives

By the end of this week, you should be able to:

1. **Present** a project concept clearly in a 3-minute pitch
2. **Give** constructive design feedback using the vocabulary from weeks 1-10
3. **Create** a complete interface hierarchy for a non-trivial application
4. **Apply** patterns (MVC, Repository, Strategy, interface-first design) to a fresh problem domain

## Pre-Class Preparation

**Time estimate: 90-120 minutes**

1. **Read** the [pre-class reading](pre-class/reading.md) on turning ideas into architecture
2. **Complete** the exercises in [pre-class/exercises](pre-class/exercises):
   - Define your project's purpose
   - List main entities
   - Sketch initial interfaces
   - Identify which patterns from weeks 3-9 apply
3. **Prepare a 3-minute pitch** of your project idea:
   - What problem does it solve?
   - Who is the user?
   - What are the main entities and interfaces?
   - Which patterns will you use?
4. **Verify** your preparation with the [verification checklist](pre-class/verification.md)

**Come to class ready to present and to listen critically to others' pitches.**

## Post-Class Work

**Time estimate: 2-3 hours**

After receiving feedback in class, you will refine your architecture:

1. **Incorporate** peer feedback into your design
2. **Create** a detailed architecture document with all interfaces and records
3. **Map** architecture layers (Domain, Service, Repository, View)
4. **Ensure** every interface has defined method signatures

See [post-class/exercises](post-class/exercises) for detailed instructions.

## What Happens in Class

### First Half (17:10-18:20)
- Brief recap of what makes good architecture (10 min)
- **Project pitch rounds:** Each student presents for 3 minutes (50 min)
  - After each pitch: 2 minutes of peer feedback
  - Teacher highlights strengths and suggests improvements

### Second Half (18:30-19:45)
- **Architecture workshop:** Work on your interface hierarchy (30 min)
  - Teacher circulates and provides individual guidance
- **Peer review:** Pair up and review each other's hierarchies (20 min)
- **Start homework together:** Begin the architecture document (25 min)

## Connection to Previous Weeks

| Week | Concept | How You Apply It This Week |
|------|---------|---------------------------|
| Week 3 | Interfaces as contracts | Define your project's contracts |
| Week 4 | Wishful programming | Design top-down, worry about implementation later |
| Week 5-6 | Data structures | Choose the right structures for your entities |
| Week 7 | Maps and strategies | Identify configurable behaviors |
| Week 8 | MVC pattern | Structure your View, Controller, and Model |
| Week 9 | Repository pattern | Design your persistence interfaces |
| Week 10 | Example projects | Use them as templates for your own design |

## Looking Ahead

- **Week 12:** Project Design Deep Dive --- finalize all interface method signatures and records
- **Week 13:** Project Testing Strategy --- write tests against your interfaces before implementing
- **Week 14:** Final Review and Launch Preparation --- ensure your design is implementation-ready
- **2-week gap:** Implement your designed system

Each week builds on the previous one. By Week 14, your entire design will be complete and you will
be ready to start coding with confidence.

## What Makes a Good Architecture Pitch?

### Structure
1. **Problem statement** (30 seconds): What problem are you solving?
2. **User story** (30 seconds): Who uses it and how?
3. **Entities** (45 seconds): What data does the system manage?
4. **Interfaces** (45 seconds): What contracts define the system?
5. **Patterns** (30 seconds): Which patterns from class will you use?

### Common Pitfalls
- **Too vague:** "It manages stuff" --- be specific about entities and operations
- **Too ambitious:** Five complex entities with external integrations --- keep scope realistic
- **No interfaces:** Describing features without contracts --- think in interfaces, not features
- **Skipping patterns:** Not connecting to what you have learned --- name the patterns you will use

## Tips for Giving Good Feedback

When reviewing a peer's pitch:
- **Start with what works:** "I like that you separated X from Y"
- **Ask clarifying questions:** "How does the View know about new items?"
- **Suggest improvements:** "Have you considered a Strategy for the scoring?"
- **Check scope:** "Can you build this in 2 weeks?"

## Verification Checklist

Before class, ensure you:
- [ ] Can explain your project in under 3 minutes
- [ ] Have identified at least 2 main entities (records)
- [ ] Have identified at least 2 interfaces
- [ ] Know which patterns from weeks 3-9 you will use
- [ ] Have completed the pre-class exercises and all tests pass
- [ ] Have reviewed your Week 10 project proposal

## Getting Help

- Review the example projects from Week 10 for structural inspiration
- Bring specific questions about your design to class
- Use the [quick-reference](../quick-reference) guides for interface design checklists
- Discuss ideas with classmates before and after class

---

**This week's mantra:** "Present early, get feedback often, iterate on your design."
