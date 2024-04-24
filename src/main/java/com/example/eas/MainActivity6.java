package com.example.eas;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class MainActivity6 extends AppCompatActivity {
    EditText eventid, eventname, eventdate, eventvenue, eventtime, organizingclub, contactnumber;
    Button Addevent;
    String eid, ename, edate, evenue, etime, oclub, cnumber;
    String url = "here you have to paste ur URL from where you are retrieving it..."

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main6);

        eventid = findViewById(R.id.eventid);
        eventname = findViewById(R.id.eventname);
        eventdate = findViewById(R.id.eventdate);
        eventvenue = findViewById(R.id.eventvenue);
        eventtime = findViewById(R.id.eventtime);
        organizingclub = findViewById(R.id.organizingclub);
        contactnumber = findViewById(R.id.contactnumber);
        Addevent = findViewById(R.id.update);

        Addevent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isValidMobile() && isValidDate()) {
                    // All fields are valid, proceed with sending request
                    sendRequest();
                }
            }
        });
    }

    private boolean isValidMobile() {
        cnumber = contactnumber.getText().toString().trim();
        if (cnumber.isEmpty()) {
            contactnumber.setError("Mobile number is required");
            return false;
        } else if (!android.util.Patterns.PHONE.matcher(cnumber).matches()) {
            contactnumber.setError("Enter a valid mobile number");
            return false;
        }
        return true;
    }

    private boolean isValidDate() {
        edate = eventdate.getText().toString().trim();
        String regex = "^\\d{2}/\\d{2}/\\d{4}$";
        if (!edate.matches(regex)) {
            eventdate.setError("Enter a valid date in dd/mm/yyyy format");
            return false;
        }
        return true;
    }

    private void sendRequest() {
        eid = eventid.getText().toString().trim();
        ename = eventname.getText().toString().trim();
        edate = eventdate.getText().toString().trim();
        evenue = eventvenue.getText().toString().trim();
        etime = eventtime.getText().toString().trim();
        oclub = organizingclub.getText().toString().trim();
        cnumber = contactnumber.getText().toString().trim();

        StringRequest rq = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(MainActivity6.this, response, Toast.LENGTH_LONG).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Toast.makeText(MainActivity6.this, volleyError.getMessage(), Toast.LENGTH_LONG).show();
            }
        }) {
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> para = new HashMap<>();
                para.put("id", eid);
                para.put("ename", ename);
                para.put("date", edate);
                para.put("venue", evenue);
                para.put("time", etime);
                para.put("org", oclub);
                para.put("contact", cnumber);

                return para;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(MainActivity6.this);
        requestQueue.add(rq);

        // Clear input fields after sending request
        eventid.setText("");
        eventname.setText("");
        eventdate.setText("");
        eventvenue.setText("");
        eventtime.setText("");
        organizingclub.setText("");
        contactnumber.setText("");
    }
}
