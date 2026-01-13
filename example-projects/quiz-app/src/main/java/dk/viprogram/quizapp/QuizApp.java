package dk.viprogram.quizapp;

import dk.viprogram.quizapp.controller.QuizController;
import dk.viprogram.quizapp.model.*;
import dk.viprogram.quizapp.repository.InMemoryQuizRepository;
import dk.viprogram.quizapp.repository.InMemoryResultRepository;
import dk.viprogram.quizapp.scoring.StandardScoring;
import dk.viprogram.quizapp.view.ConsoleQuizView;

import java.util.List;

/**
 * Main entry point for the Quiz Application.
 *
 * This class:
 * 1. Creates the concrete implementations
 * 2. Seeds some sample data
 * 3. Wires everything together
 * 4. Starts the application
 */
public class QuizApp {

    public static void main(String[] args) {
        // Create repositories
        var quizRepository = new InMemoryQuizRepository();
        var resultRepository = new InMemoryResultRepository();

        // Seed sample quizzes
        seedSampleData(quizRepository);

        // Create view and scoring strategy
        var view = new ConsoleQuizView();
        var scoringStrategy = new StandardScoring();

        // Create controller
        var controller = new QuizController(
                view,
                quizRepository,
                resultRepository,
                scoringStrategy
        );

        // Run
        controller.run();
    }

    /**
     * Creates sample quizzes for demonstration.
     */
    private static void seedSampleData(InMemoryQuizRepository repository) {
        // Java Basics Quiz
        Quiz javaQuiz = Quiz.create(
                "Java Basics",
                "Test your knowledge of Java fundamentals",
                List.of(
                        MultipleChoiceQuestion.create(
                                "Which keyword is used to define a class in Java?",
                                "Java Basics",
                                10,
                                List.of("define", "class", "struct", "type"),
                                1, // "class" is correct
                                "It's the same word in English that describes a category"
                        ),
                        TrueFalseQuestion.create(
                                "Java is a statically typed language.",
                                "Java Basics",
                                5,
                                true,
                                "Think about when type errors are caught"
                        ),
                        MultipleChoiceQuestion.create(
                                "What is the default value of an int variable?",
                                "Java Basics",
                                10,
                                List.of("null", "0", "undefined", "-1"),
                                1, // "0" is correct
                                "Primitive types have numeric defaults"
                        ),
                        TrueFalseQuestion.create(
                                "Strings in Java are mutable.",
                                "Java Basics",
                                5,
                                false,
                                "Think about what happens when you 'modify' a String"
                        )
                )
        );

        // Design Patterns Quiz
        Quiz patternsQuiz = Quiz.create(
                "Design Patterns",
                "Test your understanding of software design patterns",
                List.of(
                        MultipleChoiceQuestion.create(
                                "Which pattern defines an interface for creating objects?",
                                "Creational Patterns",
                                15,
                                List.of("Singleton", "Factory", "Observer", "Strategy"),
                                1, // "Factory" is correct
                                "Think about what manufactures products"
                        ),
                        MultipleChoiceQuestion.create(
                                "Which pattern allows behavior to be selected at runtime?",
                                "Behavioral Patterns",
                                15,
                                List.of("Adapter", "Decorator", "Strategy", "Facade"),
                                2, // "Strategy" is correct
                                "It's about choosing different approaches"
                        ),
                        TrueFalseQuestion.create(
                                "The Repository pattern is used to abstract data persistence.",
                                "Architectural Patterns",
                                10,
                                true,
                                "Think about what we learned in Week 9"
                        )
                )
        );

        repository.save(javaQuiz);
        repository.save(patternsQuiz);
    }
}
