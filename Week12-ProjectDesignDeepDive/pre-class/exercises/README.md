# Week 12 Pre-Class Exercises: Interface Specification

## Overview

This exercise asks you to create a detailed specification of your project's interfaces. Unlike
Week 11 where you listed interface names and rough methods, this week you define the complete
method signatures with parameter types, return types, and JavaDoc descriptions.

**Time estimate: 60-90 minutes**

## Instructions

1. Open `src/main/java/dk/viprogram/week12/InterfaceSpecification.java`
2. Fill in the complete specification for each of your project's interfaces
3. Include method signatures, parameter types, return types, and JavaDoc text
4. Run the tests to verify completeness

## What to Fill In

### View Interface Specification
- Every method your View interface needs
- Full method signatures: `ReturnType methodName(ParamType param)`
- JavaDoc description for each method
- How the mock version would work

### Repository Interface Specification
- All CRUD methods with types
- Domain-specific query methods
- JavaDoc describing the contract (not the implementation)

### Record Specifications
- Complete field lists with types for each record
- Factory method signatures
- Modification method signatures (methods that return new instances)

### Workflow Description
- How the Controller uses the interfaces together
- Step-by-step flow for at least 2 user operations
- Which interface methods are called in what order

## Running the Tests

```bash
mvn test
```

Tests verify:
- View interface has at least 3 method signatures
- Repository interface has at least 4 method signatures
- At least 2 records are fully specified
- Workflow descriptions are present and detailed

## Tips

- Reference the example projects' interfaces for format inspiration
- Use specific types: `List<Recipe>` not `List<Object>`
- Every prompt method should return a specific type
- Every display method should take specific parameters
- Remember: JavaDoc describes what, not how
