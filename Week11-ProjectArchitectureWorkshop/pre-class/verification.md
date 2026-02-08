# Week 11 Pre-Class Verification

## Before Class Checklist

Complete this checklist to verify you are ready for the architecture workshop.

### Reading Complete
- [ ] I have read "From Idea to Architecture" (pre-class reading)
- [ ] I understand how to break an idea into entities and interfaces
- [ ] I can explain the four architecture layers (View, Controller, Model, Repository)
- [ ] I know the difference between records (data) and interfaces (behavior)

### Project Pitch Prepared
- [ ] I have filled in all fields in `ProjectPitch.java`
- [ ] All tests pass when I run `mvn test`
- [ ] I have practiced my pitch out loud (aim for 3 minutes)
- [ ] I can explain my project's purpose in one sentence

### Design Thinking
- [ ] I have identified at least 2 main entities (records)
- [ ] I have identified at least 2 core interfaces
- [ ] I know which patterns from weeks 3-9 I will use
- [ ] I have checked my scope (2-4 entities, achievable in 2 weeks)

### Feedback Readiness
- [ ] I am prepared to give constructive feedback on others' pitches
- [ ] I remember the feedback structure: strengths, questions, suggestions
- [ ] I have reviewed the example projects from Week 10 for comparison

### Week 10 Proposal Reviewed
- [ ] I have revisited my Week 10 project proposal
- [ ] I have noted what I want to change or improve
- [ ] I have specific questions for the teacher or classmates

## Running Verification

```bash
cd pre-class/exercises
mvn test
```

**Expected output:** All tests should pass.

## If Tests Fail

The tests verify:
1. Your project has a title and description
2. You have listed at least 2 entities with descriptions
3. You have listed at least 2 interfaces with purposes
4. You have identified at least 1 pattern with an explanation
5. Your formatted pitch method produces non-empty output

If a test fails:
- Read the assertion message carefully --- it tells you exactly what is missing
- Fill in more detail in the corresponding field
- Remember: specificity beats vagueness

## Questions to Bring to Class

Write down questions about:
- Design decisions you are unsure about
- How to apply specific patterns to your domain
- Whether your scope is appropriate
- How to handle tricky entity relationships

Good questions make the workshop more valuable for everyone.
