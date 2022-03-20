package com.swlc.spppoker100.service.impl;

import com.swlc.spppoker100.entity.*;
import com.swlc.spppoker100.enums.VoteStatus;
import com.swlc.spppoker100.modal.AllVoteResponse;
import com.swlc.spppoker100.modal.SavePoints;
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
    private final ProjectMemberRepository projectMemberRepository;

    @Autowired
    public VoteServiceImpl(TempUserStoryPointRepository tempUserStoryPointRepository, UserRepository userRepository, ProjectSprintUserStoryRepository projectSprintUserStoryRepository, UserStoryRepository userStoryRepository, SppokerRepository sppokerRepository, ProjectMemberRepository projectMemberRepository) {
        this.tempUserStoryPointRepository = tempUserStoryPointRepository;
        this.userRepository = userRepository;
        this.projectSprintUserStoryRepository = projectSprintUserStoryRepository;
        this.userStoryRepository = userStoryRepository;
        this.sppokerRepository = sppokerRepository;
        this.projectMemberRepository = projectMemberRepository;
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
            if (temp.isPresent()) {
                TempUserStoryPointEntity tempVote = temp.get();
                tempVote.setVotedDate(new Date());
                tempVote.setVote(vote.getVote());
                tempUserStoryPointRepository.save(tempVote);
            } else {
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
            }
            List<TempUserStoryPointEntity> allByRoomEntityAndUserStoryEntity = tempUserStoryPointRepository.findAllByRoomEntityAndUserStoryEntity(spppokerRoomEntity, userStoryEntity);

            log.info("Execute Method: vote: return data {} " + allByRoomEntityAndUserStoryEntity.size());

            List<VoteResponse> allVotes = new ArrayList<>();
            List<ProjectMemberEntity> byProjectEntity = projectMemberRepository.findByProjectEntity(userStoryEntity.getProjectEntity());
            for (ProjectMemberEntity pm : byProjectEntity) {
                boolean isExist = false;
                for (TempUserStoryPointEntity tempVote : allByRoomEntityAndUserStoryEntity) {
                    if(tempVote.getUserEntity().getId()==pm.getCorporateEmployeeEntity().getUserEntity().getId()) {
                        isExist = true;
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
                }
                if(!isExist) {
                    allVotes.add(
                            new VoteResponse(
                                    0,
                                    pm.getCorporateEmployeeEntity().getUserEntity().getId(),
                                    pm.getCorporateEmployeeEntity().getUserEntity().getFirstName(),
                                    pm.getCorporateEmployeeEntity().getUserEntity().getLastName(),
                                    pm.getCorporateEmployeeEntity().getUserEntity().getRefNo(),
                                    new Date(),
                                    "0"
                            )
                    );
                }
            }
//            log.info("Execute Method: vote: return data {} " + allVotes.size());

            return new AllVoteResponse(spppokerRoomEntity.getRoomRef(), userStoryEntity.getId(), allVotes);

        } catch (Exception e) {
            log.error("Execute Method: vote: Error ", e, e.getMessage());
            throw e;
        }
    }

    @Override
    public AllVoteResponse justVote(String room_ref) {
        log.info("Execute Method: justVote: @param {} " + room_ref);
        try {
            Optional<SpppokerRoomEntity> roomByRoomRef = sppokerRepository.findByRoomRef(room_ref);
            SpppokerRoomEntity spppokerRoomEntity = roomByRoomRef.get();
            List<TempUserStoryPointEntity> allByRoomEntityAndStatus = tempUserStoryPointRepository.findAllByRoomEntityAndStatus(spppokerRoomEntity, VoteStatus.ACTIVE);
            List<VoteResponse> allVotes = new ArrayList<>();
            List<ProjectMemberEntity> byProjectEntity = projectMemberRepository.findByProjectEntity(spppokerRoomEntity.getProjectEntity());
            long uid = 0;
            for (ProjectMemberEntity pm : byProjectEntity) {
                boolean isExist = false;
                for (TempUserStoryPointEntity tempVote : allByRoomEntityAndStatus) {
                    if(tempVote.getUserEntity().getId()==pm.getCorporateEmployeeEntity().getUserEntity().getId()) {
                        isExist = true;
                        uid = tempVote.getUserStoryEntity().getId();
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
                }
                if(!isExist) {
                    allVotes.add(
                            new VoteResponse(
                                    0,
                                    pm.getCorporateEmployeeEntity().getUserEntity().getId(),
                                    pm.getCorporateEmployeeEntity().getUserEntity().getFirstName(),
                                    pm.getCorporateEmployeeEntity().getUserEntity().getLastName(),
                                    pm.getCorporateEmployeeEntity().getUserEntity().getRefNo(),
                                    new Date(),
                                    "0"
                            )
                    );
                }
            }

            return new AllVoteResponse(spppokerRoomEntity.getRoomRef(), uid, allVotes);
        } catch (Exception e) {
            log.error("Execute Method: justVote: Error ", e, e.getMessage());
            throw e;
        }
    }

    @Override
    public boolean savePoints(SavePoints vote) {
        log.info("Execute Method: savePoints: @param {} " + vote);
        try {
            Optional<ProjectUserStoryEntity> byId = userStoryRepository.findById(vote.getCandidate_id());
            ProjectUserStoryEntity projectUserStoryEntity = byId.get();
            projectUserStoryEntity.setPoints(vote.getVote());
            ProjectUserStoryEntity save = userStoryRepository.save(projectUserStoryEntity);
            log.info("Execute Method: savePoints: XXXXX " + save.toString());
            return true;
        } catch (Exception e) {
            log.error("Execute Method: savePoints: Error ", e, e.getMessage());
            throw e;
        }
    }
}
