# Week 11 Self-Assessment Rubric: Architecture Document

Rate yourself on each criterion. Be honest --- this helps you identify areas to improve.

## Project Identity (10 points)

| Criterion | Excellent (5) | Good (3) | Needs Work (1) |
|-----------|--------------|----------|----------------|
| **Project name** | Clear, descriptive | Adequate | Vague or missing |
| **Summary** | Clear purpose, scope, and user | Describes what it does | Unclear purpose |

**My score:** ___ / 10

## Interface Design (35 points)

| Criterion | Excellent (5) | Good (3) | Needs Work (1) |
|-----------|--------------|----------|----------------|
| **View interface** | All display/input methods with specific types | Most methods listed | Incomplete or vague types |
| **Repository interface** | CRUD + domain queries with types | Basic CRUD only | Missing key methods |
| **Method signatures** | All params and returns fully typed | Most types specified | Generic Object/String |
| **Interface count** | 2+ well-designed interfaces | 2 basic interfaces | Fewer than 2 |
| **Naming** | Clear, descriptive names following conventions | Adequate names | Unclear naming |
| **Completeness** | Every needed method is present | Most methods present | Significant gaps |
| **Mock consideration** | MockView planned with queue/record pattern | Mock mentioned | Mock not considered |

**My score:** ___ / 35

## Record Design (25 points)

| Criterion | Excellent (5) | Good (3) | Needs Work (1) |
|-----------|--------------|----------|----------------|
| **Record count** | 2-4 well-defined records | 2 basic records | Fewer than 2 |
| **Field definitions** | All fields typed with descriptions | Most fields typed | Fields unclear |
| **ID fields** | Every record has a String ID | Most have IDs | IDs missing |
| **Relationships** | Entities linked by ID references | Some relationships | Object references used |
| **Completeness** | All needed data represented | Most data captured | Significant gaps |

**My score:** ___ / 25

## Architecture Layers (20 points)

| Criterion | Excellent (5) | Good (3) | Needs Work (1) |
|-----------|--------------|----------|----------------|
| **Layer separation** | All 4 layers clearly defined | Most layers shown | Layers unclear |
| **Components per layer** | Each layer lists its components | Some components listed | Components missing |
| **Connections** | Clear how layers communicate | Some connections shown | Connections unclear |
| **Implementations** | Concrete implementations listed per interface | Some implementations | Only interfaces listed |

**My score:** ___ / 20

## Formatted Document (10 points)

| Criterion | Excellent (5) | Good (3) | Needs Work (1) |
|-----------|--------------|----------|----------------|
| **Completeness** | All sections included | Most sections included | Sections missing |
| **Readability** | Clear formatting, easy to follow | Readable | Hard to follow |

**My score:** ___ / 10

---

## Total Score

| Category | Points |
|----------|--------|
| Project Identity | ___ / 10 |
| Interface Design | ___ / 35 |
| Record Design | ___ / 25 |
| Architecture Layers | ___ / 20 |
| Formatted Document | ___ / 10 |
| **Total** | **___ / 100** |

## Score Interpretation

- **90-100:** Excellent architecture document, ready for detailed design in Week 12
- **75-89:** Good document, minor refinements needed
- **60-74:** Adequate document, needs more detail in interfaces and records
- **Below 60:** Significant revision needed --- seek teacher feedback

## Reflection Questions

1. What was the hardest part of defining your interfaces?

2. Did feedback from the workshop change your design? How?

3. Are there parts of your architecture you are still unsure about?

4. How does your architecture compare to the example projects?

5. What specific questions do you want to address in Week 12's design deep dive?
