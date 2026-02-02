package com.example.alves1_emotilog;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * Purpose:
 * - Fetches the complete list of EmojiEvent objects from the central log in MainActivity.
 * - Displays each event in a scrollable list, with the most recent events at the top.
 * - Displays a message if no events have been logged yet.
 *
 * Design Rationale:
 * - Uses a ListView with a custom adapter (EmojiEventAdapter) to render each log entry in order
 *  to have full customization
 * - Creates a reversed copy of the event list for display, instead of modifying the
 *   original data source for proper data integrity.
 * - Uses the no events logged message so it doesnt look completely empty and for better
 *  user experience.
 *
 * Outstanding Issues: None.
 */
public class LogFragment extends Fragment {
    private ListView logListView;
    private TextView emptyLogTextView;
    private EmojiEventAdapter emojiAdapter;
    private ArrayList<EmojiEvent> emojiEventList;

    public LogFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_log, container, false);

        logListView = view.findViewById(R.id.log_list);
        emptyLogTextView = view.findViewById(R.id.log_empty_text);

        emojiEventList = MainActivity.getEmojiLog().getEmojiEventList();
         //A message only visible if event list is empty
        if (emojiEventList.isEmpty()) {
           logListView.setVisibility(View.GONE);
            emptyLogTextView.setVisibility(View.VISIBLE);
        } else {

            emptyLogTextView.setVisibility(View.GONE);
            logListView.setVisibility(View.VISIBLE);
            ArrayList<EmojiEvent> reversedEventList = new ArrayList<>(emojiEventList);
            Collections.reverse(reversedEventList);

            emojiAdapter = new EmojiEventAdapter(getActivity(), reversedEventList);
            logListView.setAdapter(emojiAdapter);
        }
        return view;
    }
}