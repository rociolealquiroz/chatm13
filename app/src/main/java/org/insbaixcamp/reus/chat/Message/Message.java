package org.insbaixcamp.reus.chat.Message;

public class Message {
    private String userId;
    private String message;

    public Message() {
    }

    /**
     *
     * @param userId
     * @param message
     */
    public Message(String userId, String message) {
        this.userId = userId;
        this.message = message;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "Message{" +
                "userId='" + userId + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}
