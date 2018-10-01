package david.com.ageproject;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by David on 12/12/2017.
 */

public class EventRequest extends StringRequest {

    private static final String REGISTER_EVENT_URL = "http://unbroken-rib.000webhostapp.com/events.php";
    private Map<String, String> params;
    public EventRequest(String name, String where, String time, String description, String user, String date, Response.Listener<String> listener) {
        super(Method.POST, REGISTER_EVENT_URL, listener, null);
        params = new HashMap<>();
        params.put("eventName", name);
        params.put("eventWhere", where);
        params.put("eventTime", time);
        params.put("eventDesc", description);
        params.put("user", user);
        params.put("eventDate", date);
    }

    public Map<String, String> getParams() { return params; }
}
