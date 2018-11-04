package vn.edu.saigontech.SGTEnglishClub.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import vn.edu.saigontech.SGTEnglishClub.DAOs.studentDAO;
import vn.edu.saigontech.SGTEnglishClub.Models.Specialization;
import vn.edu.saigontech.SGTEnglishClub.Models.Student;

@RestController
@RequestMapping("rest")
public class studentRESTController {
	@Autowired
	private studentDAO studentDAO;
	
	@RequestMapping(value = "/StudentREST", method = RequestMethod.GET)
	public List<Student> getAllStudent(){
		return studentDAO.getAllStudent();
	}
	
	@RequestMapping(value = "/StudentREST/{id}", method = RequestMethod.GET)
	public Student getAllStudent(@PathVariable int id){
		return studentDAO.getStudentByID(id);
	}
	
	@RequestMapping(value = "/StudentREST", method = RequestMethod.POST)
	public Student addStu(@RequestBody Student newStu ){
		
		
		return studentDAO.addStudent(newStu);
	}
	
	@RequestMapping(value = "/StudentREST/{id}", method = RequestMethod.DELETE)
	public String deleteStudent(@PathVariable int id){
		return studentDAO.deleteStudent(id);
	}
	
	@RequestMapping(value = "/StudentREST", method = RequestMethod.PUT)
	public Student updateStu(@RequestBody Student targetStu ){
		
		
		return studentDAO.updateStudent(targetStu);
	}
	
	
}
