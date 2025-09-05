import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class MovieManagerTest {

    private MovieManager manager;

    @BeforeEach
    void setUp() {
        manager = new MovieManager();
    }

    @Test
    void testDefaultConstructor() {
        MovieManager defaultManager = new MovieManager();
        assertNotNull(defaultManager.findAll());
        assertEquals(0, defaultManager.findAll().length);
    }

    @Test
    void testCustomConstructor() {
        MovieManager customManager = new MovieManager(3);
        assertNotNull(customManager.findAll());
        assertEquals(0, customManager.findAll().length);
    }

    @Test
    void testAddMovieToEmptyManager() {
        manager.addMovie("The Matrix");

        String[] movies = manager.findAll();
        assertEquals(1, movies.length);
        assertEquals("The Matrix", movies[0]);
    }

    @Test
    void testAddMultipleMovies() {
        manager.addMovie("Movie 1");
        manager.addMovie("Movie 2");
        manager.addMovie("Movie 3");

        String[] movies = manager.findAll();
        assertEquals(3, movies.length);
        assertEquals("Movie 1", movies[0]);
        assertEquals("Movie 2", movies[1]);
        assertEquals("Movie 3", movies[2]);
    }

    @Test
    void testFindAllEmpty() {
        String[] movies = manager.findAll();
        assertNotNull(movies);
        assertEquals(0, movies.length);
    }

    @Test
    void testFindLastWhenEmpty() {
        String[] lastMovies = manager.findLast();
        assertNotNull(lastMovies);
        assertEquals(0, lastMovies.length);
    }

    @Test
    void testFindLastWithLessMoviesThanLimit() {
        manager.addMovie("Pulp Fiction");
        manager.addMovie("Fight Club");

        String[] lastMovies = manager.findLast();
        assertEquals(2, lastMovies.length);
        assertEquals("Fight Club", lastMovies[0]);
        assertEquals("Pulp Fiction", lastMovies[1]);
    }

    @Test
    void testFindLastWithExactLimit() {
        MovieManager customManager = new MovieManager(3);
        customManager.addMovie("Movie 1");
        customManager.addMovie("Movie 2");
        customManager.addMovie("Movie 3");

        String[] lastMovies = customManager.findLast();
        assertEquals(3, lastMovies.length);
        assertEquals("Movie 3", lastMovies[0]);
        assertEquals("Movie 2", lastMovies[1]);
        assertEquals("Movie 1", lastMovies[2]);
    }

    @Test
    void testFindLastWithMoreMoviesThanLimit() {
        MovieManager customManager = new MovieManager(2);
        customManager.addMovie("Movie 1");
        customManager.addMovie("Movie 2");
        customManager.addMovie("Movie 3");

        String[] lastMovies = customManager.findLast();
        assertEquals(2, lastMovies.length);
        assertEquals("Movie 3", lastMovies[0]);
        assertEquals("Movie 2", lastMovies[1]);
    }

    @Test
    void testFindLastWithCustomLimit() {
        MovieManager customManager = new MovieManager(1);
        customManager.addMovie("Single Movie");

        String[] lastMovies = customManager.findLast();
        assertEquals(1, lastMovies.length);
        assertEquals("Single Movie", lastMovies[0]);
    }

    @Test
    void testIntegrationScenario() {
        MovieManager customManager = new MovieManager(4);

        // Добавляем фильмы
        customManager.addMovie("The Shawshank Redemption");
        customManager.addMovie("The Godfather");
        customManager.addMovie("The Dark Knight");
        customManager.addMovie("Pulp Fiction");
        customManager.addMovie("Forrest Gump");

        // Проверяем findAll
        String[] allMovies = customManager.findAll();
        assertEquals(5, allMovies.length);
        assertEquals("The Shawshank Redemption", allMovies[0]);
        assertEquals("The Godfather", allMovies[1]);
        assertEquals("The Dark Knight", allMovies[2]);
        assertEquals("Pulp Fiction", allMovies[3]);
        assertEquals("Forrest Gump", allMovies[4]);

        // Проверяем findLast (должны вернуться последние 4)
        String[] lastMovies = customManager.findLast();
        assertEquals(4, lastMovies.length);
        assertEquals("Forrest Gump", lastMovies[0]);
        assertEquals("Pulp Fiction", lastMovies[1]);
        assertEquals("The Dark Knight", lastMovies[2]);
        assertEquals("The Godfather", lastMovies[3]);
    }

    @Test
    void testAddManyMovies() {
        for (int i = 1; i <= 10; i++) {
            manager.addMovie("Movie " + i);
        }

        String[] movies = manager.findAll();
        assertEquals(10, movies.length);
        assertEquals("Movie 1", movies[0]);
        assertEquals("Movie 10", movies[9]);
    }
}