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

/**
 General use of Google Gemini throughout assignment for learning purposes
(Not direct functionality additions, but general conversational querying):
-Used to look into using GridView instead of ListView for formatting emoji buttons.
-Used to get useful formatting attributes for constraint and linear layouts
-Used to break down what classes and methods might be useful for data storage and navigation.
-Explaining the lambda expressions for on click listeners
-Learning about and using Intents
-Used to help refactor when converting to fragments from activities
-How to use snackbar as an alternative to toast, so I can customize it more
-How to turn maps into ArrayLists
 */

/**
 * Purpose:
 * This class acts as the main container for the user interface and defines things like the
 * bottom navigation bar and the fragment container that holds all other menu fragments.
 * This class also holds maps like the emoji map and count map that other functions use as the
 * ground truth for shared and updated data.
 *
 * Design Rationale:
 * - This Class uses a single activity architecture, with different fragments for
 *   each app functionality. This allows for the constant bottom navigation bar and transitions
 *   between fragments without reloading the main components.
 * - It centralizes all the shared data, this helps with organization as this class
 *   is the source for any non class specific functionality.
 *
 *
 * Outstanding Issues: None.
 */
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

        //Defining buttons
        logButton = findViewById(R.id.log_button);
        summaryButton = findViewById(R.id.summary_button);
        homeButton = findViewById(R.id.home_button);


        homeButton.setOnClickListener(v -> {
            updateButtons(homeButton);

            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, new HomeFragment())
                    .commit();
        });

        summaryButton.setOnClickListener(v -> {
            updateButtons(summaryButton);

            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, new SummaryFragment())
                    .commit();

        });

        logButton.setOnClickListener(v -> {
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

    //Updates the button that associated with the menu you have entered to be the active colour,
    //All other buttons are reset to the inactive colour
    private void updateButtons(Button button) {
        Integer activeButtonColor = Color.parseColor("#8FE3EB");
        Integer inactiveButtonColor = Color.DKGRAY;

        homeButton.setBackgroundColor(inactiveButtonColor);
        summaryButton.setBackgroundColor(inactiveButtonColor);
        logButton.setBackgroundColor(inactiveButtonColor);

        button.setBackgroundColor(activeButtonColor);
    }

    public static EmojiLog getEmojiLog() {
        return emojiLog;
    }
}