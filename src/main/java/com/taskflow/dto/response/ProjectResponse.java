package com.taskflow.dto.response;

import com.taskflow.model.Project;

import java.time.LocalDateTime;

public record ProjectResponse(
        Long id,
        String name,
        String description,
        Long ownerId,
        String ownerEmail,
        String ownerName,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {

    /**
     * Convert Project entity to ProjectResponse DTO
     *
     * @param project - entity from database
     * @return ProjectResponse DTO
     */
    public static ProjectResponse fromProject(Project project){
        return new ProjectResponse(
                project.getId(),
                project.getName(),
                project.getDescription(),
                project.getOwner().getId(),
                project.getOwner().getEmail(),
                project.getOwner().getName(),
                project.getCreatedAt(),
                project.getUpdatedAt()
        );
    }

    /**
     * Convert Project entity to ProjectResponse DTO (without owner details)
     *
     * Use when owner not loaded (LAZY)
     *
     * @param project - entity from database
     * @param ownerId - owner ID only
     * @return ProjectResponse DTO
     */
    public static ProjectResponse fromProjectWithoutOwner(Project project, Long ownerId) {
        return new ProjectResponse(
                project.getId(),
                project.getName(),
                project.getDescription(),
                ownerId,
                null,  // ownerEmail not loaded
                null,  // ownerName not loaded
                project.getCreatedAt(),
                project.getUpdatedAt()
        );
    }
}
