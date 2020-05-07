package com.npee.npeeblog.model.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

@Builder
@Entity
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "replies")
public class Reply {

    private Long replyNo;
    private String reply;
    private Date registerDate;
}
