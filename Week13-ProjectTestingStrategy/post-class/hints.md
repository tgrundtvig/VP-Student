# Week 13 Hints: Test Suite Report

## Writing Model Tests

### Hint 1: Test record factory methods
Every record with a `create()` method should be tested:
```java
@Test
void createGeneratesUniqueId() {
    Entity e1 = Entity.create("Name1", ...);
    Entity e2 = Entity.create("Name2", ...);
    assertNotEquals(e1.id(), e2.id());
}
```

### Hint 2: Test modification methods return new instances
Records are immutable, so modification methods should return new objects:
```java
@Test
void modificationReturnsNewInstance() {
    Entity original = Entity.create("Name", ...);
    Entity modified = original.withNewValue("New");
    assertNotEquals(original.someField(), modified.someField());
    assertEquals(original.id(), modified.id()); // Same entity
}
```

### Hint 3: Test toString for display purposes
If your record has a custom toString, test it:
```java
@Test
void toStringIncludesTitle() {
    Entity entity = Entity.create("My Title", ...);
    assertTrue(entity.toString().contains("My Title"));
}
```

## Writing Repository Tests

### Hint 4: Start with the basic CRUD cycle
```java
@Test
void saveThenFindById() {
    Entity entity = Entity.create("Test", ...);
    repo.save(entity);
    Optional<Entity> found = repo.findById(entity.id());
    assertTrue(found.isPresent());
    assertEquals("Test", found.get().name());
}
```

### Hint 5: Test the "not found" case
```java
@Test
void findByIdNotFound() {
    assertTrue(repo.findById("non-existent").isEmpty());
}
```

### Hint 6: Test domain queries with setup data
```java
@Test
void findByCategoryReturnsOnlyMatching() {
    repo.save(Entity.create("A", ..., "cat-1"));
    repo.save(Entity.create("B", ..., "cat-2"));
    repo.save(Entity.create("C", ..., "cat-1"));

    List<Entity> results = repo.findByCategory("cat-1");

    assertEquals(2, results.size());
    assertTrue(results.stream().allMatch(e -> e.categoryId().equals("cat-1")));
}
```

### Hint 7: Test delete
```java
@Test
void deleteRemovesEntity() {
    Entity entity = Entity.create("Test", ...);
    repo.save(entity);
    repo.delete(entity.id());
    assertTrue(repo.findById(entity.id()).isEmpty());
}
```

## Writing Controller Tests

### Hint 8: The setup pattern
Every controller test needs the same setup:
```java
private MockYourView mockView;
private InMemoryYourRepository repo;
private YourController controller;

@BeforeEach
void setUp() {
    mockView = new MockYourView();
    repo = new InMemoryYourRepository();
    controller = new YourController(mockView, repo);
}
```

### Hint 9: Test the happy path first
```java
@Test
void addEntitySavesAndShowsSuccess() {
    Entity entity = Entity.create("Test", ...);
    mockView.queueEntityInput(entity);

    controller.addEntity();

    assertTrue(repo.findById(entity.id()).isPresent());
    assertEquals(1, mockView.getSuccessMessages().size());
}
```

### Hint 10: Test the cancel path
```java
@Test
void addEntityCancelledDoesNotSave() {
    mockView.queueEntityInput(null); // Simulates user cancel

    controller.addEntity();

    assertTrue(repo.findAll().isEmpty());
    assertTrue(mockView.getSuccessMessages().isEmpty());
}
```

### Hint 11: Test list/display operations
```java
@Test
void listEntitiesShowsAllFromRepository() {
    repo.save(Entity.create("A", ...));
    repo.save(Entity.create("B", ...));

    controller.listEntities();

    assertEquals(1, mockView.getDisplayedLists().size());
    assertEquals(2, mockView.getDisplayedLists().get(0).size());
}
```

## Writing Mock Implementations

### Hint 12: Follow the queue-and-record pattern
```java
public class MockYourView implements YourView {
    // Queues for programmed responses
    private final Queue<Entity> entitiesToReturn = new LinkedList<>();

    // Records of what was displayed
    private final List<String> successMessages = new ArrayList<>();

    // Programming methods
    public void queueEntityInput(Entity entity) {
        entitiesToReturn.add(entity);
    }

    // Verification methods
    public List<String> getSuccessMessages() {
        return List.copyOf(successMessages);
    }

    // Interface implementation
    @Override
    public Entity promptForNewEntity() {
        return entitiesToReturn.poll();
    }

    @Override
    public void showSuccess(String message) {
        successMessages.add(message);
    }
}
```

### Hint 13: Handle all interface methods
Even if you do not test every method yet, the mock must implement all of them.
For methods you are not testing yet, provide minimal implementations:
```java
@Override
public void showDetail(Entity entity) {
    // Not verified in current tests
}
```

## Writing InMemoryRepository

### Hint 14: HashMap is your friend
```java
public class InMemoryYourRepository implements YourRepository {
    private final Map<String, Entity> storage = new HashMap<>();

    @Override
    public void save(Entity entity) {
        storage.put(entity.id(), entity);
    }

    @Override
    public Optional<Entity> findById(String id) {
        return Optional.ofNullable(storage.get(id));
    }

    @Override
    public List<Entity> findAll() {
        return new ArrayList<>(storage.values());
    }

    @Override
    public void delete(String id) {
        storage.remove(id);
    }
}
```

### Hint 15: Domain queries use streams
```java
@Override
public List<Entity> findByCategory(String categoryId) {
    return storage.values().stream()
            .filter(e -> e.categoryId().equals(categoryId))
            .toList();
}
```

## Common Issues

### Issue 1: "I cannot test X because the interface does not have a method for it"
This is a design discovery. Add the missing method to your interface design.
Document this in the DESIGN_CHANGES field.

### Issue 2: "The test is too complicated"
If setting up a test requires many lines of queue programming, the operation might be too
complex. Consider breaking it into smaller Controller methods.

### Issue 3: "I do not know what to assert"
Think about what should be TRUE after the operation:
- Was something saved? Check the repository.
- Was something displayed? Check the mock's recorded output.
- Was a message shown? Check the mock's message lists.

### Issue 4: "My mock gets complicated"
If your mock needs many queues, your View interface might have too many prompt methods.
Consider grouping related inputs into a single method that returns a record.

## After Completing

Your test code and mock implementations will be transferred to your real exam project in Week 14.
Keep this document as a reference during the 2-week implementation period.
