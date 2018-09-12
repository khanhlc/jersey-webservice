package com.rubiconproject.user;

import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

/**
 * Tests for {@code UserManager} object
 * @author jenniec
 *
 */
public class UserManagerTest {
	private UserManager userManager;
	
    @Before
    public void setUp() throws Exception {
    	userManager = UserManager.getInstance();
    }
    
	@Test
	public void testIsExistentUser() {
    	User user = new User( 100,"Jennie", "Chau", "Female", 
				"Chau.Jennie@yahoo.com", "Los Angeles", 
				"CA", "01-01-1999");
    	assertTrue(userManager.isExistentUser(user));
	}
		
	@Test
	public void testCreateUser() {
    	User user = new User( 100,"Jennie", "Chau", "Female", 
				"Chau.Jennie@yahoo.com", "Los Angeles", 
				"CA", "01-01-1999");
    	
    	assertTrue(100 < userManager.createUser(user));
	}
	
}
