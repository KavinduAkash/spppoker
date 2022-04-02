package com.swlc.spppoker100.repository;

import com.swlc.spppoker100.entity.ProjectEntity;
import com.swlc.spppoker100.entity.UserStoryLabelEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author hp
 */
public interface UserStoryLabelRepository extends JpaRepository<UserStoryLabelEntity, Long> {
    List<UserStoryLabelEntity> findByProjectEntity(ProjectEntity projectEntity);
}
