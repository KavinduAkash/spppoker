package com.swlc.spppoker100.repository;

import com.swlc.spppoker100.entity.ProjectEntity;
import com.swlc.spppoker100.entity.SpppokerRoomEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

/**
 * @author hp
 */
public interface SppokerRepository extends JpaRepository<SpppokerRoomEntity, Long> {
    List<SpppokerRoomEntity> findByProjectEntity(ProjectEntity projectEntity);
    Optional<SpppokerRoomEntity> findByRoomRef(String ref);
}
