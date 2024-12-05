package test;

import com.zebrunner.carina.api.*;
import com.zebrunner.carina.core.registrar.ownership.MethodOwner;
import io.restassured.response.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;

import java.lang.invoke.MethodHandles;

import static org.testng.AssertJUnit.assertEquals;

public class APITest {

    private static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());


    @Test
    @MethodOwner(owner = "demo")
    public void testGetAll() {
        GetUserMethod api = new GetUserMethod();
        Response response = api.callAPI();

        LOGGER.info(response.asString());

        assertEquals(200, response.getStatusCode());
    }

    @Test
    @MethodOwner(owner = "demo")
    public void testGetSpecificUser() {
        GetUserMethod api = new GetUserMethod(3L);
        Response response = api.callAPI();

        LOGGER.info(response.asString());

        assertEquals(200, response.getStatusCode());
    }

    @Test
    @MethodOwner(owner = "demo")
    public void testDeleteUser() {
        GetUserMethod getAllApi = new GetUserMethod("users/lastUserId");
        Response response = getAllApi.callAPI();

        LOGGER.info("Get the last id: " + response.asString());
        assertEquals(200, response.getStatusCode());

        LOGGER.info("Deleting user with ID: " + response.getBody());

        Long lastUserId = response.as(Long.class);

        DeleteUserMethod deleteApi = new DeleteUserMethod(lastUserId);
        Response deleteResponse = deleteApi.callAPI();

        LOGGER.info("Delete response: " + deleteResponse.asString());

        assertEquals(204, deleteResponse.getStatusCode());
    }

    @Test
    public void testCreateUser() {
        PostUserMethod api = new PostUserMethod();

        Response response = api.callAPI();

        LOGGER.info(response.asString());

        assertEquals(response.getStatusCode(), 201);
    }


}
