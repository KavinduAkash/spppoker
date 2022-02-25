package com.swlc.spppoker100.repository;

import com.swlc.spppoker100.entity.ProjectTaskEntity;
import com.swlc.spppoker100.entity.ProjectUserStoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author hp
 */
public interface ProjectTaskRepository extends JpaRepository<ProjectTaskEntity, Long> {
    List<ProjectTaskEntity> findAllByProjectUserStoryEntity(ProjectUserStoryEntity projectUserStoryEntity);
}
