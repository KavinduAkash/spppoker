package com.swlc.spppoker100.repository;

import com.swlc.spppoker100.entity.ProjectSprintUserStoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @author hp
 */
public interface ProjectSprintUserStoryRepository extends JpaRepository<ProjectSprintUserStoryEntity, Long> {
    @Query(value = "SELECT * FROM project_sprint_user_story su WHERE su.project_user_story_id=?1 AND su.status<>'DELETED' ORDER BY su.added_date DESC LIMIT 1", nativeQuery = true)
    List<ProjectSprintUserStoryEntity> getByLatestSprintUserStoryRecord(long id);

    @Query(value = "SELECT * FROM project_sprint_user_story su WHERE su.project_sprint_id=?1 AND su.status='ACTIVE' ORDER BY su.added_date", nativeQuery = true)
    List<ProjectSprintUserStoryEntity> getByUserStoriesBySprint(long id);
}
