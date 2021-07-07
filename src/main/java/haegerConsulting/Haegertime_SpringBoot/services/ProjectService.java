package haegerConsulting.Haegertime_SpringBoot.services;

import haegerConsulting.Haegertime_SpringBoot.exceptions.ProjectExceptions.DuplicateProjectException;
import haegerConsulting.Haegertime_SpringBoot.exceptions.ProjectExceptions.ProjectNotFoundException;
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



    public Project create(Project project) throws DuplicateProjectException{

        if (project.getId() != null && projectRepository.existsById(project.getId())){

            throw new DuplicateProjectException();
        }

        return projectRepository.save(project);
    }

    public void updateProject(Project newProject) throws ProjectNotFoundException{

        Optional<Project> optionalProject = projectRepository.findById(newProject.getId());

        if (optionalProject.isPresent()){

            projectRepository.save(newProject);
        }else {

            throw new ProjectNotFoundException();
        }
    }

    public Iterable<Project> getProjectByUserId(Long user_id) throws ProjectNotFoundException{

        Iterable<Project> projects = projectRepository.findAllByUserId(user_id);

        if (projects == null){

            throw new ProjectNotFoundException();
        }else {

            return projects;
        }
    }

    public List<Project> getAllProjects(){

        return projectRepository.findAll();
    }

    public void deleteById(Long id) throws ProjectNotFoundException {

        if (projectRepository.existsById(id)){

            projectRepository.deleteById(id);
        }else {

            throw new ProjectNotFoundException();
        }
    }

    public void deleteAll(){

        projectRepository.deleteAll();
    }
}
