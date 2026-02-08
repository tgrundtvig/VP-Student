# Week 13 Self-Assessment Rubric: Test Suite Report

Rate yourself on each criterion. This is the final design assessment before Week 14's project
setup --- use it to identify any remaining gaps.

## Model Tests (15 points)

| Criterion | Excellent (5) | Good (3) | Needs Work (1) |
|-----------|--------------|----------|----------------|
| **Coverage** | Tests for create, modify, and toString | Tests for 2 of 3 | Only 1 type of test |
| **Quality** | Clear AAA pattern, descriptive names | Mostly clear | Hard to follow |
| **Assertions** | Appropriate assertions for each scenario | Some assertions correct | Wrong or missing assertions |

**My score:** ___ / 15

## Repository Tests (25 points)

| Criterion | Excellent (5) | Good (3) | Needs Work (1) |
|-----------|--------------|----------|----------------|
| **CRUD coverage** | Save, find, findAll, delete all tested | Most CRUD tested | Missing CRUD tests |
| **Domain queries** | Custom queries tested with setup data | 1 query tested | No query tests |
| **Edge cases** | Not-found, empty, delete non-existent tested | Some edge cases | No edge cases |
| **Setup** | Clean @BeforeEach with fresh repository | Some setup | No setup pattern |
| **Assertions** | Verifies correct data, not just non-null | Mostly correct | Assertions too weak |

**My score:** ___ / 25

## Controller Tests (25 points)

| Criterion | Excellent (5) | Good (3) | Needs Work (1) |
|-----------|--------------|----------|----------------|
| **Happy paths** | All main operations tested successfully | Most tested | Few tested |
| **Cancel/error paths** | Cancellation and error scenarios tested | Some tested | Not tested |
| **Mock usage** | Correct use of queue and verify patterns | Mostly correct | Incorrect patterns |
| **Integration** | Tests verify View AND Repository together | Tests one layer | Does not integrate |
| **Clarity** | Tests readable and well-named | Mostly readable | Hard to understand |

**My score:** ___ / 25

## Mock Implementation (20 points)

| Criterion | Excellent (5) | Good (3) | Needs Work (1) |
|-----------|--------------|----------|----------------|
| **MockView** | Complete with all queues, lists, and methods | Most methods present | Incomplete |
| **InMemoryRepo** | Complete with HashMap and all methods | Most methods present | Incomplete |
| **Correctness** | Queue/list operations logically correct | Mostly correct | Errors in logic |
| **Completeness** | All interface methods implemented | Most implemented | Missing methods |

**My score:** ___ / 20

## Design Reflection (15 points)

| Criterion | Excellent (5) | Good (3) | Needs Work (1) |
|-----------|--------------|----------|----------------|
| **Awareness** | Clear identification of design changes | Some changes noted | No reflection |
| **Specificity** | Names specific methods/interfaces affected | General statements | Vague |
| **Action** | Describes how design was updated | Mentions changes | No action taken |

**My score:** ___ / 15

---

## Total Score

| Category | Points |
|----------|--------|
| Model Tests | ___ / 15 |
| Repository Tests | ___ / 25 |
| Controller Tests | ___ / 25 |
| Mock Implementation | ___ / 20 |
| Design Reflection | ___ / 15 |
| **Total** | **___ / 100** |

## Score Interpretation

- **90-100:** Excellent test suite, ready to transfer to real project in Week 14
- **75-89:** Good coverage, minor gaps to fill during Week 14
- **60-74:** Adequate but needs more test cases, especially edge cases
- **Below 60:** Significant work needed --- seek teacher feedback before Week 14

## Reflection Questions

1. Which test was the hardest to write? What made it difficult?

2. Did writing tests change your understanding of any interface?

3. Are you confident your mock can support all the controller tests you need?

4. How many tests do you estimate your final project will have?

5. What is the first thing you will implement when the 2-week period starts?
