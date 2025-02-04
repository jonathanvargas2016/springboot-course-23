package com.hexagonal.tasks.domain.models;

public class AdditionalTaskInfo {
    private final Long userId;
    private final String username;
    private final String userEmail;


    public AdditionalTaskInfo(Long userId, String username, String userEmail) {
        this.userId = userId;
        this.username = username;
        this.userEmail = userEmail;
    }

    public Long getUserId() {
        return userId;
    }

    public String getUsername() {
        return username;
    }

    public String getUserEmail() {
        return userEmail;
    }
}
