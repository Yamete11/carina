package test;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.IOException;
import java.io.StringReader;

public class ApiTest {
    private static final String BASE_URL = "https://api.openweathermap.org/data/2.5/weather";
    private static final String APP_ID = "704a8626cb386c5140ae3c353615adca";

    @Test
    public void testValidCityAndCountry() {
        Response response = RestAssured
                .given()
                .queryParam("q", "London,uk")
                .queryParam("APPID", APP_ID)
                .get(BASE_URL);

        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertTrue(response.getBody().asString().contains("London"));
    }

    @Test
    public void testInvalidCity(){
        Response response = RestAssured
                .given()
                .queryParam("q", "InvalidCity")
                .queryParam("APPID", APP_ID)
                .get(BASE_URL);

        Assert.assertEquals(response.getStatusCode(), 404);
        Assert.assertTrue(response.getBody().asString().contains("city not found"));
    }

    @Test
    public void testValidCityWithoutCountry(){
        Response response = RestAssured
                .given()
                .queryParam("q", "London")
                .queryParam("APPID", APP_ID)
                .get(BASE_URL);

        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertTrue(response.getBody().asString().contains("London"));
    }

    @Test
    public void testInvalidApiKey(){
        Response response = RestAssured
                .given()
                .queryParam("q", "London")
                .queryParam("APPID", "InvalidApiKey")
                .get(BASE_URL);

        Assert.assertEquals(response.getStatusCode(), 401);
        Assert.assertTrue(response.getBody().asString().contains("Invalid API key"));
    }

    @Test
    public void testResponseHeader(){
        Response response = RestAssured
                .given()
                .queryParam("q", "London,uk")
                .queryParam("APPID", APP_ID)
                .get(BASE_URL);

        String contentType = response.getHeader("Content-type");
        Assert.assertEquals(contentType, "application/json; charset=utf-8");

        String server = response.getHeader("Server");
        Assert.assertNotNull(server);
        Assert.assertEquals(server, "openresty");
    }

    @Test
    public void testXmlResponseAgainstXsd() throws SAXException, IOException {
        Response response = RestAssured
                .given()
                .queryParam("q", "London,uk")
                .queryParam("APPID", APP_ID)
                .queryParam("mode", "xml")
                .get(BASE_URL);

        Assert.assertEquals(response.getStatusCode(), 200);
        SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
        Schema schema = factory.newSchema(new StreamSource(getClass().getClassLoader().getResourceAsStream("weather-schema.xsd")));

        Validator validator = schema.newValidator();
        validator.validate(new StreamSource(new StringReader(response.getBody().asString())));
    }

    @Test
    public void testBadRequest() {
        Response response = RestAssured
                .given()
                .queryParam("q", "")
                .queryParam("APPID", APP_ID)
                .get(BASE_URL);

        Assert.assertEquals(response.getStatusCode(), 400);
        Assert.assertTrue(response.getBody().asString().contains("Nothing to geocode"));
    }

    @Test
    public void testContentTypeHeader() {
        Response response = RestAssured
                .given()
                .queryParam("q", "London,uk")
                .queryParam("APPID", APP_ID)
                .get(BASE_URL);

        String contentType = response.getHeader("Content-Type");
        Assert.assertNotNull(contentType);
        Assert.assertTrue(contentType.contains("application/json"));
    }


    @Test
    public void testResponseTime() {
        Response response = RestAssured
                .given()
                .queryParam("q", "London,uk")
                .queryParam("APPID", APP_ID)
                .get(BASE_URL);

        long responseTime = response.getTime();
        Assert.assertTrue(responseTime < 2000, "Response time is too slow");
    }

    @Test
    public void testCoordinatesRequest() {
        Response response = RestAssured
                .given()
                .queryParam("lat", "51.5074")
                .queryParam("lon", "-0.1278")
                .queryParam("APPID", APP_ID)
                .get(BASE_URL);

        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertTrue(response.getBody().asString().contains("London"));
    }



}
