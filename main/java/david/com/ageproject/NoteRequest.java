package david.com.ageproject;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by David on 12/12/2017.
 */

public class NoteRequest extends StringRequest {

    private static final String REGISTER_EVENT_URL = "http://unbroken-rib.000webhostapp.com/addNote.php";
    private Map<String, String> params;
    public NoteRequest(String noteContent, String username, String date, Response.Listener<String> listener) {
        super(Method.POST, REGISTER_EVENT_URL, listener, null);
        params = new HashMap<>();
        params.put("noteContent", noteContent);
        params.put("user", username);
        params.put("noteDate", date);
    }

    public Map<String, String> getParams() { return params; }
}
