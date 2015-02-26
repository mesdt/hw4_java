package mesdt.hw2.repo;

import java.util.List;

import mesdt.hw2.core.Score;
import mesdt.hw2.core.Student;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ScoreRepository extends JpaRepository<Score, Score.Id> {

	List<Score> findByIdStudent(Student student);

	Long deleteByIdStudent(Student student);

}
