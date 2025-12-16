package com.taskflow.dto.request;

import jakarta.validation.constraints.Size;

public record UpdateProjectRequest(

        @Size(min = 3, max = 200, message = "Project name must be between 3 and 200 characters")
        String name,

        @Size(max = 2000, message = "Description cannot exceed 2000 characters")
        String description
) {

    public UpdateProjectRequest{
        if(name != null){
            name = name.trim();
        }

        if(description != null){
            description = description.trim();
        }
    }

}
