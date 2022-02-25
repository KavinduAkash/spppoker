package com.swlc.spppoker100.repository;

import com.swlc.spppoker100.entity.CorporateEmployeeEntity;
import com.swlc.spppoker100.entity.ProjectEntity;
import com.swlc.spppoker100.entity.ProjectMemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

/**
 * @author hp
 */
public interface ProjectMemberRepository extends JpaRepository<ProjectMemberEntity, Long> {
    Optional<ProjectMemberEntity> findByProjectEntityAndCorporateEmployeeEntity(ProjectEntity projectEntity,
                                                                                CorporateEmployeeEntity corporateEmployeeEntity);
    @Query("SELECT pm.projectEntity FROM ProjectMemberEntity pm WHERE pm.corporateEmployeeEntity = :corporateEmployee")
    List<ProjectEntity> getByCorporateEmployeeEntity(@Param("corporateEmployee") CorporateEmployeeEntity corporateEmployeeEntity);

    @Query("SELECT pm FROM ProjectMemberEntity pm WHERE pm.corporateEmployeeEntity = :corporateEmployee")
    List<ProjectMemberEntity> getProjectMemberByCorporateEmployeeEntity(@Param("corporateEmployee") CorporateEmployeeEntity corporateEmployeeEntity);

    List<ProjectMemberEntity> findByProjectEntity(ProjectEntity projectEntity);

    Optional<ProjectMemberEntity> findByCorporateEmployeeEntityAndProjectEntity(CorporateEmployeeEntity corporateEmployeeEntity, ProjectEntity projectEntity);
}
