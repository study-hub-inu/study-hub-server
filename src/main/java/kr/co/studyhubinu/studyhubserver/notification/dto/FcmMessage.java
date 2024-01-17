package kr.co.studyhubinu.studyhubserver.notification.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class FcmMessage {

    @JsonProperty("validate_only")
    private final boolean validateOnly;
    private final Message message;

    @RequiredArgsConstructor
    @Getter
    public static class Message {

        private final Data data;
        private final String token;
    }

    @RequiredArgsConstructor
    @Getter
    public static class Data {

        private final String senderName;
        private final String senderId;
        private final String receiverId;
        private final String message;
        private final String openProfileUrl;
    }
}