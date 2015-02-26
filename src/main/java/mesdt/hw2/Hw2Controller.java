package mesdt.hw2;

import mesdt.hw2.core.Student;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class Hw2Controller {

	@Autowired
	private Hw2Service hw2;

	protected final Log log = LogFactory.getLog(getClass());

	@RequestMapping(method = RequestMethod.GET, value = "/")
	public String index(Model vars) {
		vars.addAttribute("students", hw2.students());
		return "students";
	}

	@RequestMapping(method = RequestMethod.GET, value = "/students/{id}")
	public String student(@PathVariable Long id, Model vars) {
		Student student = hw2.student(id);
		vars.addAttribute("student", student).addAttribute("scores", hw2.scores(student));
		return "student";
	}

	@RequestMapping(method = RequestMethod.POST, value = "/students")
	public String createStudent(@RequestParam String name) {
		Student student = hw2.createStudent(name);
		return "redirect:/students/" + student.getId();
	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/students/{id}")
	public String deleteStudent(@PathVariable Long id) {
		hw2.deleteStudent(id);
		return "redirect:/";
	}

	@RequestMapping(method = RequestMethod.PUT, value = "/students/{id}/scores")
	public String setScores(@PathVariable Long id, @RequestParam("subjectIds[]") Long[] subjectIds,
			@RequestParam("scores[]") Integer[] scores) {
		Student student = hw2.student(id);
		hw2.setScores(student, subjectIds, scores);
		return "redirect:/students/" + student.getId();
	}

}
