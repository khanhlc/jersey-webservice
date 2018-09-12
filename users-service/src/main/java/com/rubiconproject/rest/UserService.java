package com.rubiconproject.rest;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.rubiconproject.exception.NotFoundException;
import com.rubiconproject.user.UserManager;
import com.rubiconproject.user.User;


/**
 * RESTful Web service that allows basic REST operations on a list of {@code User}: get, create, update, and delete users
 * <p>
 * @author jenniec
 *
 */
@Path("/users")
public class UserService {

	private final UserManager userManager = UserManager.getInstance();

	/**
	 * @return all users in json format
	 */
	@GET
	@Path("/all")
	@Produces(MediaType.APPLICATION_JSON)
	public User[] getAllUsers() {
		return userManager.getUserList().toArray(new User[0]);
	}

	/**
	 * @param id
	 * @return the user object in json format with userId == id
	 */
	@GET
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public User getUser(@PathParam("id") long id){
		User user = userManager.lookupUserById(id);
		if (null != user){
			return user;
		}
		else {
			throw new NotFoundException("User " + id + " not found");
		}
	}

	/**
	 * @param user
	 * @return status of Created, 201, if successfully add user
	 * User needs to have at least first name, last name and email address. 
	 * Otherwise, status of Bad Request, 400, is returned.
	 */
	@POST
	@Path("/add")
	@Produces(MediaType.APPLICATION_JSON)
	public Response addUser(User user){
		if (userManager.validateUser(user)) {
			userManager.createUser(user);			
			return Response.status(Response.Status.CREATED).build();
		}
		else {
			System.out.println("ERROR: User has invalid email address or invalid birthday format(MM-DD-YYYY)");
			return Response.status(Response.Status.BAD_REQUEST).build();      
		}
	}

	/**
	 * @param id
	 * @param user
	 * @return response status of OK, 200, if successfully update user. 
	 * Otherwise, return response status of NOT FOUND, 404
	 */
	@PUT
	@Path("update/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response updateUser(@PathParam("id") long id, User user){
		if(id == user.getId() && userManager.isExistentUser(user)) {
			userManager.updateUser(user);		  
			return Response.status(Response.Status.OK).build();
		} else {
			System.out.println("ERROR: User ID from request and User object do not match or Cannot find this user");
			return Response.status(Response.Status.NOT_FOUND).build();      
		}
	}

	/**
	 * @param id
	 * @return response status of OK, 200, if successfully delete user with userID == id. 
	 * Otherwise, return response status of NOT FOUND, 404
	 */
	@DELETE
	@Path("/remove/{id}")
	public Response deleteUser(@PathParam("id") long id){
		User user = userManager.lookupUserById(id);
		if (null != user){
			userManager.deleteUser(user);
			return Response.status(Response.Status.OK).build();     
		}else {
			System.out.println("Cannot find this user to delete");
			return Response.status(Response.Status.NOT_FOUND).build();      
		}
	}
}