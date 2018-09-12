
package com.rubiconproject.exception;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * This is an Exception when Response status is 404, not found
 * <p>
 * @author jenniec
 *
 */
public class NotFoundException extends WebApplicationException {

	private static final long serialVersionUID = 1L;

    /**
     * Create a HTTP 404 (Not Found) exception.
     * @param message the String that is the entity of the 404 response.
     */
    public NotFoundException(String message) {
      super(Response.status(Response.Status.NOT_FOUND).entity(message).type(MediaType.TEXT_PLAIN).build());
    }

}
