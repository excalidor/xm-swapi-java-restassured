package starter.testsuite;

import org.testng.annotations.Test;
import starter.testImplementations.TestImplementations;


public class SWAPITest extends TestImplementations {

    @Test
    public void StarWarsAPITest() {

        getLattestRelease();
        getTallestCharacterFromLatestRelease();
        getTallestCharacterFromAllMovies();
        validateApiResponse();
    }
}
