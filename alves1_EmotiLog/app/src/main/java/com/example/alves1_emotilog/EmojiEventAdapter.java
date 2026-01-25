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


//This class was adapted to fit a new adapter type from an original Google Gemini response on Sat Jan 24,
//"How do I start setting up a custom adapter for my emoji events to fill in the elements of the log events"
public class EmojiEventAdapter extends ArrayAdapter<EmojiEvent> {

    public EmojiEventAdapter(Context context, ArrayList<EmojiEvent> events) {
        super(context, 0, events);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        // Get the data item for this position
        EmojiEvent event = getItem(position);

        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.log_event, parent, false);
        }

        // Lookup view for data population
        TextView emojiText = convertView.findViewById(R.id.emoji_event_display);
        TextView timestampText = convertView.findViewById(R.id.date_event_text);
        TextView emojiNameText = convertView.findViewById(R.id.name_event_text);


        // Populate the data into the template view using the data object
        if (event != null) {
            emojiText.setText(event.getEmotion());

            emojiNameText.setText(event.getEmotionName());

            // Format the timestamp into a readable string
            SimpleDateFormat formatter = new SimpleDateFormat(" EEE, MMM d, yyyy 'at' hh:mm:ss a", Locale.getDefault());
            String dateString = formatter.format(event.getTimestamp());
            timestampText.setText(dateString);
        }

        // Return the completed view to render on screen
        return convertView;
    }
}
    