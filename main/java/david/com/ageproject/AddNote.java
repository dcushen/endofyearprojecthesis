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

public class AddNote extends AppCompatActivity {
    String date;
    Button addNoteDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);

        final EditText usernameNote = (EditText) findViewById(R.id.usernameNote);
        final EditText noteContent = (EditText) findViewById(R.id.noteContent);
        addNoteDB = (Button) findViewById(R.id.addNoteDB);

        Intent getDate = getIntent();
        date = getDate.getStringExtra("date");

        addNoteDB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String name = usernameNote.getText().toString();
                final String note = noteContent.getText().toString();

                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("myTag", response);
                        try {
                            JSONObject jsonResponse = new JSONObject(response);
                            boolean success = jsonResponse.getBoolean("success");

                            if(success) {
                                Intent intent = new Intent(AddNote.this, NotesActivity.class);
                                startActivity(intent);
                            } else {
                                AlertDialog.Builder builder = new AlertDialog.Builder(AddNote.this);
                                builder.setMessage("Adding note failed").setNegativeButton("Retry", null).create().show();

                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                };

                NoteRequest noteEvent = new NoteRequest(name, note, date, responseListener);
                RequestQueue queue = Volley.newRequestQueue(AddNote.this);
                queue.add(noteEvent);
            }
        });
    }
}