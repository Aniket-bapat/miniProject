package project.man.Repositories;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import project.man.models.Project;


@Repository
public interface ProjectRepository extends JpaRepository<Project,Long> {

    
} 
