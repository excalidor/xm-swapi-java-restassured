package starter.testImplementations;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.module.jsv.JsonSchemaValidator;
import starter.api.ApiLayer;
import starter.models.Film;
import starter.models.Films;
import starter.models.Person;

import java.util.*;

import static org.hamcrest.MatcherAssert.assertThat;


public class TestImplementations extends ApiLayer {

    protected static final String FILMS_ENDPOINT = "films/";
    protected static final String PEOPLE_ENDPOINT = "people/";

    protected List<String> characters;

    protected List<Film> filmresults;
    protected Films films;
    protected ObjectMapper objectMapper = new ObjectMapper();

    protected void getLattestRelease() {
        LOGGER.info("Requestting films endpoint");
        LOGGER.info("*** Searching for the latest release. ***");
        get(properties.getProperty("baseUrl") + FILMS_ENDPOINT);

        try {
            films = objectMapper.readValue(response.asString(), Films.class);
        } catch (Exception e) {
            LOGGER.error("Skipping unsuported data entry.");
        }
        filmresults = films.getResults();
        Collections.sort(filmresults, (f1, f2) -> f2.getRelease_date().compareTo(f1.getRelease_date()));
        System.out.println("***********************************");
        LOGGER.info("\n\nThe latest release: " + filmresults.get(0).getTitle() + "\n");
        System.out.println("\n" + filmresults.get(0).toString() + "\n");
        System.out.println("***********************************");
    }

    protected void getTallestCharacterFromLatestRelease() {
        LOGGER.info("*** Searching for the tallest character from the latest release.***");
        characters = filmresults.get(0).getCharacters();
        List<Person> personList = new ArrayList<>();
        for (int i = 0; i < characters.size(); i++) {
            if (properties.getProperty("requestLogging").equals("true")) LOGGER.info(characters.get(i));
            get(characters.get(i));
            try {
                personList.add(objectMapper.readValue(response.asString(), Person.class));
            } catch (Exception e) {
                LOGGER.error("Skipping unsuported data entry.");
            }
        }
        personList.sort(Comparator.comparingInt(Person::getHeight).reversed());
        System.out.println("***********************************");
        System.out.println("\n" + personList.get(0).toString() + "\n");
        LOGGER.info("\n\n" + personList.get(0).getName() + " - " + personList.get(0).getHeight() + " is the tallest person from the movie " + filmresults.get(0).getTitle() + "\n");
        System.out.println("***********************************");
    }

    protected void getTallestCharacterFromAllMovies() {
        LOGGER.info("*** Searching the tallest character from all Star Wars movies. ***");
        List<String> chars = new ArrayList<>();
        for (Film f : films.getResults()) {
            chars.addAll(f.getCharacters());
        }
        Set<String> uniqueChars = new HashSet<>();
        for (String character : chars) {
            uniqueChars.add(character);
        }
        List<Person> allPersonList = new ArrayList<>();
        for (String element : uniqueChars) {
            get(element);
            try {
                allPersonList.add(objectMapper.readValue(response.asString(), Person.class));
            } catch (Exception e) {
                LOGGER.info("Skipping unsuported data entry.");
            }
        }
        allPersonList.sort(Comparator.comparingInt(Person::getHeight).reversed());
        System.out.println("\n***********************************");
        LOGGER.info("\n\n" + allPersonList.get(0).getName() + " - " + allPersonList.get(0).getHeight() + " is the tallest person from all the SW movies.\n");
        System.out.println("\n" + allPersonList.get(0).toString() + "\n");
        System.out.println("***********************************");
    }

    protected void validateApiResponse() {

        get(properties.getProperty("baseUrl") + PEOPLE_ENDPOINT);
        String responseBody = response.asString();
        assertThat("Json validation failed.", responseBody, JsonSchemaValidator.matchesJsonSchema(jsonSchema));
        System.out.println("***********************************");
        LOGGER.info("Json schema validation successful.");
        System.out.println("***********************************");
    }

    protected static final String jsonSchema = "{\n" +
            "  \"$schema\": \"http://json-schema.org/draft-07/schema#\",\n" +
            "  \"type\": \"object\",\n" +
            "  \"properties\": {\n" +
            "    \"count\": { \"type\": \"integer\" },\n" +
            "    \"next\": { \"type\": [\"string\", \"null\"] },\n" +
            "    \"previous\": { \"type\": [\"string\", \"null\"] },\n" +
            "    \"results\": {\n" +
            "      \"type\": \"array\",\n" +
            "      \"items\": {\n" +
            "        \"type\": \"object\",\n" +
            "        \"properties\": {\n" +
            "          \"name\": { \"type\": \"string\" },\n" +
            "          \"height\": { \"type\": \"string\" },\n" +
            "          \"mass\": { \"type\": \"string\" },\n" +
            "          \"hair_color\": { \"type\": \"string\" },\n" +
            "          \"skin_color\": { \"type\": \"string\" },\n" +
            "          \"eye_color\": { \"type\": \"string\" },\n" +
            "          \"birth_year\": { \"type\": \"string\" },\n" +
            "          \"gender\": { \"type\": \"string\" },\n" +
            "          \"homeworld\": { \"type\": \"string\" },\n" +
            "          \"films\": { \"type\": \"array\", \"items\": { \"type\": \"string\" } },\n" +
            "          \"species\": { \"type\": \"array\", \"items\": { \"type\": \"string\" } },\n" +
            "          \"vehicles\": { \"type\": \"array\", \"items\": { \"type\": \"string\" } },\n" +
            "          \"starships\": { \"type\": \"array\", \"items\": { \"type\": \"string\" } },\n" +
            "          \"created\": { \"type\": \"string\", \"format\": \"date-time\" },\n" +
            "          \"edited\": { \"type\": \"string\", \"format\": \"date-time\" },\n" +
            "          \"url\": { \"type\": \"string\" }\n" +
            "        },\n" +
            "        \"required\": [\"name\", \"height\", \"mass\", \"hair_color\", \"skin_color\", \"eye_color\", \"birth_year\", \"gender\", \"homeworld\", \"films\", \"species\", \"vehicles\", \"starships\", \"created\", \"edited\", \"url\"]\n" +
            "      }\n" +
            "    }\n" +
            "  },\n" +
            "  \"required\": [\"count\", \"next\", \"previous\", \"results\"]\n" +
            "}";
}
