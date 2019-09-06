package com.gp.learners.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.gp.learners.entities.User;

public interface UserRepository extends JpaRepository<User, Integer>{
	
	
	User findByEmailAndPassword(String email,String password);
	
	List<User> findUserIdStaffIdNameRoleStatusByRoleNot(Integer role);
	
	@Query(value="select * from user u WHERE u.user_id = :userId ",nativeQuery=true)
	User findByUserId(@Param("userId")Integer userId);
}