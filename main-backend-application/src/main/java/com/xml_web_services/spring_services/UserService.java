package com.xml_web_services.spring_services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xml_web_services.domain.Message;
import com.xml_web_services.domain.MessagesHistory;
import com.xml_web_services.domain.User;
import com.xml_web_services.repositories.UserRepository;


@Service
public class UserService {

	@Autowired
	private MessageService messageService;
	
	@Autowired
	private UserRepository userRepository;
	
	public boolean registerUser(User user) {
		user.setUserStatus("PENDING");
		user.setUserType("USER");
		User u = this.userRepository.save(user);
		if (u != null) {
			return true;
		}
		return false;
	}
	
	public User find(User user) {
		User us = this.userRepository.findByUsername(user.getUsername());
		System.out.println(us);
		System.out.println(user.getUsername());
		if (us == null) return null;
		
		if (us.getPassword().equals(user.getPassword())) {
			System.out.println(us.getUsername() + "zljzlj");
			return us;
		} else {
			return null;
		}
	}

	public boolean setStatus(String id, String userStatus) {
		System.out.println("Sssssssssssssssssssssssssssss");
		System.out.println(id);
		System.out.println(userStatus);
		Optional<User> optionalUser = this.userRepository.findById(Long.parseLong(id));
		if (optionalUser.isPresent()) {
			User user = optionalUser.get();
			user.setUserStatus(userStatus);
			this.userRepository.save(user);
			return true;
		}else {
			return false;
		}
	}
	
	public List<User> getAcceptedUsers() {
		return this.userRepository.findByUserStatusAndUserType("ACTIVATED", "USER");
	}
	
	public List<User> getPendingUsers() {
		return this.userRepository.findByUserStatusAndUserType("PENDING", "USER");
	}

	public List<MessagesHistory> messagesHistory(User user) {
		List<MessagesHistory> history = new ArrayList<>();
		List<Message> messages = this.messageService.findByUser(user);
		/*for (Message m : messages) {
			Reply r = this.replyService.getReply(m);
			MessagesHistory ch = new MessagesHistory();
			ch.setMessage(m);
			ch.setReply(r);
			history.add(ch);
		}*/
		// TODO : CHECK THIS
		return history;
	}

	public boolean deleteUser(long id) {
		this.userRepository.deleteById(id);
		return true;
	}

	public boolean blockUser(long id) {
		User user = this.userRepository.findById(id).get();
		user.setUserStatus("DECLINED");
		this.userRepository.save(user);
		return true;
	}
	

}
