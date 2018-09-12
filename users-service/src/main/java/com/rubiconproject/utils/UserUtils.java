package com.rubiconproject.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;

import com.rubiconproject.user.User;

/**
 * This is a utility class for {@code User} object. 
 * This class aids validating User's required data fields 
 * @author jenniec
 *
 */
public class UserUtils {
	public static final Pattern VALID_EMAIL_ADDRESS_REGEX = 
		    Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
	public static final Pattern VALID_BIRTHDAY_REGEX = 
		    Pattern.compile("(0?[1-9]|1[012])-(0?[1-9]|[12][0-9]|3[01])-((19|20)\\d\\d)");
	
	/**
	 * @param emailStr
	 * @return true if this is valid email format
	 */
	public static boolean validEmail(String emailStr) {
		if (StringUtils.isBlank(emailStr)) {
			return false;
		}
		Matcher matcher = VALID_EMAIL_ADDRESS_REGEX .matcher(emailStr);
        return matcher.find();
	}
	
	/**
	 * @param bdayStr
	 * @return true if this is valid birthday format.
	 * Valid birthday formats are: M-D-YYYY or MM-DD-YYYY
	 */
	public static boolean validBirthday(String bdayStr) {
		if (StringUtils.isNotBlank(bdayStr)) {
			Matcher matcher = VALID_BIRTHDAY_REGEX .matcher(bdayStr);
	        return matcher.find();
		}
		return true;
	}
	
	/**
	 * @param user
	 * @return true if this user has all required data fields
	 */
	public static boolean hasRequiredFields(User user) {
		return (StringUtils.isNotBlank(user.getFirstName()) && 
				StringUtils.isNotBlank(user.getLastName()) && 
				validEmail(user.getEmail()) &&
				validBirthday(user.getBirthday()));
	}
}
