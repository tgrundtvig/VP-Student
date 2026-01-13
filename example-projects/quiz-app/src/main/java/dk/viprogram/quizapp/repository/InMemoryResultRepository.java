package dk.viprogram.quizapp.repository;

import dk.viprogram.quizapp.model.QuizResult;

import java.util.*;

/**
 * In-memory implementation of ResultRepository.
 */
public class InMemoryResultRepository implements ResultRepository {

    private final Map<String, QuizResult> storage = new HashMap<>();

    @Override
    public QuizResult save(QuizResult result) {
        storage.put(result.id(), result);
        return result;
    }

    @Override
    public Optional<QuizResult> findById(String id) {
        return Optional.ofNullable(storage.get(id));
    }

    @Override
    public List<QuizResult> findAll() {
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
    public List<QuizResult> findByQuizId(String quizId) {
        return storage.values().stream()
                .filter(r -> r.quizId().equals(quizId))
                .toList();
    }

    @Override
    public List<QuizResult> findByPlayerName(String playerName) {
        return storage.values().stream()
                .filter(r -> r.playerName().equalsIgnoreCase(playerName))
                .toList();
    }

    @Override
    public List<QuizResult> getTopScores(String quizId, int limit) {
        return storage.values().stream()
                .filter(r -> r.quizId().equals(quizId))
                .sorted(Comparator.comparingInt(QuizResult::score).reversed())
                .limit(limit)
                .toList();
    }
}
