# Week 12 Pre-Class Verification

## Before Class Checklist

Complete this checklist to verify you are ready for the design deep dive.

### Reading Complete
- [ ] I have read "Designing Complete Interfaces" (pre-class reading)
- [ ] I understand how return types communicate intent (void, Optional, List)
- [ ] I know the difference between contract JavaDoc and implementation JavaDoc
- [ ] I understand how records serve as data transfer objects

### Interface Specification Complete
- [ ] I have filled in all fields in `InterfaceSpecification.java`
- [ ] All tests pass when I run `mvn test`
- [ ] My View interface has at least 3 methods with full signatures
- [ ] My Repository interface has at least 4 methods with full signatures
- [ ] I have written JavaDoc for both interfaces

### Record Design Complete
- [ ] I have specified at least 2 records with fields
- [ ] Each record has an ID field
- [ ] I have described factory methods and modification methods
- [ ] Entities reference each other by ID

### Workflow Documented
- [ ] I have described at least 2 user operations step by step
- [ ] Each workflow shows which interface methods are called
- [ ] The order of calls makes logical sense

### Peer Review Readiness
- [ ] My interface specification is clear enough for a peer to review
- [ ] I can explain each method's purpose without referencing implementation
- [ ] I am prepared to review a classmate's specification

## Running Verification

```bash
cd pre-class/exercises
mvn test
```

**Expected output:** All tests should pass.

## If Tests Fail

The tests verify:
1. View and Repository interfaces have names and methods
2. Method signatures include types and descriptions
3. JavaDoc is present and sufficiently detailed
4. Records have fields with types
5. Workflows are documented

If a test fails, read the assertion message for specific guidance on what to add.

## Questions to Bring to Class

- Are there methods I am uncertain about the return type for?
- Are there operations where I do not know which layer should be responsible?
- Does my View interface need any methods I have not thought of?
- Are my record fields sufficient for all planned operations?
