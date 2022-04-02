package com.swlc.spppoker100.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.swlc.spppoker100.enums.VoteStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

/**
 * @author hp
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "temp_user_story_point")
public class TempUserStoryPointEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="room_id")
    private SpppokerRoomEntity roomEntity;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="user_id")
    private UserEntity userEntity;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="user_story__id")
    private ProjectUserStoryEntity userStoryEntity;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date votedDate;
    private String vote;
    @Enumerated(EnumType.STRING)
    private VoteStatus status;

    public TempUserStoryPointEntity(SpppokerRoomEntity roomEntity, UserEntity userEntity, ProjectUserStoryEntity userStoryEntity, Date votedDate, String vote, VoteStatus status) {
        this.roomEntity = roomEntity;
        this.userEntity = userEntity;
        this.userStoryEntity = userStoryEntity;
        this.votedDate = votedDate;
        this.vote = vote;
        this.status = status;
    }

    @Override
    public String toString() {
        return "TempUserStoryPointEntity{" +
                "id=" + id +
                ", roomEntity=" + roomEntity +
                ", userEntity=" + userEntity +
                ", userStoryEntity=" + userStoryEntity +
                ", votedDate=" + votedDate +
                ", vote='" + vote + '\'' +
                ", status=" + status +
                '}';
    }
}
