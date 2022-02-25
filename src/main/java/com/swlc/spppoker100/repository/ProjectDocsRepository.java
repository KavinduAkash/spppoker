package com.swlc.spppoker100.repository;

import com.swlc.spppoker100.entity.ProjectDocsEntity;
import com.swlc.spppoker100.entity.ProjectEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author hp
 */
public interface ProjectDocsRepository extends JpaRepository<ProjectDocsEntity, Long> {
    List<ProjectDocsEntity> findAllByProjectEntity(ProjectEntity projectEntity);
}
