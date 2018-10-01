package david.com.ageproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class ViewItemClicked extends AppCompatActivity {

    TextView where, desc, time, unsure;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_item_clicked);

        where = (TextView)findViewById(R.id.whereTextView);
        desc = (TextView)findViewById(R.id.descTextView);
        time = (TextView)findViewById(R.id.timeTextView);
        unsure = (TextView)findViewById(R.id.textView4);

        Intent intent = getIntent();
        Bundle b = intent.getExtras();
        String a = b.getString("where");
        String[] arrRecd = b.getStringArray("array");
        where.setText("Where: " + arrRecd[1]);
    }
}
