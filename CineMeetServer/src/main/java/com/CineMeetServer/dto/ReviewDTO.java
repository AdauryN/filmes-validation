package com.CineMeetServer.dto;

import lombok.Data;

import java.util.Date;

@Data
public class ReviewDTO {

    private Long id;

    private Date reviewDate;

    private String review;

    private Long rating;

    private Long eventId;
    private Long reviewerId;
    private String reviewerName;


}
