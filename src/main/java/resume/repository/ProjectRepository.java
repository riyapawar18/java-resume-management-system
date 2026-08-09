package resume.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import resume.entity.Project;

public interface ProjectRepository extends JpaRepository<Project, Long> {
}