import org.junit.jupiter.api.Test;
import org.mongo.JsonProcessor;

public class JsonProcessorTest {
    @Test
    public void validateJsonFileSuccessTest() {
        JsonProcessor jsonProcessor = new JsonProcessor();
        String correctJson = "{\n" +
                "  \"FirstName\": \"abc\",\n" +
                "  \"LastName\": \"defg\",\n" +
                "  \"Address\":\n" +
                "    {\n" +
                "      \"Line1\": \"abc\",\n" +
                "      \"state\": \"CA\"\n" +
                "    }\n" +
                "}";

        assert (jsonProcessor.isValidJsonFile(correctJson) == true);
    }

    @Test
    public void validateJsonFormatFileFailedTest() {
        JsonProcessor jsonProcessor = new JsonProcessor();
        String invalidJson =
                "{\n" +
                        "  \"FirstName\" \"error\": \"fname\",\n" +
                        "  \"LastName\": \"lname\",\n" +
                        "  \"Address\":\n" +
                        "\n" +
                        "      \"Line1\": \"abc\",\n" +
                        "      \"state\": \"CA\"\n" +
                        "    }\n" +
                        "}";

        assert (jsonProcessor.isValidJsonFile(invalidJson) == false);
    }

    @Test
    public void validateJsonContainArrayFileFailedTest() {
        JsonProcessor jsonProcessor = new JsonProcessor();
        String invalidJson = "{\n" +
                "Service\": [\"Site SEO Review\", \"abc\"]" +
                "}";

        assert (jsonProcessor.isValidJsonFile(invalidJson) == false);
    }


    @Test
    public void flatJsonFileSuccessTest() {
        JsonProcessor jsonProcessor = new JsonProcessor();
        String json = "{\n" +
                "  \"FirstName\": \"fname\",\n" +
                "  \"Address\":\n" +
                "    {\n" +
                "      \"Line1\": \"abc\",\n" +
                "      \"state\": \"CA\"\n" +
                "    }\n" +
                "}";

        String flatJson = jsonProcessor.flatJsonFile(json);
        String expectedFlatJson = "{\"FirstName\":\"fname\",\"Address.Line1\":\"abc\",\"Address.state\":\"CA\"}";
        assert (expectedFlatJson.equals(flatJson));
    }

    @Test
    public void flatJsonFileFailedTest() {
        JsonProcessor jsonProcessor = new JsonProcessor();
        String invalidJson = "{\n" +
                "  \"FirstName\": \"fname\",\n" +
                "  \"Address\":\n" +
                "    {\n" +
                "      \"Line1\": \"abc\",\n" +
                "      \"Line\" \"CA\"\n" +
                "    }\n" +
                "}";

        String flatJson = jsonProcessor.flatJsonFile(invalidJson);
        assert (flatJson == null);
    }


}
