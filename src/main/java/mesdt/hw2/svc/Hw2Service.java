package mesdt.hw2.svc;

import java.util.Collection;
import java.util.Map;

import mesdt.hw2.core.Student;
import mesdt.hw2.core.Subject;

public interface Hw2Service {

	Collection<Student> students();

	Student student(Long id);

	Map<Subject, Integer> scores(Student student);

	void setScores(Student student, Long[] subjectIds, Integer[] scores);

	Student createStudent(String name);

	void deleteStudent(Long id);
        
        void updateStudent(Long id, String name);
        
        public Map<String, Integer> scoresAsStr(Student student);

}
