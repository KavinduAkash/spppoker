package com.swlc.spppoker100.repository;

import com.swlc.spppoker100.entity.ProjectEntity;
import com.swlc.spppoker100.entity.ProjectUserStoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author hp
 */
public interface UserStoryRepository extends JpaRepository<ProjectUserStoryEntity, Long> {
    List<ProjectUserStoryEntity> findByProjectEntity(ProjectEntity projectEntity);
}
