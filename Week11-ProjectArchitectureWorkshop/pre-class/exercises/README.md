# Week 11 Pre-Class Exercises: Prepare Your Project Pitch

## Overview

Before class, you need to prepare a structured project pitch. This exercise helps you organize
your thinking by filling in a `ProjectPitch.java` file with your project's key details.

**Time estimate: 60-90 minutes**

## Instructions

1. Open `src/main/java/dk/viprogram/week11/ProjectPitch.java`
2. Fill in each field with your project's information
3. Run the tests to verify completeness

## What to Fill In

### Project Title and Description
- Give your project a clear, descriptive title
- Write a 2-3 sentence description explaining what it does and who it is for

### Main Entities (at least 2)
- List the core data objects your system will manage
- For each entity, think about what properties it needs
- Remember: entities should be records (immutable data carriers)

### Core Interfaces (at least 2)
- List the behavior contracts your system needs
- At minimum you need a View interface and a Repository interface
- Consider whether you need Strategy or other behavioral interfaces

### Patterns Used
- Identify which patterns from weeks 3-9 apply to your project
- Every project should use at least MVC and Repository
- Explain briefly how each pattern applies

### Formatted Pitch
- Implement the `getFormattedPitch()` method to produce a readable summary
- This is what you will use as notes during your 3-minute class presentation

## Running the Tests

```bash
mvn test
```

The tests verify:
- Your project has a title
- You have defined at least 2 main entities
- You have defined at least 2 core interfaces
- You have identified at least 1 pattern
- Your formatted pitch is not empty

## Tips

- Look at the example projects from Week 10 for inspiration on entity and interface design
- Think about scope: 2-4 entities is the sweet spot
- Be specific: "RecipeView" is better than "View"
- Consider how you will test each interface (mock implementations)

## Connecting to Your Week 10 Proposal

If you completed the Week 10 post-class project proposal, use it as a starting point. This
exercise asks you to refine and structure that initial thinking into a presentable pitch.

## After Completing

- Practice saying your pitch out loud (aim for 3 minutes)
- Review the [verification checklist](../verification.md)
- Come to class ready to present and to give feedback to others
