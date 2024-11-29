package project.man.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import project.man.Repositories.ProjectRepository;
import project.man.models.Project;

@Service
public class ProjectService {
    @Autowired
    private ProjectRepository projectRepository;

    public Optional<Project> getById(long Id) {
        return projectRepository.findById(Id);
    }
    public List<Project> getAll(long Id) {
        return projectRepository.findAll();
    }

    public void delete(Project project) {
        projectRepository.delete(project);
    }

    
}
