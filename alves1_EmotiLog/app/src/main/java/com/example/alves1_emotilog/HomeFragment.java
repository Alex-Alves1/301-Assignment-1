package com.example.alves1_emotilog;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;


public class HomeFragment extends Fragment {
    private GridView emojiGrid;
    private ArrayAdapter<String> emojiAdapter;
    private ArrayList<String> emojiList;
    private Snackbar activeSnackbar = null;

    public HomeFragment() {
        // Required empty public constructor
    }

    // This method was taken/adapted from a Google Gemini response on Sat Jan 24,
    // "How can I make it so if I switch to a different fragment, it will dismiss the snackbar"
    @Override
    public void onPause() {
        super.onPause();
        if (activeSnackbar != null && activeSnackbar.isShown()) {
            activeSnackbar.dismiss();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        emojiGrid = view.findViewById(R.id.emoji_grid);

        emojiList = new ArrayList<>(Arrays.asList(MainActivity.emojis));

        emojiAdapter = new ArrayAdapter<>(getActivity(), R.layout.content, emojiList);
        emojiGrid.setAdapter(emojiAdapter);


        emojiGrid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                // Get emoji and emoji name
                String selectedEmoji = emojiList.get(position);
                String emotionName = MainActivity.emotionMap.get(selectedEmoji);

                // update count of that emoji
                Integer count = MainActivity.countMap.get(selectedEmoji);
                if (count != null) MainActivity.countMap.put(selectedEmoji, count + 1);
                else MainActivity.countMap.put(selectedEmoji, 1);

                //Logging the emotion to the log
                MainActivity.getEmojiLog().addEvent(selectedEmoji);

                //Setting up a snackbar to display above the emoji grid, and alerts the user the emotion that was just logged
                Snackbar snackbar = Snackbar.make(requireView(), "Logged: " + emotionName + " " + selectedEmoji, Snackbar.LENGTH_SHORT);
                snackbar.setAnchorView(emojiGrid);
                View snackbarView = snackbar.getView();
                TextView snackbarTextView = snackbarView.findViewById(com.google.android.material.R.id.snackbar_text);
                snackbarTextView.setTextSize(20);
                snackbarTextView.setGravity(Gravity.CENTER);
                activeSnackbar = snackbar;
                snackbar.show();

            }
        });
        return view;
    }

}