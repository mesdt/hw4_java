package mesdt.hw2;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class Hw2Controller {

	@RequestMapping(method = RequestMethod.GET, value = "/")
	public String index() {
		return "students";
	}

}
