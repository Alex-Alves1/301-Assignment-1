package com.example.alves1_emotilog;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.HashMap;
import java.util.Map;


public class MainActivity extends AppCompatActivity {
    private Button summaryButton;
    private Button logButton;
    private Button homeButton;

    //Initialize the emoji log to keep track of all events
    private static EmojiLog emojiLog = new EmojiLog();
    public static final String[] emojis = {"😄", "😢", "🥹", "😡", "🥳", "😵‍💫", "🫢", "🤒", "😰"};
    //Initialize maps for names of each emotion
    public static final Map<String, String> emotionMap = new HashMap<>();

    public static Map<String, Integer> countMap = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        //Initializing the mappings for emoji to text for all classes to access
        emotionMap.put("😄", "Happy");
        emotionMap.put("😢", "Sad");
        emotionMap.put("🥹", "Grateful");
        emotionMap.put("😡", "Angry");
        emotionMap.put("🥳", "Excited");
        emotionMap.put("😵‍💫", "Confused");
        emotionMap.put("🫢", "Surprised");
        emotionMap.put("🤒", "Sick");
        emotionMap.put("😰", "Anxious");

        //Initializing the mappings for emoji to count for all classes to access
        countMap.put("😄", 0);
        countMap.put("😢", 0);
        countMap.put("🥹", 0);
        countMap.put("😡", 0);
        countMap.put("🥳", 0);
        countMap.put("😵‍💫", 0);
        countMap.put("🫢", 0);
        countMap.put("🤒", 0);
        countMap.put("😰", 0);

        logButton = findViewById(R.id.log_button);
        summaryButton = findViewById(R.id.summary_button);
        homeButton = findViewById(R.id.home_button);


        homeButton.setOnClickListener(v -> {
            // Later, you will start the homeActivity here
            updateButtons(homeButton);

            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, new HomeFragment())
                    .commit();
        });

        summaryButton.setOnClickListener(v -> {
            // Later, you will start the SummaryActivity here
            //Toast.makeText(MainActivity.this, "Summary screen coming soon!", Toast.LENGTH_SHORT).show();
            updateButtons(summaryButton);

            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, new SummaryFragment())
                    .commit();

        });

        logButton.setOnClickListener(v -> {
            // Later, you will start the LogActivity here
            //Toast.makeText(MainActivity.this, "Log screen coming soon!", Toast.LENGTH_SHORT).show();
            updateButtons(logButton);

            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, new LogFragment())
                    .commit();
        });

        // Makes sure that we always start on home page with button clicked when app starts
        if (savedInstanceState == null) {
            homeButton.performClick();
        }
    }

    //Updates the button that associated with the menu you have entered to be gray,
    //All other buttons are reset to the inactive blue
    private void updateButtons(Button button) {
        Integer activeButtonColor = Color.DKGRAY;
        Integer inactiveButtonColor = Color.parseColor("#8FE3EB");

        homeButton.setBackgroundColor(inactiveButtonColor);
        summaryButton.setBackgroundColor(inactiveButtonColor);
        logButton.setBackgroundColor(inactiveButtonColor);

        button.setBackgroundColor(activeButtonColor);
    }

    public static EmojiLog getEmojiLog() {
        return emojiLog;
    }
}