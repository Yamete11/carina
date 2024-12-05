package test;

import com.zebrunner.carina.api.GetWeatherMethod;
import com.zebrunner.carina.core.registrar.ownership.MethodOwner;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class APICarinaTest {

    @Test
    @MethodOwner(owner = "demo")
    public void testValidCityAndCountry() {

        String city = "London,uk";
        GetWeatherMethod getWeatherMethod = new GetWeatherMethod(city);
        Response response = getWeatherMethod.callAPIExpectSuccess();

        Assert.assertTrue(response.getBody().asString().contains("London"));
    }

    @Test
    @MethodOwner(owner = "demo")
    public void testInvalidCity() {
        String city = "InvalidCity";
        GetWeatherMethod getWeatherMethod = new GetWeatherMethod(city);
        Response response = getWeatherMethod.callAPI();

        Assert.assertTrue(response.getBody().asString().contains("city not found"));
    }

    @Test
    @MethodOwner(owner = "demo")
    public void testInvalidApiKey() {
        String city = "London";
        String invalidApiKey = "InvalidApiKey";

        GetWeatherMethod getWeatherMethod = new GetWeatherMethod(city, invalidApiKey);

        Response response = getWeatherMethod.callAPI();

        Assert.assertTrue(response.getBody().asString().contains("Invalid API key"));
    }

    @Test
    @MethodOwner(owner = "demo")
    public void testValidCityWithoutCountry() {
        String city = "London";
        GetWeatherMethod getWeatherMethod = new GetWeatherMethod(city);
        Response response = getWeatherMethod.callAPIExpectSuccess();

        Assert.assertTrue(response.getBody().asString().contains("London"));
    }

    @Test
    @MethodOwner(owner = "demo")
    public void testResponseHeader() {
        String city = "London,uk";
        GetWeatherMethod getWeatherMethod = new GetWeatherMethod(city);
        Response response = getWeatherMethod.callAPIExpectSuccess();

        String contentType = response.getHeader("Content-type");
        Assert.assertEquals(contentType, "application/json; charset=utf-8");

        String server = response.getHeader("Server");
        Assert.assertNotNull(server);
        Assert.assertEquals(server, "openresty");
    }

    @Test
    @MethodOwner(owner = "demo")
    public void testBadRequest() {
        String city = "";
        GetWeatherMethod getWeatherMethod = new GetWeatherMethod(city);
        Response response = getWeatherMethod.callAPI();

        Assert.assertEquals(response.getStatusCode(), 400);
        Assert.assertTrue(response.getBody().asString().contains("Nothing to geocode"));
    }

    @Test
    @MethodOwner(owner = "demo")
    public void testContentTypeHeader() {
        String city = "London,uk";
        GetWeatherMethod getWeatherMethod = new GetWeatherMethod(city);
        Response response = getWeatherMethod.callAPIExpectSuccess();

        String contentType = response.getHeader("Content-Type");
        Assert.assertNotNull(contentType);
        Assert.assertTrue(contentType.contains("application/json"));
    }

    @Test
    @MethodOwner(owner = "demo")
    public void testResponseTime() {
        String city = "London,uk";
        GetWeatherMethod getWeatherMethod = new GetWeatherMethod(city);
        Response response = getWeatherMethod.callAPIExpectSuccess();

        long responseTime = response.getTime();
        Assert.assertTrue(responseTime < 2000, "Response time is too slow");
    }

}
