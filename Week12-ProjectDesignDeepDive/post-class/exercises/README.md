# Week 12 Post-Class: Complete Interface Design with Workflows

## Overview

After the in-class peer review and refinement, it is time to finalize your interface design.
This exercise asks you to produce a complete design document that includes JavaDoc for every
interface and method, plus workflow pseudo-code showing how the interfaces interact.

**Time estimate: 2-3 hours**

## What You Will Create

A `DesignDocument.java` file containing:

1. **Complete JavaDoc** for every interface and every method in your project
2. **Workflow pseudo-code** for all major user operations
3. **Mock implementation sketches** showing how you would test each interface
4. **Edge case documentation** for important methods

## Instructions

1. Open `src/main/java/dk/viprogram/week12/DesignDocument.java`
2. Fill in each section incorporating feedback from today's peer review
3. Run tests to verify completeness
4. Review: could someone write tests from your design alone?

## Incorporating Peer Review Feedback

During class, a partner reviewed your interface specification. Common feedback includes:

- "This method's return type should be Optional, not the raw type"
- "You need a method for selecting from a list"
- "This parameter should be more specific"
- "The JavaDoc does not explain what happens with an empty list"

Go through your notes and update your design accordingly.

## Running the Tests

```bash
mvn test
```

Tests verify:
- Complete JavaDoc is provided for all interfaces
- At least 3 detailed workflows are documented
- Mock implementation approach is described
- Edge cases are documented

## The Quality Bar

Your design document is ready when:
- [ ] A classmate can write a mock implementation from your View JavaDoc alone
- [ ] A classmate can write an InMemoryRepository from your Repository JavaDoc alone
- [ ] Every method's contract is clear: what it takes, what it returns, what happens on edge cases
- [ ] Workflow pseudo-code shows the exact sequence of method calls for each operation

## Tips

1. **Write JavaDoc as if for a stranger** --- do not assume the reader knows your project
2. **Edge cases matter** --- what happens with empty lists? Missing IDs? Null input?
3. **Mock sketches reveal gaps** --- if you cannot sketch a mock, the interface has a problem
4. **Workflows are your implementation guide** --- they become the Controller's methods
