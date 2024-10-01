package com.crio.xlido.entities;

public class Reply {
    private Long replyId;
    private Long userId;
    private String content;
    
    public Reply(Long replyId, Long userId, String content) {
        this.replyId = replyId;
        this.userId = userId;
        this.content = content;
    }

    public Long getReplyId() {
        return replyId;
    }

    public void setReplyId(Long replyId) {
        this.replyId = replyId;
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

    
}
