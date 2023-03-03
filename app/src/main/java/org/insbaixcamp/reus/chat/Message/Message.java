package org.insbaixcamp.reus.chat.Message;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Message {
    private String userId;
    private String message;
    private long time;

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
        this.time = System.currentTimeMillis();
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

    public long getTime() {
        return time;
    }

    public String getReadableTime() {
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm", Locale.getDefault());
        return sdf.format(new Date(time));
    }


    @Override
    public String toString() {
        return "Message{" +
                "userId='" + userId + '\'' +
                ", message='" + message + '\'' +
                '}';
    }


}
