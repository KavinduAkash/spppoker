package com.swlc.spppoker100.service.impl;

import com.swlc.spppoker100.entity.*;
import com.swlc.spppoker100.enums.VoteStatus;
import com.swlc.spppoker100.modal.AllVoteResponse;
import com.swlc.spppoker100.modal.Vote;
import com.swlc.spppoker100.modal.VoteResponse;
import com.swlc.spppoker100.repository.*;
import com.swlc.spppoker100.service.VoteService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * @author hp
 */
@Log4j2
@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class VoteServiceImpl implements VoteService {
    private final TempUserStoryPointRepository tempUserStoryPointRepository;
    private final UserRepository userRepository;
    private final ProjectSprintUserStoryRepository projectSprintUserStoryRepository;
    private final UserStoryRepository userStoryRepository;
    private final SppokerRepository sppokerRepository;

    @Autowired
    public VoteServiceImpl(TempUserStoryPointRepository tempUserStoryPointRepository, UserRepository userRepository, ProjectSprintUserStoryRepository projectSprintUserStoryRepository, UserStoryRepository userStoryRepository, SppokerRepository sppokerRepository) {
        this.tempUserStoryPointRepository = tempUserStoryPointRepository;
        this.userRepository = userRepository;
        this.projectSprintUserStoryRepository = projectSprintUserStoryRepository;
        this.userStoryRepository = userStoryRepository;
        this.sppokerRepository = sppokerRepository;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public AllVoteResponse vote(Vote vote) {
        log.info("Execute Method: vote: @param {} " + vote.toString());
        try {

            UserEntity userById = userRepository.getById(vote.getVoter_id());

            Optional<SpppokerRoomEntity> roomByRoomRef = sppokerRepository.findByRoomRef(vote.getRoom_ref());
            SpppokerRoomEntity spppokerRoomEntity = roomByRoomRef.get();

            Optional<ProjectUserStoryEntity> userStoryById = userStoryRepository.findById(vote.getCandidate_id());
            ProjectUserStoryEntity userStoryEntity = userStoryById.get();

            Optional<TempUserStoryPointEntity> temp =
                    tempUserStoryPointRepository.findByUserEntityAndAndRoomEntityAndAndUserStoryEntity(
                            userById,
                            spppokerRoomEntity,
                            userStoryEntity
                    );

            log.info("Execute Method: vote: @param {} 1");

            if (temp.isPresent()) {
                log.info("Execute Method: vote: @param {} 1.1");
                TempUserStoryPointEntity tempVote = temp.get();
                tempVote.setVotedDate(new Date());
                tempVote.setVote(vote.getVote());
                tempUserStoryPointRepository.save(tempVote);
                log.info("Execute Method: vote: @param {} 2");
            } else {
                log.info("Execute Method: vote: @param {} 1.2");
                tempUserStoryPointRepository.save(
                        new TempUserStoryPointEntity(
                            spppokerRoomEntity,
                                userById,
                                userStoryEntity,
                                new Date(),
                                vote.getVote(),
                                VoteStatus.ACTIVE
                        )
                );
                log.info("Execute Method: vote: @param {} 4");
            }
            log.info("Execute Method: vote: @param {} 5");
            List<TempUserStoryPointEntity> allByRoomEntityAndUserStoryEntity = tempUserStoryPointRepository.findAllByRoomEntityAndUserStoryEntity(spppokerRoomEntity, userStoryEntity);
            log.info("Execute Method: vote: @param {} 6");
            List<VoteResponse> allVotes = new ArrayList<>();

            for (TempUserStoryPointEntity tempVote : allByRoomEntityAndUserStoryEntity) {
                allVotes.add(
                        new VoteResponse(
                                tempVote.getId(),
                                tempVote.getUserEntity().getId(),
                                tempVote.getUserEntity().getFirstName(),
                                tempVote.getUserEntity().getLastName(),
                                tempVote.getUserEntity().getRefNo(),
                                tempVote.getVotedDate(),
                                tempVote.getVote()
                        )
                );
            }

            return new AllVoteResponse(spppokerRoomEntity.getRoomRef(), userStoryEntity.getId(), allVotes);

        } catch (Exception e) {
            log.error("Execute Method: vote: Error ", e, e.getMessage());
            throw e;
        }
    }
}
