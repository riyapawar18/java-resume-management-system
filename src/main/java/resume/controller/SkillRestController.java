package resume.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import resume.entity.Skill;
import resume.service.SkillService;

import java.util.List;

@RestController
@RequestMapping("/api/skills")
public class SkillRestController {

    private final SkillService skillService;

    public SkillRestController(SkillService skillService) {
        this.skillService = skillService;
    }

    @GetMapping
    public List<Skill> getSkills() {
        return skillService.getAllSkills();
    }

    @GetMapping("/{id}")
    public Skill getSkill(@PathVariable Long id) {
        return skillService.getSkillById(id);
    }

    @PostMapping
    public ResponseEntity<String> createSkill(
            @RequestBody Skill skill) {

        skillService.saveSkill(skill);

        return ResponseEntity.ok(
                "Skill created successfully"
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateSkill(
            @PathVariable Long id,
            @RequestBody Skill skill) {

        Skill existing =
                skillService.getSkillById(id);

        existing.setName(skill.getName());
        existing.setCategory(skill.getCategory());

        skillService.saveSkill(existing);

        return ResponseEntity.ok(
                "Skill updated successfully"
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteSkill(
            @PathVariable Long id) {

        skillService.deleteSkill(id);

        return ResponseEntity.ok(
                "Skill deleted successfully"
        );
    }
}