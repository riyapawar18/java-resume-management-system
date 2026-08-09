package resume.service;

import org.springframework.stereotype.Service;
import resume.entity.Project;
import resume.repository.ProjectRepository;

import java.util.List;

@Service
public class ProjectService {

    private final ProjectRepository repository;

    public ProjectService(ProjectRepository repository) {
        this.repository = repository;
    }

    public List<Project> getAllProjects() {
        return repository.findAll();
    }

    public Project getProjectById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Project not found"));
    }

    public void saveProject(Project project) {
        repository.save(project);
    }

    public void deleteProject(Long id) {
        repository.deleteById(id);
    }
}