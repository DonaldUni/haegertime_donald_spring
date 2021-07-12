package haegerConsulting.Haegertime_SpringBoot.services;

import haegerConsulting.Haegertime_SpringBoot.exceptions.DuplicateException;
import haegerConsulting.Haegertime_SpringBoot.exceptions.ElementNotFoundException;
import haegerConsulting.Haegertime_SpringBoot.model.Project;
import haegerConsulting.Haegertime_SpringBoot.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ProjectService {

    @Autowired
    ProjectRepository projectRepository;



    public Project create(Project project) throws DuplicateException{

        if (project.getId() != null && projectRepository.existsById(project.getId())){

            throw new DuplicateException("This element already exist.");
        }

        return projectRepository.save(project);
    }

    public void updateProject(Project newProject) throws ElementNotFoundException{

        Optional<Project> optionalProject = projectRepository.findById(newProject.getId());

        if (optionalProject.isPresent()){

            projectRepository.save(newProject);
        }else {

            throw new ElementNotFoundException("This element has been not found.");
        }
    }

    public Project getProject(Long id) throws ElementNotFoundException {

        Optional<Project> project = projectRepository.findById(id);

        if (project.isEmpty()){

            throw new ElementNotFoundException("This element has been not found.");
        }

        return project.get();
    }


    public List<Project> getAllProjects(){

        return projectRepository.findAll();
    }

    public void deleteById(Long id) throws ElementNotFoundException {

        if (projectRepository.existsById(id)){

            projectRepository.deleteById(id);
        }else {

            throw new ElementNotFoundException("This element has been not found.");
        }
    }

    public void deleteAll(){

        projectRepository.deleteAll();
    }
}
