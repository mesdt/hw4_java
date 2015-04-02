package mesdt.hw2.svc;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.transaction.Transactional;

import mesdt.hw2.core.Score;
import mesdt.hw2.core.Student;
import mesdt.hw2.core.Subject;
import mesdt.hw2.repo.ScoreRepository;
import mesdt.hw2.repo.StudentRepository;
import mesdt.hw2.repo.SubjectRepository;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Hw2ServiceImpl implements Hw2Service {

	@Autowired
	private StudentRepository studentz;

	@Autowired
	private SubjectRepository subjectz;

	@Autowired
	private ScoreRepository scorez;

	protected final Log log = LogFactory.getLog(getClass());

	@Override
	public Collection<Student> students() {
		return studentz.findAll();
	}

	@Override
	public Student student(Long id) {
		return studentz.getOne(id);
	}

	@Override
	public Map<Subject, Integer> scores(Student student) {
		// XXX: ручной джоин!
		Map<Subject, Integer> scores = new HashMap<>();
		for (Subject subject : subjectz.findAll()) {
			scores.put(subject, null);
		}
		for (Score score : scorez.findByIdStudent(student)) {
			scores.put(score.getSubject(), score.getScore());
		}
		return scores;
	}

	@Override
	public Map<String, Integer> scoresAsStr(Student student) {
		// XXX: ручной джоин!
		Map<String, Integer> scores = new HashMap<>();
		for (Subject subject : subjectz.findAll()) {
			scores.put(subject.getName(), null);
		}
		for (Score score : scorez.findByIdStudent(student)) {
			scores.put(score.getSubject().getName(), score.getScore());
		}
		return scores;
	}
        
	@Override
	@Transactional
	public void setScores(Student student, Long[] subjectIds, Integer[] scores) {
		scorez.deleteByIdStudent(student);
		int i = 0;
		for (Subject subject : subjectz.findAll(Arrays.asList(subjectIds))) {
			if (scores[i] != null) {
				scorez.save(new Score(student, subject, scores[i]));
			}
			++i;
		}
	}

	@Override
	public Student createStudent(String name) {
		return studentz.save(new Student(name));
	}

	@Override
	public void deleteStudent(Long id) {
		studentz.delete(id);
	}
	@Override
	public void updateStudent(Long id, String name) {
		Student student = student(id);
                student.setName(name);
                studentz.save(student);
	}
}
