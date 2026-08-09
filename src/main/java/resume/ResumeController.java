package resume;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import resume.service.SkillService;
import resume.service.ProjectService;

@Controller
public class ResumeController {

    private final SkillService skillService;
    private final ProjectService projectService;

    public ResumeController(
            SkillService skillService,
            ProjectService projectService) {

        this.skillService = skillService;
        this.projectService = projectService;
    }

    @GetMapping("/")
    public String home(Model model) {

        model.addAttribute(
                "skills",
                skillService.getAllSkills()
        );

        model.addAttribute(
                "projects",
                projectService.getAllProjects()
        );

        return "resume";
    }
}