package mesdt.hw2.repo;

import mesdt.hw2.core.Student;

import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {

}
