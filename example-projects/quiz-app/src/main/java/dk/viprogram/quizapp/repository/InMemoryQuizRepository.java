package dk.viprogram.quizapp.repository;

import dk.viprogram.quizapp.model.Quiz;

import java.util.*;

/**
 * In-memory implementation of QuizRepository.
 */
public class InMemoryQuizRepository implements QuizRepository {

    private final Map<String, Quiz> storage = new HashMap<>();

    @Override
    public Quiz save(Quiz quiz) {
        storage.put(quiz.id(), quiz);
        return quiz;
    }

    @Override
    public Optional<Quiz> findById(String id) {
        return Optional.ofNullable(storage.get(id));
    }

    @Override
    public List<Quiz> findAll() {
        return new ArrayList<>(storage.values());
    }

    @Override
    public void deleteById(String id) {
        storage.remove(id);
    }

    @Override
    public long count() {
        return storage.size();
    }

    @Override
    public List<Quiz> findByCategory(String category) {
        return storage.values().stream()
                .filter(quiz -> quiz.questions().stream()
                        .anyMatch(q -> q.getCategory().equalsIgnoreCase(category)))
                .toList();
    }
}
