package com.rubiconproject.utils;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import com.rubiconproject.user.User;

/**
 * Tests for {@code UserUtils} object
 * @author jenniec
 *
 */
public class UserUtilsTest {

    /**
     * Test email regex with different email: valid, non-valid, null, empty 
     */
    @Test
    public void testValidEmail() {
    	String validEmail= "user-email@mydomain.com";
    	assertTrue (UserUtils.validEmail(validEmail));
    	String validEmailWithSubdomain= "user-email@mydomain.hk.com";
    	assertTrue (UserUtils.validEmail(validEmailWithSubdomain));
    	String emailWithNoDomain = "wrongemail.com";
       	assertFalse (UserUtils.validEmail(emailWithNoDomain));   
    	String emailWithNoUserName = "@domain.com";
       	assertFalse (UserUtils.validEmail(emailWithNoUserName)); 
    	String emptyEmail = "";
       	assertFalse (UserUtils.validEmail(emptyEmail));
    	String nullEmail = null;
       	assertFalse (UserUtils.validEmail(nullEmail));
       	
    }
    
    /**
     * Test birthday regex with different dates: valid date (MM-DD-YYYY), no date , wrong date, wrong date format 
     */
    @Test
    public void testValidBirthday() {
    	String validBirthday= "02-29-1990";
    	assertTrue (UserUtils.validBirthday(validBirthday));
       	String birthdayOutofRange = "02-33-1990";
    	assertFalse (UserUtils.validBirthday(birthdayOutofRange));      	
    	String emptyBirthday = "";
       	assertFalse(UserUtils.validEmail(emptyBirthday));
       	
    }
    
    @Test
	public void testHasRequiredFields() {
    	User validFullUser = new User( 1,"Jennie", "Chau", "Female", 
				"Chau.Jennie@yahoo.com", "Los Angeles", 
				"CA", "01-01-1999");
    	assertTrue(UserUtils.hasRequiredFields(validFullUser));
    	User validUserWithOnlyRequiredFields = new User( 1,"Jennie", "Chau", null, 
				"Chau.Jennie@yahoo.com", null, 
				null, null);
    	assertTrue(UserUtils.hasRequiredFields(validUserWithOnlyRequiredFields));
    	User invalidUserMissingFirstname = new User( 1,null, "Chau", "Female", 
				"Chau.Jennie@yahoo.com", "Los Angeles", 
				"CA", "01-01-1999");
    	assertFalse(UserUtils.hasRequiredFields(invalidUserMissingFirstname));
    	User invalidUserBadEmail = new User( 1,null, "Chau", "Female", 
				"Chau.Jennieyahoo.com", "Los Angeles", 
				"CA", "01-01-1999");
    	assertFalse(UserUtils.hasRequiredFields(invalidUserBadEmail));
	}
    
}
