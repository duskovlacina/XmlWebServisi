package com.xml_web_services.rest;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.xml_web_services.domain.MessagesHistory;
import com.xml_web_services.domain.User;
import com.xml_web_services.spring_services.UserService;

@RestController
@RequestMapping("/user")
@CrossOrigin(origins = {"http://localhost:8081",
"http://localhost:8082", "http://localhost:8088"}, maxAge = 3600, allowCredentials = "true")
public class UserController {

	@Autowired
	private UserService userService;
	
	@RequestMapping(value = "/loginUser", method = RequestMethod.POST)
	public ResponseEntity<User> loginUser(@RequestBody User user, HttpServletRequest request) {
		User us = this.userService.find(user);
		if (us != null && us.getUserStatus().equals("ACTIVATED")) {
			request.getSession().setAttribute("user", us);
			return new ResponseEntity<>(HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
	
	@RequestMapping(value = "/loginAdmin", method = RequestMethod.POST)
	public ResponseEntity<User> loginAdmin(@RequestBody User user, HttpServletRequest request) {
		User us = this.userService.find(user);
		if (us != null && us.getUserStatus().equals("ACTIVATED")) {
			request.getSession().setAttribute("admin", us);
			return new ResponseEntity<>(HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
	
	
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public ResponseEntity<User> logout(HttpServletRequest request, @RequestParam String u) {
		if(u.equals("admin")) {
			request.getSession().removeAttribute("admin");
		}else if (u.equals("user")) {
			request.getSession().removeAttribute("user");
		}
			
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@RequestMapping(value = "/account", method = RequestMethod.GET)
	public ResponseEntity<User> account(HttpServletRequest request, @RequestParam String u) {
		User user = (User) request.getSession().getAttribute("user");
		User admin = (User) request.getSession().getAttribute("admin");
		if (user != null && u.equals("user")) {
			return new ResponseEntity<>(user, HttpStatus.OK);
		} else if (admin != null && u.equals("admin")) {
			return new ResponseEntity<>(admin, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
	}
	
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public ResponseEntity<User> register(@RequestBody User user, HttpServletRequest request) {
		boolean b = this.userService.registerUser(user);
		if (b) {
			return new ResponseEntity<>(HttpStatus.CREATED);
		}
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
	
	@RequestMapping(value = "/not-activated", method = RequestMethod.GET)
	public ResponseEntity<List<User>> getNotActivatedUsers() {
		return new ResponseEntity<>(this.userService.getPendingUsers(), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/activated", method = RequestMethod.GET)
	public ResponseEntity<List<User>> getActivatedUsers() {
		return new ResponseEntity<>(this.userService.getAcceptedUsers(), HttpStatus.OK);
	}
	
	// TODO: should be put
	@RequestMapping(value = "/approve", method = RequestMethod.POST)
	public ResponseEntity<User> approveRegistration(@RequestBody String id) {
		boolean b = this.userService.setStatus(id.substring(0, id.length()-1), "ACTIVATED");
		if (b) return new ResponseEntity<>(HttpStatus.OK);
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<User> delete(@PathVariable long id) {
		boolean flag = this.userService.deleteUser(id);
		if (flag) {
			return new ResponseEntity<>(HttpStatus.OK);
		}else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@RequestMapping(value = "/block/{id}", method = RequestMethod.GET)
	public ResponseEntity<User> decline(@PathVariable long id) {
		boolean b = this.userService.blockUser(id);
		if (b) return new ResponseEntity<>(HttpStatus.OK);
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	@RequestMapping(value = "/chat", method = RequestMethod.GET)
	public ResponseEntity<List<MessagesHistory>> chat(HttpServletRequest request) {
		return new ResponseEntity<>(this.userService.messagesHistory((User)request.getSession().getAttribute("user")), HttpStatus.OK);
	}
	
	
}
