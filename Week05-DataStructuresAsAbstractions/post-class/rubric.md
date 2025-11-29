# Week 5: Self-Assessment Rubric

Use this rubric to evaluate your own work before submission.

## Exercise 1: LinkedSimpleList (35 points)

| Criteria | Points | Self-Score |
|----------|--------|------------|
| Constructor initializes head to null and size to 0 | 5 | |
| add() correctly adds elements at the end | 5 | |
| get() returns correct element for valid index | 5 | |
| get() throws IndexOutOfBoundsException for invalid index | 3 | |
| remove() correctly removes and returns element | 5 | |
| remove() handles removing head correctly | 3 | |
| contains() correctly finds elements using equals() | 4 | |
| clear() properly resets the list | 2 | |
| All LinkedSimpleListTest tests pass | 3 | |

**Exercise 1 Total: ___ / 35**

---

## Exercise 2: LinkedQueue (30 points)

| Criteria | Points | Self-Score |
|----------|--------|------------|
| Constructor initializes front, back, and size correctly | 4 | |
| enqueue() adds to back correctly | 5 | |
| enqueue() handles empty queue (sets both front and back) | 3 | |
| dequeue() removes from front correctly | 5 | |
| dequeue() updates back when queue becomes empty | 3 | |
| dequeue() throws NoSuchElementException when empty | 3 | |
| peek() returns front without removing | 3 | |
| All QueueTest tests pass | 4 | |

**Exercise 2 Total: ___ / 30**

---

## Exercise 3: ArraySortedList (35 points)

| Criteria | Points | Self-Score |
|----------|--------|------------|
| add() inserts elements in sorted order | 8 | |
| add() correctly shifts elements when inserting | 4 | |
| getMin() returns smallest element | 3 | |
| getMax() returns largest element | 3 | |
| indexOf() finds elements (ideally using binary search) | 5 | |
| contains() works correctly | 3 | |
| remove() maintains sorted order | 3 | |
| Handles duplicates correctly | 3 | |
| All SortedListTest tests pass | 3 | |

**Exercise 3 Total: ___ / 35**

---

## Overall Assessment

**Total Score: ___ / 100**

### Grading Scale
- 90-100: Excellent - You've mastered data structures as abstractions
- 80-89: Good - Solid understanding with minor gaps
- 70-79: Satisfactory - Basic concepts understood
- Below 70: Needs work - Review the material and try again

### Reflection Questions

Answer these honestly:

1. **Did you design interfaces before implementation?**
   - [ ] Yes, I understood the interface contract first
   - [ ] Partially, I sometimes looked at tests to understand
   - [ ] No, I jumped straight to implementation

2. **Did you understand the trade-offs between array and linked implementations?**
   - [ ] Yes, I can explain when to use each
   - [ ] Partially, I understand the basics
   - [ ] No, I'm not sure of the differences

3. **What was the hardest part?**

   _Your answer:_

4. **Which implementation did you find easiest? Why?**

   _Your answer:_

5. **If you had to implement the same interface differently, what would you change?**

   _Your answer:_

---

## Design Quality Checklist

Beyond just passing tests, check:

- [ ] Methods are focused and do one thing
- [ ] Error handling is consistent (same messages as tests expect)
- [ ] Private helper methods are used appropriately
- [ ] No code duplication
- [ ] Variable names are clear and meaningful

---

## Extension Challenge (Bonus)

If you completed the extensions, add your self-assessment:

| Extension | Completed? | Notes |
|-----------|------------|-------|
| Implement iterator pattern | [ ] | |
| Add insertAt(index, element) to LinkedList | [ ] | |
| Create CircularQueue | [ ] | |
| Implement PriorityQueue | [ ] | |
