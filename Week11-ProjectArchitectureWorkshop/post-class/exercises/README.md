# Week 11 Post-Class: Architecture Document

## Overview

After receiving feedback on your pitch in class, it is time to create a detailed architecture
document. This document captures every interface, every record, and every layer of your system.

**The goal:** Someone reading your architecture document should understand your entire system's
structure without seeing any implementation code.

**Time estimate: 2-3 hours**

## What You Will Create

An `ArchitectureDocument.java` file containing:

1. **Project name** and summary
2. **All interfaces** with method signatures
3. **All records** with field definitions
4. **Architecture layers** showing how components connect
5. **A formatted document** combining everything into a readable output

## Instructions

1. Open `src/main/java/dk/viprogram/week11/ArchitectureDocument.java`
2. Fill in each section based on your refined design (incorporating class feedback)
3. Run tests to verify completeness
4. Review and iterate until the document is clear

## Incorporating Feedback

During the architecture workshop, you received feedback from peers and the teacher. Common
feedback themes include:

### Scope Adjustments
- "Add another entity to make it more interesting"
- "Remove X to keep scope manageable"
- "That feature is a stretch goal, not MVP"

### Interface Improvements
- "Your View interface needs a method for X"
- "Your Repository should support querying by Y"
- "Consider a Strategy pattern for Z"

### Record Refinements
- "Link entities by ID, not object reference"
- "Add a factory method for creation"
- "Consider what properties are really needed"

**Action:** Go through your notes from class and update your design before filling in the
architecture document.

## Running the Tests

```bash
mvn test
```

Tests verify:
- You have defined at least 2 interfaces with method signatures
- You have defined at least 2 records with fields
- Your architecture has defined layers
- Your formatted document is complete and readable

## Tips

1. **Use the example projects as reference** --- your structure should resemble Task Manager or
   Quiz App
2. **Be specific in method signatures** --- `void showRecipeList(List<Recipe> recipes)` not
   `void show(Object data)`
3. **Include all three View implementations** you plan to have: Console, JavaFX (optional), Mock
4. **Think about your Controller** --- what methods does it need to coordinate the flow?
5. **Every interface method should have types** --- no `Object` or `String` for everything

## Success Criteria

Your architecture document is ready when:
- [ ] All interfaces have complete method signatures with types
- [ ] All records have typed fields
- [ ] Architecture layers are clearly defined
- [ ] Someone could write tests against your interfaces
- [ ] The formatted document reads as a coherent system description
