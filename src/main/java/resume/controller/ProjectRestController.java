package resume.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import resume.entity.Project;
import resume.service.ProjectService;

import java.util.List;

@RestController
@RequestMapping("/api/projects")
public class ProjectRestController {

    private final ProjectService projectService;

    public ProjectRestController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @GetMapping
    public List<Project> getProjects() {
        return projectService.getAllProjects();
    }

    @GetMapping("/{id}")
    public Project getProject(@PathVariable Long id) {
        return projectService.getProjectById(id);
    }

    @PostMapping
    public ResponseEntity<String> createProject(
            @RequestBody Project project) {

        projectService.saveProject(project);

        return ResponseEntity.ok(
                "Project created successfully"
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateProject(
            @PathVariable Long id,
            @RequestBody Project project) {

        Project existing =
                projectService.getProjectById(id);

        existing.setTitle(project.getTitle());
        existing.setTechnologies(project.getTechnologies());
        existing.setDescription(project.getDescription());

        projectService.saveProject(existing);

        return ResponseEntity.ok(
                "Project updated successfully"
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProject(
            @PathVariable Long id) {

        projectService.deleteProject(id);

        return ResponseEntity.ok(
                "Project deleted successfully"
        );
    }
}