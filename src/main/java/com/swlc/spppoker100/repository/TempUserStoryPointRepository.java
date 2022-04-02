package com.swlc.spppoker100.repository;

import com.swlc.spppoker100.entity.ProjectUserStoryEntity;
import com.swlc.spppoker100.entity.SpppokerRoomEntity;
import com.swlc.spppoker100.entity.TempUserStoryPointEntity;
import com.swlc.spppoker100.entity.UserEntity;
import com.swlc.spppoker100.enums.VoteStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

/**
 * @author hp
 */
public interface TempUserStoryPointRepository extends JpaRepository<TempUserStoryPointEntity,Long> {
    Optional<TempUserStoryPointEntity> findByUserEntityAndAndRoomEntityAndAndUserStoryEntity(
            UserEntity userEntity,
            SpppokerRoomEntity spppokerRoomEntity,
            ProjectUserStoryEntity projectUserStoryEntity
    );
    List<TempUserStoryPointEntity> findAllByRoomEntityAndUserStoryEntity(
            SpppokerRoomEntity spppokerRoomEntity,
            ProjectUserStoryEntity projectUserStoryEntity
    );

    List<TempUserStoryPointEntity> findAllByRoomEntityAndStatus(SpppokerRoomEntity spppokerRoomEntity, VoteStatus status);
}
