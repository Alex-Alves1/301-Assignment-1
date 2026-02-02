package com.example.alves1_emotilog;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Map;

import android.app.DatePickerDialog;
import android.widget.Button;
import android.widget.DatePicker;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 *
 * Purpose:
 * - Fetches the event log or count map from MainActivity.
 * - Processes this data to calculate the total number of times each unique emotion
 *   has been logged during the session, or does calculations on the event log to display
 *   per day counts if the day filter is used.
 * - Displays this summary in a clear scrollable listview
 *
 * Design Rationale:
 * - If using a count map from MainActivity, it directly reflects the session's totals.
 *   If processing the log directly based on a date filter, it uses a hashmap to calculate
 *   the counts.
 * - Uses a ListView with a custom adapter (EmojiSummaryAdapter) to render each emoji entry in order
 *  to have full customization
 *
 * Outstanding Issues: None.
 */

/*
Elements of this class (attributes and functions) involving the sort by date elements were taken/adapted from a
Google Gemini response on Sun Feb 2nd 2026:
"is there a way that I could make it so the summary page allows you to sort by day, based on count timestamps?"
 */
public class SummaryFragment extends Fragment {
    private ListView summaryListView;
    private EmojiSummaryAdapter emojiSummaryAdapter;
    private Button sortDateButton;
    private Button clearDateButton;

    public SummaryFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_summary, container, false);

        //Finding Buttons
        summaryListView = view.findViewById(R.id.summary_list);
        sortDateButton = view.findViewById(R.id.sort_date_button);
        clearDateButton = view.findViewById(R.id.clear_date_button);

        ArrayList<Map.Entry<String, Integer>> summaryList = new ArrayList<>(MainActivity.countMap.entrySet());
        emojiSummaryAdapter = new EmojiSummaryAdapter(getActivity(), summaryList);
        summaryListView.setAdapter(emojiSummaryAdapter);
/*
The following function was from a Google Gemini response on Sun Feb 2nd 2026:
"is there a way that I could make it so the summary page allows you to sort by day, based on count timestamps?"
 */
        sortDateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog();
            }
        });
/*
The following function was adapted from a Google Gemini response on Sun Feb 2nd 2026:
"is there a way that I could make it so the summary page allows you to sort by day, based on count timestamps?"
 */
        clearDateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Re-fetch the summary list to ensure it's up to date with the latest totals
                ArrayList<Map.Entry<String, Integer>> updatedSummaryList = new ArrayList<>(MainActivity.countMap.entrySet());
                emojiSummaryAdapter.updateData(updatedSummaryList);
                clearDateButton.setVisibility(View.GONE); // Hide button again
            }
        });

        return view;
    }
/*
The following function was adapted from a Google Gemini response on Sun Feb 2nd 2026:
"is there a way that I could make it so the summary page allows you to sort by day, based on count timestamps?"
 */
    private void showDatePickerDialog() {
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(),
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        filterSummaryByDate(year, monthOfYear, dayOfMonth);
                    }
                }, year, month, day);
        datePickerDialog.show();
    }
/*
The following function was adapted from a Google Gemini response on Sun Feb 2nd 2026:
"is there a way that I could make it so the summary page allows you to sort by day, based on count timestamps?"
 */
    private void filterSummaryByDate(int year, int month, int day) {
        // Set calendar to start of the selected day
        Calendar calStart = Calendar.getInstance();
        calStart.set(year, month, day, 0, 0, 0);
        Date startDate = calStart.getTime();

        // Set calendar to end of the selected day
        Calendar calEnd = Calendar.getInstance();
        calEnd.set(year, month, day, 23, 59, 59);
        Date endDate = calEnd.getTime();

        // Initialize map with all emojis and a count of 0.
        // This ensures all emojis are shown, even with a count of 0 for the selected day.
        HashMap<String, Integer> dailyCountMap = new HashMap<>();
        for (String emoji : MainActivity.emojis) { // MainActivity.emojis is the master list of emoji strings
            dailyCountMap.put(emoji, 0);
        }

        // Get all events and update counts for the selected day
        List<EmojiEvent> allEvents = MainActivity.getEmojiLog().getEmojiEventList();
        for (EmojiEvent event : allEvents) {
            if (!event.getTimestamp().before(startDate) && !event.getTimestamp().after(endDate)) {
                // This event is on the selected day
                String emoji = event.getEmotion();
                dailyCountMap.put(emoji, dailyCountMap.getOrDefault(emoji, 0) + 1);
            }
        }

        // Update adapter with the new filtered list
        ArrayList<Map.Entry<String, Integer>> dailySummaryList = new ArrayList<>(dailyCountMap.entrySet());
        emojiSummaryAdapter.updateData(dailySummaryList);
        clearDateButton.setVisibility(View.VISIBLE); // Show the clear button
    }


}