package com.taskflow.service;


import com.taskflow.dto.request.CreateProjectRequest;
import com.taskflow.dto.response.ProjectResponse;
import com.taskflow.exception.UserNotFoundException;
import com.taskflow.model.Project;
import com.taskflow.model.User;
import com.taskflow.repository.ProjectRepository;
import com.taskflow.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProjectService {

    private static final Logger log = LoggerFactory.getLogger(ProjectService.class);

    private final ProjectRepository projectRepository;
    private final UserRepository userRepository;


    public ProjectService(ProjectRepository projectRepository, UserRepository userRepository) {
        this.projectRepository = projectRepository;
        this.userRepository = userRepository;
    }

    /**
     * Create new project
     * Business rules:
     * - Owner must exist in database
     * - Project name required (validated by DTO)
     *
     * @param request - project data from client
     * @param ownerEmail - authenticated user email
     * @return ProjectResponse
     * @throws UserNotFoundException if owner not found
     */
    @Transactional
    public ProjectResponse create(CreateProjectRequest request, String ownerEmail){
        log.info("Creating project '{}' for user: {}", request.name(), ownerEmail);

        User owner = userRepository.findByEmail(ownerEmail).orElseThrow(() -> new UserNotFoundException("email", ownerEmail));

        Project project = new Project(
                request.name(),
                request.description(),
                owner
        );

        Project savedProject = projectRepository.save(project);

        log.info("Project created successfully: {} with ID: {}",
                savedProject.getName(), savedProject.getId());

        return ProjectResponse.fromProject(savedProject);
    }

    /**
     * List all projects for authenticated user
     *
     * @param ownerEmail - authenticated user email
     * @return List of ProjectResponse
     */
    public List<ProjectResponse> listUserProjects(String ownerEmail){
        log.info("Fetching projects for user: {}", ownerEmail);

        User owner = userRepository.findByEmail(ownerEmail).orElseThrow(
                () -> new UserNotFoundException("email", ownerEmail)
        );

        List<Project> projects = projectRepository.findByOwnerId(owner.getId());

        log.info("Found {} projects for user: {}", projects.size(), ownerEmail);

        return projects.stream().map(ProjectResponse::fromProject).toList();
    }
}
