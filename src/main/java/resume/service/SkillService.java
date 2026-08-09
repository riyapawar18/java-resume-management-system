package resume.service;

import org.springframework.stereotype.Service;
import resume.entity.Skill;
import resume.repository.SkillRepository;

import java.util.List;

@Service
public class SkillService {

    private final SkillRepository repository;

    public SkillService(SkillRepository repository) {
        this.repository = repository;
    }

    public List<Skill> getAllSkills() {
        return repository.findAll();
    }

    public Skill getSkillById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Skill not found"));
    }

    public void saveSkill(Skill skill) {
        repository.save(skill);
    }

    public void deleteSkill(Long id) {
        repository.deleteById(id);
    }
}