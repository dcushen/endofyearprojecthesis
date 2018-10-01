package david.com.ageproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class AddEvent extends AppCompatActivity {
    String date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_event);

        final EditText eventName = (EditText) findViewById(R.id.eventName);
        final EditText eventWhere = (EditText) findViewById(R.id.eventWhere);
        final EditText eventTime = (EditText) findViewById(R.id.eventTime);
        final EditText eventUsername = (EditText) findViewById(R.id.eventPerson);
        final EditText eventDescription = (EditText) findViewById(R.id.eventDescription);
        final Button addEventButton = (Button) findViewById(R.id.addEventButton);

        Intent getDate = getIntent();
        date = getDate.getStringExtra("date");

        addEventButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String name = eventName.getText().toString();
                final String where = eventWhere.getText().toString();
                final String time = eventTime.getText().toString();
                final String user = eventUsername.getText().toString();
                final String description = eventDescription.getText().toString();
                final String eventDate = date;


                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("myTag", response);
                        try {
                            JSONObject jsonResponse = new JSONObject(response);
                            boolean success = jsonResponse.getBoolean("success");

                            if(success) {
                                Intent intent = new Intent(AddEvent.this, CalendarActivity.class);
                                AddEvent.this.startActivity(intent);
                            } else {
                                AlertDialog.Builder builder = new AlertDialog.Builder(AddEvent.this);
                                builder.setMessage("Adding event failed").setNegativeButton("Retry", null).create().show();

                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                };

                EventRequest registerEvent = new EventRequest(name, where, time, description, user, eventDate, responseListener);
                RequestQueue queue = Volley.newRequestQueue(AddEvent.this);
                queue.add(registerEvent);
            }
        });
    }
}
