# Week 7 Self-Assessment Rubric

Use this rubric to evaluate your own work. Be honest - it's for your learning!

## Pre-Class Exercises (KeyValueStore + SortingStrategy)

### KeyValueStore Implementation

| Criterion | Not Yet (0) | Partial (1) | Complete (2) |
|-----------|-------------|-------------|--------------|
| **put() works** | Doesn't compile or always fails | Works for new keys but not replacements | Works for both new keys and replacements |
| **get() works** | Returns wrong values or crashes | Works but doesn't return Optional properly | Returns Optional.empty() for missing keys |
| **containsKey() works** | Always returns same value | Works but not after remove | Works in all cases |
| **remove() works** | Doesn't remove or crashes | Removes but doesn't return value | Removes and returns previous value |
| **size/isEmpty work** | Wrong values | One works, other doesn't | Both accurate |
| **keys/values work** | Crashes or wrong | Returns values but not unmodifiable | Returns unmodifiable collections |
| **All tests pass** | 0-50% pass | 50-90% pass | All tests pass |

**Score: ___ / 14**

### SortingStrategy Understanding

| Criterion | Not Yet (0) | Partial (1) | Complete (2) |
|-----------|-------------|-------------|--------------|
| **Can write basic comparator** | Cannot create one | Creates one but wrong ordering | Creates correct comparators |
| **Understands return values** | Confused about neg/zero/pos | Knows concept but makes mistakes | Clear understanding |
| **Can use reversed()** | Doesn't know how | Uses but sometimes wrong | Uses correctly |
| **Can use thenComparing()** | Doesn't know how | Uses but chain is wrong | Chains correctly |

**Score: ___ / 8**

## Post-Class Exercises (SearchEngine)

### SearchEngine Implementation

| Criterion | Not Yet (0) | Partial (1) | Complete (2) |
|-----------|-------------|-------------|--------------|
| **Constructor works** | Crashes or doesn't initialize | Initializes but without defensive copy | Defensive copy + default strategies |
| **addItem/removeItem work** | Don't modify collection | Work but return wrong values | Work correctly with Optional returns |
| **setXxxStrategy works** | Doesn't store strategy | Stores but search doesn't use it | Stores and search uses it |
| **search() works** | Crashes or returns wrong results | Works with search but not filter/sort | All three strategies applied correctly |
| **getAllItems() works** | Crashes or empty | Returns items but no filter/sort | Filter and sort applied |
| **All tests pass** | 0-50% pass | 50-90% pass | All tests pass |

**Score: ___ / 12**

### Strategy Usage Understanding

| Criterion | Not Yet (0) | Partial (1) | Complete (2) |
|-----------|-------------|-------------|--------------|
| **Can create SearchStrategy** | Cannot | Creates but logic wrong | Creates correct strategies |
| **Can create FilterStrategy** | Cannot | Creates but logic wrong | Creates correct strategies |
| **Can combine strategies** | Cannot use and/or | Uses but combines wrong | Combines correctly |
| **Understands when to use which** | Confuses search vs filter | Knows difference but sometimes wrong | Clear distinction |

**Score: ___ / 8**

## Total Score

| Section | Your Score | Max Score |
|---------|------------|-----------|
| KeyValueStore | ___ | 14 |
| SortingStrategy | ___ | 8 |
| SearchEngine | ___ | 12 |
| Strategy Usage | ___ | 8 |
| **Total** | ___ | **42** |

## Self-Evaluation Questions

1. Can I explain why we use `Map<K,V>` instead of `HashMap<K,V>` as a variable type?

   [ ] Yes, clearly [ ] Somewhat [ ] Not really

2. Can I write a Comparator for any object if given the sorting criteria?

   [ ] Yes, easily [ ] With hints [ ] Need more practice

3. Can I explain the Strategy pattern to someone else?

   [ ] Yes, with examples [ ] The basic idea [ ] Not confidently

4. Do I understand how search, filter, and sort work together?

   [ ] Yes, clearly [ ] I see how but couldn't recreate [ ] It's confusing

## Reflection

What concept was hardest this week?

_________________________________________________________________

What would help you understand it better?

_________________________________________________________________

What concept "clicked" for you this week?

_________________________________________________________________
