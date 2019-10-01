package com.gp.learners.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.gp.learners.entities.InstructorNotification;

public interface InstructorNotificationRepository extends JpaRepository<InstructorNotification, Integer>{
	
	@Query(value="select * from instructor_notification i where i.instructor_id = :instructorId and i.view = :type order by date DESC",nativeQuery=true)
	public List<InstructorNotification> findNotificationByInstructorId(@Param("instructorId") Integer instructorId,@Param("type") Integer type);
}
