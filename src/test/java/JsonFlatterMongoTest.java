import org.mongo.JsonFlatterMongo;
import org.junit.jupiter.api.Test;

public class JsonFlatterMongoTest {
    @Test
    public void validateJsonFileSuccessTest() {
        JsonFlatterMongo jsonFlatterMongo = new JsonFlatterMongo();
        String correctJson = "{\n" +
                "  \"FirstName\": \"karim\",\n" +
                "  \"LastName\": \"Ibrahim\",\n" +
                "  \"Address\":\n" +
                "    {\n" +
                "      \"Line1\": \"abc\",\n" +
                "      \"state\": \"CA\"\n" +
                "    }\n" +
                "}";

        assert (jsonFlatterMongo.isValidJsonFile(correctJson) == true);
    }

    @Test
    public void validateJsonFileFailedTest() {
        JsonFlatterMongo jsonFlatterMongo = new JsonFlatterMongo();
        String invalidJson = "{\n" +
                "  \"FirstName\" \"error\": \"fname\",\n" +
                "  \"LastName\": \"lname\",\n" +
                "  \"Address\":\n" +
                "\n" +
                "      \"Line1\": \"abc\",\n" +
                "      \"state\": \"CA\"\n" +
                "    }\n" +
                "}";

        assert (jsonFlatterMongo.isValidJsonFile(invalidJson) == false);
    }

    @Test
    public void flatJsonFileSuccessTest() {
        JsonFlatterMongo jsonFlatterMongo = new JsonFlatterMongo();
        String json = "{\n" +
                "  \"FirstName\": \"fname\",\n" +
                "  \"Address\":\n" +
                "    {\n" +
                "      \"Line1\": \"abc\",\n" +
                "      \"state\": \"CA\"\n" +
                "    }\n" +
                "}";

        String flatJson = jsonFlatterMongo.flatJsonFile(json);
        String expectedFlatJson = "{\"FirstName\":\"fname\",\"Address.Line1\":\"abc\",\"Address.state\":\"CA\"}";
        assert (expectedFlatJson.equals(flatJson));
    }

    @Test
    public void flatJsonFileFailedTest() {
        JsonFlatterMongo jsonFlatterMongo = new JsonFlatterMongo();
        String invalidJson = "{\n" +
                "  \"FirstName\": \"fname\",\n" +
                "  \"Address\":\n" +
                "    {\n" +
                "      \"Line1\": \"abc\",\n" +
                "      \"Line\" \"CA\"\n" +
                "    }\n" +
                "}";

        String flatJson = jsonFlatterMongo.flatJsonFile(invalidJson);
        assert (flatJson == null);
    }


}
