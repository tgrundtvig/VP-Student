package dk.viprogram.week07;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests for understanding and using SortingStrategy.
 * These tests demonstrate how to create and use sorting strategies.
 */
@DisplayName("SortingStrategy")
class SortingStrategyTest {

    @Nested
    @DisplayName("Basic Strategy Usage")
    class BasicStrategyUsage {

        @Test
        @DisplayName("sort by name ascending")
        void sortByNameAscending() {
            List<Player> players = new ArrayList<>(List.of(
                    new Player("Charlie", 90),
                    new Player("Alice", 100),
                    new Player("Bob", 85)
            ));

            // Create a strategy that compares by name
            SortingStrategy<Player> byName = (p1, p2) -> p1.name().compareTo(p2.name());

            // Sort using the strategy
            players.sort(byName::compare);

            assertEquals("Alice", players.get(0).name());
            assertEquals("Bob", players.get(1).name());
            assertEquals("Charlie", players.get(2).name());
        }

        @Test
        @DisplayName("sort by score descending")
        void sortByScoreDescending() {
            List<Player> players = new ArrayList<>(List.of(
                    new Player("Charlie", 90),
                    new Player("Alice", 100),
                    new Player("Bob", 85)
            ));

            // Create a strategy that compares by score (highest first)
            SortingStrategy<Player> byScoreDesc = (p1, p2) -> p2.score() - p1.score();

            players.sort(byScoreDesc::compare);

            assertEquals("Alice", players.get(0).name());   // 100
            assertEquals("Charlie", players.get(1).name()); // 90
            assertEquals("Bob", players.get(2).name());     // 85
        }

        @Test
        @DisplayName("sort by level ascending")
        void sortByLevelAscending() {
            List<Player> players = new ArrayList<>(List.of(
                    new Player("Charlie", 90, 5),
                    new Player("Alice", 100, 10),
                    new Player("Bob", 85, 3)
            ));

            SortingStrategy<Player> byLevel = (p1, p2) -> p1.level() - p2.level();

            players.sort(byLevel::compare);

            assertEquals("Bob", players.get(0).name());     // level 3
            assertEquals("Charlie", players.get(1).name()); // level 5
            assertEquals("Alice", players.get(2).name());   // level 10
        }
    }

    @Nested
    @DisplayName("Reversed Strategy")
    class ReversedStrategy {

        @Test
        @DisplayName("reversed strategy inverts order")
        void reversedInvertsOrder() {
            List<Player> players = new ArrayList<>(List.of(
                    new Player("Charlie", 90),
                    new Player("Alice", 100),
                    new Player("Bob", 85)
            ));

            SortingStrategy<Player> byNameAsc = (p1, p2) -> p1.name().compareTo(p2.name());
            SortingStrategy<Player> byNameDesc = byNameAsc.reversed();

            players.sort(byNameDesc::compare);

            assertEquals("Charlie", players.get(0).name());
            assertEquals("Bob", players.get(1).name());
            assertEquals("Alice", players.get(2).name());
        }
    }

    @Nested
    @DisplayName("Chained Strategies")
    class ChainedStrategies {

        @Test
        @DisplayName("thenComparing breaks ties")
        void thenComparingBreaksTies() {
            List<Player> players = new ArrayList<>(List.of(
                    new Player("Charlie", 90, 5),
                    new Player("Alice", 90, 10),   // Same score as Charlie
                    new Player("Bob", 85, 3)
            ));

            // Sort by score descending, then by name ascending for ties
            SortingStrategy<Player> byScore = (p1, p2) -> p2.score() - p1.score();
            SortingStrategy<Player> byName = (p1, p2) -> p1.name().compareTo(p2.name());
            SortingStrategy<Player> combined = byScore.thenComparing(byName);

            players.sort(combined::compare);

            // Alice and Charlie both have score 90, so alphabetically Alice first
            assertEquals("Alice", players.get(0).name());
            assertEquals("Charlie", players.get(1).name());
            assertEquals("Bob", players.get(2).name());
        }

        @Test
        @DisplayName("multiple thenComparing calls")
        void multipleThenComparing() {
            List<Player> players = new ArrayList<>(List.of(
                    new Player("Charlie", 90, 5),
                    new Player("Alice", 90, 5),    // Same score and level as Charlie
                    new Player("Bob", 90, 5),     // Same as above
                    new Player("Diana", 85, 10)
            ));

            // Sort by score desc, then level desc, then name asc
            SortingStrategy<Player> byScore = (p1, p2) -> p2.score() - p1.score();
            SortingStrategy<Player> byLevel = (p1, p2) -> p2.level() - p1.level();
            SortingStrategy<Player> byName = (p1, p2) -> p1.name().compareTo(p2.name());

            SortingStrategy<Player> combined = byScore.thenComparing(byLevel).thenComparing(byName);

            players.sort(combined::compare);

            // All with score 90 come first, same level so sorted by name
            assertEquals("Alice", players.get(0).name());
            assertEquals("Bob", players.get(1).name());
            assertEquals("Charlie", players.get(2).name());
            assertEquals("Diana", players.get(3).name());
        }
    }

    @Nested
    @DisplayName("Strategy as Method Parameter")
    class StrategyAsParameter {

        /**
         * This helper method demonstrates passing a strategy as a parameter.
         */
        private List<Player> sortPlayers(List<Player> players, SortingStrategy<Player> strategy) {
            List<Player> result = new ArrayList<>(players);
            result.sort(strategy::compare);
            return result;
        }

        @Test
        @DisplayName("same method, different strategies")
        void sameMethodDifferentStrategies() {
            List<Player> players = List.of(
                    new Player("Charlie", 90),
                    new Player("Alice", 100),
                    new Player("Bob", 85)
            );

            // Same sortPlayers method, different strategies
            List<Player> byName = sortPlayers(players, (p1, p2) -> p1.name().compareTo(p2.name()));
            List<Player> byScore = sortPlayers(players, (p1, p2) -> p2.score() - p1.score());

            assertEquals("Alice", byName.get(0).name());  // Alphabetically first
            assertEquals("Alice", byScore.get(0).name()); // Highest score
        }
    }
}
