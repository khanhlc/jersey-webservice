package com.rubiconproject.rest;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;

import org.glassfish.grizzly.http.server.HttpServer;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.rubiconproject.rest.Main;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Tests for {@code UserService} object
 * @author jenniec
 *
 */
public class UserServiceTest {

    private HttpServer server;
    private WebTarget target;
    public static final int RESPONSE_STATUS_OK = 200;
    public static final int RESPONSE_STATUS_CREATED = 201;
    public static final int RESPONSE_STATUS_BAD_REQUEST = 400;
    public static final int RESPONSE_STATUS_NOT_FOUND = 404;
    
    @Before
    public void setUp() throws Exception {
        server = Main.startServer();
        Client c = ClientBuilder.newClient();
        target = c.target(Main.BASE_URI);
    }

    @After
    public void tearDown() throws Exception {
        server.shutdownNow();
    }

    /**
     * Test: Get all users
     */
    @Test
    public void testGetAllUsers() {
        String response = target.path("users/all").request().get(String.class);
        assertNotNull(response);
    }
    
    @Test
    public void testGetExistentUser() {
        Response response = target.path("users/101").request().get();
        assertNotNull(response);
    }
    
    @Test
    public void testGetNonExistentUser() {
        Response response = target.path("users/2000").request().get();
        assertEquals(404, response.getStatus());     
    }
   
    @Test
    public void testAddValidUser() {
    	String jsonUser = "{\"firstName\":\"Laura\",\"lastName\":\"Stuart\",\"email\":\"lstuart@example.com\","
    			+ "\"city\":\"Braintree\",\"state\":\"MA\",\"birthday\":\"1-22-1980\"}";
        Response response = target.path("users/add").request().post(Entity.json(jsonUser));
        assertEquals(RESPONSE_STATUS_CREATED, response.getStatus()); 
    }
  
    /**
     * Test adding a user that doesn't have all required fields, which are 
     * first name, last name, and email
     */
    @Test
    public void testAddUserWithMissingRequiredFields() {
    	String jsonUser = "{\"firstName\":\"Laura\",\"lastName\":\"Stuart\"}";
        Response response = target.path("users/add").request().post(Entity.json(jsonUser));
        assertEquals(RESPONSE_STATUS_BAD_REQUEST, response.getStatus()); 
    	jsonUser = "{\"firstName\":\"Laura\",\"email\":\"lstuart@example.com\"}";
        response = target.path("users/add").request().post(Entity.json(jsonUser));
        assertEquals(RESPONSE_STATUS_BAD_REQUEST, response.getStatus());         
    }
    
    @Test
    public void testAddUserWithInvalidEmail() {
    	String jsonUser = "{\"firstName\":\"Laura\",\"lastName\":\"Stuart\",\"email\":\"lstuartexample.com\","
    			+ "\"city\":\"Braintree\",\"state\":\"MA\",\"birthday\":\"1-22-1980\"}";
        Response responseMsg = target.path("users/add").request().post(Entity.json(jsonUser));
        assertEquals(RESPONSE_STATUS_BAD_REQUEST, responseMsg.getStatus()); 
    }
 
    @Test
    public void testAddUserWithInvalidBirthdayFormat() {
    	String jsonUser = "{\"firstName\":\"Laura\",\"lastName\":\"Stuart\",\"email\":\"lstuart@example.com\","
    			+ "\"city\":\"Braintree\",\"state\":\"MA\",\"birthday\":\"122-1980\"}";
        Response responseMsg = target.path("users/add").request().post(Entity.json(jsonUser));
        assertEquals(RESPONSE_STATUS_BAD_REQUEST, responseMsg.getStatus()); 
    }

    @Test
    public void testUpdateUser() {
    	String jsonUser = "{\"id\": 100, \"firstName\":\"Laura\",\"lastName\":\"Stuart\",\"email\":\"lstuart@example.com\","
    			+ "\"city\":\"Braintree\",\"state\":\"MA\",\"birthday\":\"1-22-1980\"}";
        Response responseMsg = target.path("users/update/100").request().put(Entity.json(jsonUser));
        assertEquals(RESPONSE_STATUS_OK, responseMsg.getStatus()); 
    }
 
    @Test
    public void testUpdateNonExistentUser() {
    	String jsonUser = "{\"id\": 100, \"firstName\":\"Laura\",\"lastName\":\"Stuart\",\"email\":\"lstuart@example.com\","
    			+ "\"city\":\"Braintree\",\"state\":\"MA\",\"birthday\":\"1-22-1980\"}";
        Response responseMsg = target.path("users/update/1000").request().put(Entity.json(jsonUser));
        assertEquals(RESPONSE_STATUS_NOT_FOUND, responseMsg.getStatus()); 
    }
    
    @Test
    public void testDeleteExistentUser() {
        Response response = target.path("users/remove/101").request().delete();
        assertEquals(RESPONSE_STATUS_OK, response.getStatus());     
    } 
    
    @Test
    public void testDeleteNonExistentUser() {
        Response responseMsg = target.path("users/remove/1000").request().delete();
        assertEquals(RESPONSE_STATUS_NOT_FOUND, responseMsg.getStatus());     
    }  
}
