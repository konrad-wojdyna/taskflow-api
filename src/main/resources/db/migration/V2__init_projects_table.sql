-- V2: Initialize projects table with relationship to users
CREATE TABLE projects (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(200) NOT NULL,
    description TEXT,
    owner_id BIGINT NOT NULL,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP,

    -- Foreign Key constraint
    CONSTRAINT fk_projects_owner
        FOREIGN KEY (owner_id)
        REFERENCES users(id)
        ON DELETE CASCADE
);

-- Create index on owner_id (performance for findByOwnerId)
CREATE INDEX idx_projects_owner_id ON projects(owner_id);

-- Create index on created_at (for sorting)
CREATE INDEX idx_projects_created_at ON projects(created_at);