package com.example.alves1_emotilog;

import java.util.Date;

public class EmojiEvent {
    private String emotion;
    private Date timestamp;
    private String emotionName;

    public EmojiEvent(String emotion) {
        this.emotion = emotion;
        this.timestamp = new Date();
        this.emotionName = MainActivity.emotionMap.get(emotion);
    }

    public EmojiEvent(String emotion, Date timestamp) {
        this.emotion = emotion;
        this.timestamp = timestamp;
        this.emotionName = MainActivity.emotionMap.get(emotion);
    }

    public String getEmotion() {
        return emotion;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public String getEmotionName() {
        return emotionName;
    }

}
