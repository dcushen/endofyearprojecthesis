package david.com.ageproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MemoryGameActivity extends AppCompatActivity {

    Button fourByFour;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            setContentView(R.layout.activity_memory_game);
        }catch (Exception e) {
            Log.e("MYAPP", "exception: " + Log.getStackTraceString(e));
        }
        fourByFour = (Button)findViewById(R.id.fourByFourButton);
        fourByFour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent startFourByFour = new Intent(MemoryGameActivity.this, fourByFourGame.class);
                startActivity(startFourByFour);

            }
        });
    }
}
