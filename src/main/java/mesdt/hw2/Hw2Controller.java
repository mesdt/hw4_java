package mesdt.hw2;

import mesdt.hw2.repo.StudentRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class Hw2Controller {

	@Autowired
	private StudentRepository students;

	@RequestMapping(method = RequestMethod.GET, value = "/")
	public String index(Model vars) {
		vars.addAttribute("students", students.findAll());
		return "students";
	}

}
