# Week 12 Self-Assessment Rubric: Design Document

Rate yourself on each criterion. Be honest --- this helps you identify areas to strengthen
before writing tests in Week 13.

## Interface JavaDoc (30 points)

| Criterion | Excellent (5) | Good (3) | Needs Work (1) |
|-----------|--------------|----------|----------------|
| **View JavaDoc** | Every method documented with params, returns, edge cases | Most methods documented | Incomplete documentation |
| **Repository JavaDoc** | Every method documented with params, returns, edge cases | Most methods documented | Incomplete documentation |
| **Contract focus** | Describes what, not how; implementation-independent | Mostly contract-focused | Includes implementation details |
| **Edge cases** | All edge cases documented (empty, null, missing) | Some edge cases covered | Edge cases not addressed |
| **Param/return docs** | Every @param and @return present and meaningful | Most present | Missing or trivial |
| **Readability** | A stranger could implement from JavaDoc alone | Mostly understandable | Unclear without explanation |

**My score:** ___ / 30

## Workflow Pseudo-Code (30 points)

| Criterion | Excellent (5) | Good (3) | Needs Work (1) |
|-----------|--------------|----------|----------------|
| **Coverage** | 3+ major operations documented | 2 operations | Fewer than 2 |
| **Step detail** | Each step names the layer and method | Steps are general | Steps are vague |
| **Error paths** | Error paths included for each workflow | Some error paths | No error paths |
| **Method references** | Exact method names from interfaces used | Approximate methods | Generic descriptions |
| **Completeness** | Full flow from user action to feedback | Most steps present | Missing important steps |
| **Logical flow** | Steps follow logically, no gaps | Mostly logical | Confusing order |

**My score:** ___ / 30

## Mock Sketches (20 points)

| Criterion | Excellent (5) | Good (3) | Needs Work (1) |
|-----------|--------------|----------|----------------|
| **View mock** | Queue/list state for all methods | Most methods covered | Incomplete |
| **Repository mock** | InMemory with HashMap storage described | Basic description | Missing |
| **Programming methods** | How to set up the mock for tests | Mentioned | Not described |
| **Verification methods** | How to check what the mock recorded | Mentioned | Not described |

**My score:** ___ / 20

## Edge Case Documentation (20 points)

| Criterion | Excellent (5) | Good (3) | Needs Work (1) |
|-----------|--------------|----------|----------------|
| **Coverage** | All important methods' edge cases documented | Some methods covered | Few edge cases |
| **Specificity** | Each edge case has trigger, behavior, and response | General descriptions | Vague |
| **Decisions** | Clear choices (Optional vs exception, empty list behavior) | Some decisions documented | No decisions made |
| **Consistency** | Same patterns applied across all interfaces | Mostly consistent | Inconsistent |

**My score:** ___ / 20

---

## Total Score

| Category | Points |
|----------|--------|
| Interface JavaDoc | ___ / 30 |
| Workflow Pseudo-Code | ___ / 30 |
| Mock Sketches | ___ / 20 |
| Edge Case Documentation | ___ / 20 |
| **Total** | **___ / 100** |

## Score Interpretation

- **90-100:** Excellent design, ready to write tests in Week 13
- **75-89:** Good design, minor gaps to fill
- **60-74:** Adequate but needs more detail, especially in JavaDoc and workflows
- **Below 60:** Significant revision needed --- revisit the reading and examples

## Reflection Questions

1. Could a classmate write a mock implementation of your View from the JavaDoc alone?

2. Do your workflows cover all the main user operations in your project?

3. Are there any methods where you are unsure about the return type or edge case behavior?

4. How did peer review feedback change your design?

5. What do you anticipate being the hardest part of writing tests in Week 13?
