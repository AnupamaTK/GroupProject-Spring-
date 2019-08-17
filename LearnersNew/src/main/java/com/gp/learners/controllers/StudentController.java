package com.gp.learners.controllers;


import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


import com.gp.learners.entities.Package;
import com.gp.learners.entities.Student;
import com.gp.learners.entities.mapObject.StudentPackageMapWrapper;
import com.gp.learners.repositories.StudentRepository;
import com.gp.learners.service.StudentService;


@RestController
@CrossOrigin(origins="*",allowedHeaders="*",maxAge=3600)
public class StudentController {
	
	@Autowired
	StudentService studentService;
	
	@Autowired
	StudentRepository studentRepository;
	
	@PostMapping("/student/register")
	public Student StudentRegister(@RequestBody Student student) {
		System.out.println(student);
		return studentRepository.save(student);
	}
	
	@GetMapping("/students")
	public List<Student> studetsList(){
		return studentRepository.findAll();
	}
	
	
	//get student following package(output-->packId,Title)
	@GetMapping("student/package/{id}")
	public List<Integer> studentPakage(@PathVariable("id") Integer id) {
		return studentService.packageListId(id);
	}
	
	//get student following package(output-->packId,Title)
	@GetMapping("student/package/list/{id}")
	public List<Package> studentPakageList(@PathVariable("id") Integer id) {
			return studentService.packageList(id);
	}
	
	
	//add student package details to the database
	@PostMapping("student/package/{stuId}")
	public Object studentPackageAdd(@PathVariable("stuId") Integer id,@RequestBody StudentPackageMapWrapper object) {
		
		return studentService.packageAdd(id,object);
		
	}
	
	//delete student package details from the database
	@DeleteMapping("student/package/{stuId}/{pacId}")
	public ResponseEntity<Void> studentPackageDelete(@PathVariable("stuId") Integer stuId,@PathVariable("pacId") Integer pacId ) {
			
		if(studentService.packageDelete(stuId,pacId).equals("success")) {
			System.out.println("ok2");
			return ResponseEntity.noContent().build();
		}
		System.out.println("Ok1");
		return ResponseEntity.notFound().build();
			
	}
	
	//student Course Fee Details(return the --> courseFeeId,amount,date,method)
	@GetMapping("student/coursefees/{stuId}/{pacId}")
	public List<Object> courseFees(@PathVariable("stuId") Integer stuId,@PathVariable("pacId") Integer pacId) {
			return studentService.courseFeeList(stuId,pacId);
	}
	
}
