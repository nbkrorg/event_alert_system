package com.example.eas;

import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity9 extends AppCompatActivity {
    String url = "https://bhargava63.000webhostapp.com/history.php";
    LinearLayout eventLayout;
    Typeface audiowide;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main9);

        eventLayout = findViewById(R.id.eventLayout);

        Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                try {
                                    JSONObject jsonObject = new JSONObject(response);
                                    String status = jsonObject.getString("status");
                                    String message = jsonObject.getString("message");

                                    if (status.equals("success")) {
                                        JSONArray eventsArray = jsonObject.getJSONArray("events");
                                        if (eventsArray.length() > 0) {
                                            // Clear existing views
                                            eventLayout.removeAllViews();

                                            // Add column headers
                                            LinearLayout headerRowLayout = new LinearLayout(MainActivity9.this);
                                            headerRowLayout.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
                                            headerRowLayout.setOrientation(LinearLayout.HORIZONTAL);

                                            TextView header1TextView = new TextView(MainActivity9.this);
                                            header1TextView.setText("Event ID");
                                            header1TextView.setTextSize(20);
                                            header1TextView.setTypeface(audiowide, Typeface.BOLD);
                                            header1TextView.setLayoutParams(new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 1));

                                            TextView header2TextView = new TextView(MainActivity9.this);
                                            header2TextView.setText("Name");
                                            header2TextView.setTextSize(20);
                                            header2TextView.setTypeface(audiowide, Typeface.BOLD);
                                            LinearLayout.LayoutParams params2 = new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 1);
                                            params2.setMargins(50, 0, 50, 0);
                                            header2TextView.setLayoutParams(params2);

                                            TextView header3TextView = new TextView(MainActivity9.this);
                                            header3TextView.setText("Date");
                                            header3TextView.setTextSize(20);
                                            header3TextView.setTypeface(audiowide, Typeface.BOLD);
                                            header3TextView.setLayoutParams(new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 1));

                                            headerRowLayout.addView(header1TextView);
                                            headerRowLayout.addView(header2TextView);
                                            headerRowLayout.addView(header3TextView);

                                            eventLayout.addView(headerRowLayout);

                                            // Add event rows
                                            for (int i = 0; i < eventsArray.length(); i++) {
                                                JSONObject eventObject = eventsArray.getJSONObject(i);
                                                String eventId = eventObject.getString("id");
                                                String eventName = eventObject.getString("name");
                                                String eventDate = eventObject.getString("date");

                                                LinearLayout eventRowLayout = new LinearLayout(MainActivity9.this);
                                                eventRowLayout.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
                                                eventRowLayout.setOrientation(LinearLayout.HORIZONTAL);

                                                TextView eventIdTextView = new TextView(MainActivity9.this);
                                                eventIdTextView.setText(eventId);
                                                eventIdTextView.setTextSize(20);
                                                eventIdTextView.setTypeface(audiowide);
                                                eventIdTextView.setLayoutParams(new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 1));

                                                TextView eventNameTextView = new TextView(MainActivity9.this);
                                                eventNameTextView.setText(eventName);
                                                eventNameTextView.setTextSize(20);
                                                eventNameTextView.setTypeface(audiowide);
                                                LinearLayout.LayoutParams params3 = new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 1);
                                                params3.setMargins(50, 0, 50, 0);
                                                eventNameTextView.setLayoutParams(params3);

                                                TextView eventDateTextView = new TextView(MainActivity9.this);
                                                eventDateTextView.setText(eventDate);
                                                eventDateTextView.setTextSize(20);
                                                eventDateTextView.setTypeface(audiowide);
                                                eventDateTextView.setLayoutParams(new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 1));

                                                eventRowLayout.addView(eventIdTextView);
                                                eventRowLayout.addView(eventNameTextView);
                                                eventRowLayout.addView(eventDateTextView);

                                                eventLayout.addView(eventRowLayout);
                                            }

                                        } else {
                                            // Handle no events found
                                            Toast.makeText(MainActivity9.this, "No events found", Toast.LENGTH_SHORT).show();
                                        }
                                    } else {
                                        // Handle error status
                                        Toast.makeText(MainActivity9.this, message, Toast.LENGTH_SHORT).show();
                                    }
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                    Toast.makeText(MainActivity9.this, "Error parsing JSON", Toast.LENGTH_SHORT).show();
                                }
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                error.printStackTrace();
                                Toast.makeText(MainActivity9.this, "Error fetching data", Toast.LENGTH_SHORT).show();
                            }
                        });

                // Add the request to the RequestQueue
                RequestQueue requestQueue = Volley.newRequestQueue(MainActivity9.this);
                requestQueue.add(stringRequest);
            }
        });
    }
}