package mesdt.hw2;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import mesdt.hw2.core.Score;
import mesdt.hw2.core.Student;
import mesdt.hw2.core.Subject;
import mesdt.hw2.repo.ScoreRepository;
import mesdt.hw2.repo.StudentRepository;
import mesdt.hw2.repo.SubjectRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Hw2Service {

	@Autowired
	private StudentRepository students;

	@Autowired
	private SubjectRepository subjects;

	@Autowired
	private ScoreRepository scorez;

	public Collection<Student> students() {
		return students.findAll();
	}

	public Student student(Long id) {
		return students.getOne(id);
	}

	public Map<Subject, Integer> scores(Student student) {
		// XXX: ручной джоин!
		Map<Subject, Integer> scores = new HashMap<>();
		for (Subject subject : subjects.findAll()) {
			scores.put(subject, null);
		}
		for (Score score : scorez.findByIdStudent(student)) {
			scores.put(score.getSubject(), score.getScore());
		}
		return scores;
	}

	public Student createStudent(String name) {
		return students.save(new Student(name));
	}

	public void deleteStudent(Long id) {
		students.delete(id);
	}

}
