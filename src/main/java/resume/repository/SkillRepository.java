package resume.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import resume.entity.Skill;

public interface SkillRepository extends JpaRepository<Skill, Long> {
}