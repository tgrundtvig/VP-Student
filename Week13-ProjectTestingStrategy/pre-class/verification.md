# Week 13 Pre-Class Verification

## Before Class Checklist

Complete this checklist to verify you are ready for the testing strategy session.

### Reading Complete
- [ ] I have read "Testing Against Interfaces" (pre-class reading)
- [ ] I understand the MockView pattern (queues for input, lists for output)
- [ ] I understand the InMemoryRepository pattern (HashMap-based)
- [ ] I know the Arrange-Act-Assert pattern for tests
- [ ] I can explain why tests use interface types, not implementation types

### Test Plan Complete
- [ ] I have filled in all fields in `TestPlanDocument.java`
- [ ] All tests pass when I run `mvn test`
- [ ] I have at least 3 test categories defined
- [ ] I have specific test cases for at least 2 components
- [ ] My mock strategy describes internal state and methods
- [ ] My estimated total test count is at least 10

### Design Documents Available
- [ ] I have my Week 11 architecture document available
- [ ] I have my Week 12 design document available
- [ ] I know all my interface method signatures
- [ ] I know all my record field definitions

### Ready to Code Tests
- [ ] I can write a basic JUnit 5 test from memory
- [ ] I know how to use @Test, @BeforeEach, @DisplayName
- [ ] I know how to use assertEquals, assertTrue, assertFalse
- [ ] I have IntelliJ ready with a Maven project open

## Running Verification

```bash
cd pre-class/exercises
mvn test
```

**Expected output:** All tests should pass.

## If Tests Fail

The tests verify:
1. At least 3 test categories are defined
2. At least 2 components have test cases with at least 2 cases each
3. Mock strategy is documented with sufficient detail
4. Test infrastructure is described
5. Estimated test count is reasonable (at least 10 total)

Read the assertion messages for specific guidance.

## Questions to Bring to Class

- Are there interface methods that seem hard to test?
- Am I unsure about what to verify in any test?
- Should I test the mock/in-memory implementations themselves?
- How do I test error paths and edge cases?
