package com.example.alves1_emotilog;

import java.util.Date;

/**
 * Purpose:
 * This class acts as a data model to hold all the information related to one specific
 * log entry, including the emoji, its emotion's name, and the timestamp it was
 * logged.
 *
 * Design Rationale:
 * - Encapsulates event data into an object, making it easy to use in different functionalities
 * throughout the app.
 * - Uses the emotionMap from MainActivity to fetch emotion names, always accurate.
 *
 * Outstanding Issues: None.
 */
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
