# Week 14 Post-Class: Set Up Your Exam Project

## Overview

This is the final post-class exercise of the course. After this, you transition from design
to implementation. Your task is to set up the actual Maven project for your exam project,
create the package structure, and copy in your interfaces, records, and tests.

**This is not a document exercise --- this is a real project setup.**

**Time estimate: 2-3 hours for setup, then 2 weeks for implementation**

## What You Will Create

Your actual exam project! Specifically:

1. **A Maven project** with the correct pom.xml
2. **Package structure** matching your architecture layers
3. **Interface files** (actual Java interfaces)
4. **Record files** (actual Java records)
5. **Test files** (actual JUnit tests from Week 13)
6. **Mock and InMemory implementations** (for tests to compile)
7. **A git repository** with your initial commit

## Step-by-Step Instructions

### Step 1: Create the Maven Project

Create a new Maven project (in IntelliJ or command line):
- GroupId: `dk.viprogram`
- ArtifactId: your project name (e.g., `recipe-manager`)
- Java version: 21

### Step 2: Set Up pom.xml

Ensure your pom.xml has Java 21, JUnit 5, and appropriate plugins.
See the pre-class reading for a complete template.

### Step 3: Create Package Structure

Create the packages matching your Week 11 architecture:
```
src/main/java/dk/viprogram/yourproject/
    model/
    repository/
    repository/inmemory/
    view/
    view/console/
    view/mock/
    controller/

src/test/java/dk/viprogram/yourproject/
    model/
    repository/
    controller/
```

### Step 4: Create Interface Files

Translate your Week 12 interface specifications into actual Java files:
- View interface with all method signatures and JavaDoc
- Repository interface(s) with all method signatures and JavaDoc
- Any Strategy or other behavioral interfaces

### Step 5: Create Record Files

Translate your record specifications into actual Java files:
- All entity records with fields, factory methods, and toString
- Any enums needed

### Step 6: Create Test Double Files

From your Week 13 test code:
- MockView class implementing your View interface
- InMemoryRepository class(es) implementing your Repository interface(s)

### Step 7: Create Test Files

Copy your Week 13 test code into actual test files:
- Model tests
- Repository tests
- Controller tests (these will not compile yet --- that is expected)

### Step 8: Verify Compilation

```bash
mvn clean compile
```

The project should compile. Tests may not pass yet (controller tests need the Controller
class), but they should compile.

### Step 9: Initialize Git

```bash
git init
git add .
git commit -m "Initial project setup: interfaces, records, tests, and mocks"
```

### Step 10: Create the Launch Checklist

Fill in `ProjectLaunchChecklist.java` to verify your setup is complete.

## Running the Meta-Tests

For this exercise's meta-tests (in THIS Maven project, not your exam project):

```bash
mvn test
```

These tests verify that you have documented your project setup in the
`ProjectLaunchChecklist.java` file.

## Success Criteria

Your exam project is properly set up when:
- [ ] `mvn clean compile` succeeds
- [ ] Package structure matches your architecture
- [ ] All interfaces have complete method signatures and JavaDoc
- [ ] All records have fields, factory methods, and toString
- [ ] MockView and InMemoryRepository are created
- [ ] Test files exist (some may fail compilation until Controller exists)
- [ ] Git repository is initialized with an initial commit
- [ ] You know what to implement first (Phase 1 of your roadmap)

## Tips

1. **Copy carefully** --- typos in interface method signatures cause test failures later
2. **Compile often** --- fix compilation errors immediately, do not let them accumulate
3. **Git commit after each file** --- if something breaks, you can always go back
4. **Start Phase 1 tonight** --- records are the easiest to implement, get them done now
5. **Do not start the View** --- it is the last thing to implement, not the first
