package com.swlc.spppoker100.repository;

import com.swlc.spppoker100.entity.CorporateEntity;
import com.swlc.spppoker100.entity.ProjectEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

/**
 * @author hp
 */
public interface ProjectRepository extends JpaRepository<ProjectEntity, Long> {
    List<ProjectEntity> findByCorporateEntity(CorporateEntity corporateEntity);
    Optional<ProjectEntity> findByRef(String ref);
}
