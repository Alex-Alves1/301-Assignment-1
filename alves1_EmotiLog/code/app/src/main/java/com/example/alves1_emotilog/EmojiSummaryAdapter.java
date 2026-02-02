package com.example.alves1_emotilog;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Map;

/**
 * Purpose:
 * - Custom ArrayAdapter for displaying the count information for each emotion
 *  in the SummaryFragment's ListView.
 * - Populates the views within each row with the data for the corresponding emotion.
 *
 * Design Rationale:
 * - Needed to create a custom adapter so the count information for each emotion could be displayed
 *  in an organized and functional way.
 *
 * Outstanding Issues: None.
 */
//This class was taken/adapted from a Google Gemini response on Sat Jan 24,
//"How do I start setting up a custom adapter for my emoji events to fill in the elements of the log events"
public class EmojiSummaryAdapter extends ArrayAdapter<Map.Entry<String, Integer>> {

    public EmojiSummaryAdapter(Context context, ArrayList<Map.Entry<String,Integer>> countMap) {
        super(context, 0, countMap);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        // Get the data item for this position
        Map.Entry<String, Integer> entry = getItem(position);

        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.summary_event, parent, false);
        }

        // Lookup view for data population
        TextView emojiText = convertView.findViewById(R.id.emoji_event_display);
        TextView emojiNameText = convertView.findViewById(R.id.name_event_text);
        TextView emojiCountText = convertView.findViewById(R.id.count_event_text);

        // Populate the data into the template view using the data object
        if (entry != null) {
            //pull from count map entry
            String emoji = entry.getKey();
            Integer count = entry.getValue();

            emojiText.setText(emoji);
            emojiNameText.setText(MainActivity.emotionMap.get(emoji));
            emojiCountText.setText(String.valueOf(count));
        }

        // Return the completed view to render on screen
        return convertView;
    }
/*
The following function was from a Google Gemini response on Sun Feb 2nd 2026:
"is there a way that I could make it so the summary page allows you to sort by day, based on count timestamps?"
 */
    public void updateData(ArrayList<Map.Entry<String, Integer>> newList) {
        clear();
        addAll(newList);
        notifyDataSetChanged();
    }
}
    