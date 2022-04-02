package com.swlc.spppoker100.repository;

import com.swlc.spppoker100.entity.CorporateEmployeeEntity;
import com.swlc.spppoker100.entity.CorporateEntity;
import com.swlc.spppoker100.entity.UserEntity;
import com.swlc.spppoker100.enums.CorporateAccessStatusType;
import com.swlc.spppoker100.enums.CorporateAccessType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

/**
 * @author hp
 */
public interface CorporateEmployeeRepository extends JpaRepository<CorporateEmployeeEntity, Long> {
    Optional<CorporateEmployeeEntity> findByUserEntityAndCorporateEntityAndCorporateAccessTypeAndStatusType(
            UserEntity userEntity, CorporateEntity corporateEntity, CorporateAccessType corporateAccessType,
            CorporateAccessStatusType statusType);

    Optional<CorporateEmployeeEntity> findByUserEntityAndCorporateEntityAndStatusType(
            UserEntity userEntity, CorporateEntity corporateEntity, CorporateAccessStatusType statusType);

    Optional<CorporateEmployeeEntity> findByUserEntityAndCorporateEntity(UserEntity userEntity, CorporateEntity corporateEntity);

    List<CorporateEmployeeEntity> findByUserEntityAndStatusType(UserEntity userEntity, CorporateAccessStatusType statusType);

    List<CorporateEmployeeEntity> findAllByCorporateEntityAndStatusType(CorporateEntity corporateEntity, CorporateAccessStatusType statusType);

    @Query(value = "SELECT * FROM corporate_employee e WHERE e.corporate_id = ?1 AND e.user_id IN(SELECT u.id FROM user u WHERE u.first_name LIKE %?2% OR u.last_name LIKE %?2% OR u.email LIKE %?2% OR u.ref_no LIKE %?2%)", nativeQuery = true)
    List<CorporateEmployeeEntity> searchCorporateEmployeeEntity(long corporateId, String search);

    Optional<CorporateEmployeeEntity> findByIdAndCorporateEntity(long id, CorporateEntity corporateEntity);
}
