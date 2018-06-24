package com.xml_web_services.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.xml_web_services.domain.User;


@Repository
public interface UserRepository extends JpaRepository<User, Long>{
	
	User findByUsername(String username);
	List<User> findByUserStatusAndUserType(String userStatus, String userType);
}
