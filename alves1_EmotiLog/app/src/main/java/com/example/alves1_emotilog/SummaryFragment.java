package com.example.alves1_emotilog;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;

public class SummaryFragment extends Fragment {
    private ListView summaryListView;
    private EmojiSummaryAdapter emojiSummaryAdapter;

    public SummaryFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_summary, container, false);

        summaryListView = view.findViewById(R.id.summary_list);

        ArrayList<Map.Entry<String, Integer>> summaryList = new ArrayList<>(MainActivity.countMap.entrySet());

        emojiSummaryAdapter = new EmojiSummaryAdapter(getActivity(), summaryList);
        summaryListView.setAdapter(emojiSummaryAdapter);

        return view;
    }


}