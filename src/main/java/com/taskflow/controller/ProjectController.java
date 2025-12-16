package com.taskflow.controller;


import com.taskflow.dto.request.CreateProjectRequest;
import com.taskflow.dto.response.ProjectResponse;
import com.taskflow.service.ProjectService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/projects")
public class ProjectController {

    private final ProjectService projectService;

    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }


    /**
     * Create new project
     *
     * POST /api/projects
     * Authorization: Bearer <token>
     *
     * @param request - project data (validated)
     * @param userDetails - authenticated user (from JWT)
     * @return 201 Created + ProjectResponse
     */
    @PostMapping
    public ResponseEntity<ProjectResponse> createProject(
            @Valid @RequestBody CreateProjectRequest request,
            @AuthenticationPrincipal UserDetails userDetails
            ){

        String ownerEmail = userDetails.getUsername();

        ProjectResponse response = projectService.create(request, ownerEmail);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }


    /**
     * List all projects for authenticated user
     *
     * GET /api/projects
     * Authorization: Bearer <token>
     *
     * @param userDetails - authenticated user (from JWT)
     * @return 200 OK + List<ProjectResponse>
     */
    @GetMapping
    public ResponseEntity<List<ProjectResponse>> listUserProjects(
            @AuthenticationPrincipal UserDetails userDetails
    ){
        String ownerEmail = userDetails.getUsername();

        List<ProjectResponse> projects = projectService.listUserProjects(ownerEmail);

        return ResponseEntity.ok(projects);
    }
}
