package com.example.alves1_emotilog;

import java.util.ArrayList;
import java.util.Date;

public class EmojiLog {
    private ArrayList<EmojiEvent> emojiLog;
// Initializes the arrayList for the emojiLog
    public EmojiLog () {
        emojiLog = new ArrayList<>();
    }
    public void addEvent(String emotion) {
        EmojiEvent event = new EmojiEvent(emotion);
        emojiLog.add(event);
    }
    public void addEvent(String emotion, Date timestamp) {
        EmojiEvent event = new EmojiEvent(emotion, timestamp);
        emojiLog.add(event);
    }

    public ArrayList<EmojiEvent> getEmojiEventList() {
        return emojiLog;
    }
}
