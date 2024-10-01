package com.crio.xlido.entities;

import java.util.*;

public class Question {

    private Long questionId;   // Unique ID for each question
    private Long eventId;      // Event to which the question belongs
    private Long userId;       // User who added the question
    private String content;    // The content of the question

    // private Set<Long> upvotedUserIds ;
    private Set<Long> upvotedUserIds = new HashSet<>(); // Set to track user IDs who have upvoted the question
    
    private List<Reply> replies = new ArrayList<>();  // Add a list of replies
    private long timestamp;     // Timestamp of when the question was created

    // Constructor for creating a new question
    public Question(Long eventId, Long userId, String content) {
        this.eventId = eventId;
        this.userId = userId;
        this.content = content;
    }

    // public Question(Long questionId, Long eventId, Long userId, String content,
    //         Set<Long> upvotedUserIds, List<Reply> replies, long timestamp) {
    //     this.questionId = questionId;
    //     this.eventId = eventId;
    //     this.userId = userId;
    //     this.content = content;
    //     this.upvotedUserIds = upvotedUserIds;
    //     this.replies = replies;
    //     this.timestamp = timestamp;
    // }

    // Constructor for loading a question with an ID
    public Question(Long questionId, Long eventId, Long userId, String content , long timeStamp) {
        this.questionId = questionId;
        this.eventId = eventId;
        this.userId = userId;
        this.content = content;
        this.timestamp = timeStamp;
    }

    //GETTERS and SETTERS


    public Long getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Long questionId) {
        this.questionId = questionId;
    }

    public Long getEventId() {
        return eventId;
    }

    public void setEventId(Long eventId) {
        this.eventId = eventId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public List<Reply> getReplies() {
        return replies;
    }

    public void setReplies(List<Reply> replies) {
        this.replies = replies;
    }

    // LAZY initialization
    // public Set<Long> getUpvotedUserIds() {
    //     if (upvotedUserIds == null) {
    //         upvotedUserIds = new HashSet<>(); // Initialize the set if it is null
    //     }
    //     return upvotedUserIds;
    // }

    public Set<Long> getUpvotedUserIds() {
        return upvotedUserIds;
    }

    public void setUpvotedUserIds(Set<Long> upvotedUserIds) {
        this.upvotedUserIds = upvotedUserIds;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }
     

}
