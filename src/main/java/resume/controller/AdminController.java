package resume.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import resume.entity.Skill;
import resume.entity.Project;
import resume.service.SkillService;
import resume.service.ProjectService;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final SkillService skillService;
    private final ProjectService projectService;

    public AdminController(
            SkillService skillService,
            ProjectService projectService) {

        this.skillService = skillService;
        this.projectService = projectService;
    }

    // =========================
    // DASHBOARD
    // =========================

    @GetMapping
    public String dashboard(Model model) {

        model.addAttribute(
                "skillCount",
                skillService.getAllSkills().size()
        );

        model.addAttribute(
                "projectCount",
                projectService.getAllProjects().size()
        );

        return "admin-dashboard";
    }


    // =========================
    // SKILLS
    // =========================

    @GetMapping("/skills")
    public String skills(Model model) {

        model.addAttribute(
                "skills",
                skillService.getAllSkills()
        );

        return "admin-skills";
    }

    @GetMapping("/skills/add")
    public String addSkill(Model model) {

        model.addAttribute("skill", new Skill());

        return "add-skill";
    }

    @PostMapping("/skills/save")
    public String saveSkill(@ModelAttribute Skill skill) {

        skillService.saveSkill(skill);

        return "redirect:/admin/skills";
    }

    @GetMapping("/skills/edit/{id}")
    public String editSkill(
            @PathVariable Long id,
            Model model) {

        model.addAttribute(
                "skill",
                skillService.getSkillById(id)
        );

        return "edit-skill";
    }

    @PostMapping("/skills/update/{id}")
    public String updateSkill(
            @PathVariable Long id,
            @ModelAttribute Skill skill) {

        Skill existing =
                skillService.getSkillById(id);

        existing.setName(skill.getName());
        existing.setCategory(skill.getCategory());

        skillService.saveSkill(existing);

        return "redirect:/admin/skills";
    }

    @GetMapping("/skills/delete/{id}")
    public String deleteSkill(@PathVariable Long id) {

        skillService.deleteSkill(id);

        return "redirect:/admin/skills";
    }


    // =========================
    // PROJECTS
    // =========================

    @GetMapping("/projects")
    public String projects(Model model) {

        model.addAttribute(
                "projects",
                projectService.getAllProjects()
        );

        return "admin-projects";
    }

    @GetMapping("/projects/add")
    public String addProject(Model model) {

        model.addAttribute(
                "project",
                new Project()
        );

        return "add-project";
    }

    @PostMapping("/projects/save")
    public String saveProject(
            @ModelAttribute Project project) {

        projectService.saveProject(project);

        return "redirect:/admin/projects";
    }

    @GetMapping("/projects/edit/{id}")
    public String editProject(
            @PathVariable Long id,
            Model model) {

        model.addAttribute(
                "project",
                projectService.getProjectById(id)
        );

        return "edit-project";
    }

    @PostMapping("/projects/update/{id}")
    public String updateProject(
            @PathVariable Long id,
            @ModelAttribute Project project) {

        Project existing =
                projectService.getProjectById(id);

        existing.setTitle(project.getTitle());
        existing.setTechnologies(project.getTechnologies());
        existing.setDescription(project.getDescription());

        projectService.saveProject(existing);

        return "redirect:/admin/projects";
    }

    @GetMapping("/projects/delete/{id}")
    public String deleteProject(
            @PathVariable Long id) {

        projectService.deleteProject(id);

        return "redirect:/admin/projects";
    }
}