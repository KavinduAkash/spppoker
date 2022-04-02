package com.swlc.spppoker100.repository;

import com.swlc.spppoker100.entity.AdminEntity;
import com.swlc.spppoker100.enums.StatusType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * @author hp
 */

@Repository
public interface AdminRepository extends JpaRepository<AdminEntity, Long> {
    Optional<AdminEntity> findByUsername(String username);
    Optional<AdminEntity> findByEmail(String email);
    Optional<AdminEntity> findByContactNumber(String contactNumber);
//    @Query(value = NativeQueryConstant.FILTER_ADMIN_BY_NOT_EQUAL_STATUS, nativeQuery = true)
//    List<AdminEntity> filerAdminByNotEqualStatusType(StatusType statusType);
    Optional<AdminEntity> findByUsernameAndStatusType(String username, StatusType statusType);
}
