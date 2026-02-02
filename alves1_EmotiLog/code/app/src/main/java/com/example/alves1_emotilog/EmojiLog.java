package com.example.alves1_emotilog;

import java.util.ArrayList;
import java.util.Date;

/**
 * Manages the collection of all EmojiEvent objects for the application session.
 *
 * Purpose:
 * - This class acts as a list based storage for all the emotions logged by the user. It
 * encapsulates an ArrayList of EmojiEvent objects and provides the methods needed to add and
 * retrieve events.
 *
 * Design Rationale:
 * - This serves as an encapsulated arrayList that can be accessed by all fragments.
 * - It is designed to be instantiated as a static final object in MainActivity, so that it can act
 *   as a source of truth for all components for the sessions entire session. All fragments interact
 *   with the same list.
 *
 * Outstanding Issues: None.
 */
public class EmojiLog {
    private ArrayList<EmojiEvent> emojiLog;
//Initializes the arrayList for the emojiLog
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
