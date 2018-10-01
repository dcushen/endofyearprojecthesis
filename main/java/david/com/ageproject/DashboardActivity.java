package david.com.ageproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class DashboardActivity extends AppCompatActivity {

    Button calendarButton, memoryButton, dropDetectorButton, noteButton;
    TextView welcomeText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        calendarButton = (Button)findViewById(R.id.calendarButton);
        memoryButton = (Button)findViewById(R.id.memoryButton);
        dropDetectorButton = (Button)findViewById(R.id.dropDetectorButton);
        noteButton = (Button) findViewById(R.id.notesButton);
        welcomeText = (TextView) findViewById(R.id.welcomeText);


        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        String message = "Welcome back to us " + name;
        welcomeText.setText(message);
        calendarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent calendarIntent = new Intent(DashboardActivity.this, CalendarActivity.class);
                startActivity(calendarIntent);

            }
        });

        noteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent notesIntent = new Intent(DashboardActivity.this, NotesActivity.class);
                startActivity(notesIntent);

            }
        });
        memoryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent memoryIntent = new Intent(DashboardActivity.this, MemoryGameActivity.class);
                startActivity(memoryIntent);
            }
        });

        dropDetectorButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent dropIntent = new Intent(DashboardActivity.this, DropDetector.class);
                startActivity(dropIntent);
            }
        });

    }
}
