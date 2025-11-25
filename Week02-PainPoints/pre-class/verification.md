# Pre-Class Verification Checklist

Before coming to class, make sure you can answer "yes" to these questions:

## Reading Comprehension

- [ ] I understand what "code smells" are and why they matter
- [ ] I can identify at least 3 code smells in my Week 1 game
- [ ] I understand the difference between coupling and cohesion
- [ ] I recognize why "it works" isn't the same as "it's good code"
- [ ] I understand why testing reveals design problems

## Exercise Understanding

### EnhancedCharacter Exercise
- [ ] I tried adding special abilities to the Character class
- [ ] I noticed how the class became more complex
- [ ] I struggled with managing different ability effects
- [ ] I found it hard to test abilities in isolation
- [ ] I realized adding more abilities would make it worse

### GameMode Exercise
- [ ] I attempted to implement different game modes
- [ ] I noticed the amount of if-else statements needed
- [ ] I saw code duplication between modes
- [ ] I realized adding a new mode would require many changes
- [ ] I found it difficult to test each mode independently

### ComplexBattle Exercise
- [ ] I tried implementing team battles with status effects
- [ ] I noticed how quickly the complexity grew
- [ ] I struggled with tracking effects per character
- [ ] I found the processBattleRound method becoming huge
- [ ] I realized testing all combinations would be nearly impossible

## Key Realizations

- [ ] I experienced firsthand that extending my Week 1 code is harder than expected
- [ ] I understand that tightly coupled code makes changes cascade
- [ ] I see why testing is difficult when everything depends on everything
- [ ] I recognize that my code has low cohesion (classes doing too many things)
- [ ] I'm frustrated with the current approach and ready to learn a better way

## Testing Insights

- [ ] I tried writing the tests and found many were incomplete or impossible
- [ ] I noticed I couldn't test features in isolation
- [ ] I realized I needed to manipulate internal state for testing
- [ ] I saw that my tests were fragile and would break easily
- [ ] I understood that I was testing implementation details, not behavior

## Documentation

- [ ] I made notes about what was difficult to implement
- [ ] I documented which code smells I encountered
- [ ] I listed the problems I faced when trying to extend features
- [ ] I noted which tests were impossible or very difficult to write

## Mindset Check

- [ ] I understand that struggling with these exercises is the point
- [ ] I'm not frustrated with myself - the code structure is the problem
- [ ] I'm curious about how interfaces will solve these problems
- [ ] I'm ready to discuss my struggles in class
- [ ] I see the value in experiencing bad design before learning good design

## Questions to Bring to Class

Write down 2-3 questions you have after doing these exercises:

1. _________________________________________________
2. _________________________________________________
3. _________________________________________________

## Common Issues (It's OK if you experienced these!)

- **"I couldn't complete the exercises"** - That's fine! The struggle is the lesson
- **"My tests don't work"** - Perfect! That shows the design problems
- **"I had to copy-paste code"** - Yes! That's a code smell
- **"Everything breaks when I change one thing"** - Exactly the problem we're addressing
- **"I don't know how to test this properly"** - You've discovered the core issue

## Ready for Class?

If you've genuinely attempted the exercises and experienced the frustrations, you're ready! The goal wasn't to succeed - it was to experience why our current approach doesn't scale.

Come to class ready to:
- Share what was hardest
- Discuss what frustrated you most
- Learn how interfaces solve these problems
- Appreciate why design matters

Remember: Every professional programmer has written code like this. The important thing is recognizing the problems and being open to learning better approaches!