package com.example.eas;

import android.os.Bundle;
import android.view.ViewGroup;
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

public class MainActivity5 extends AppCompatActivity {
    String url = "https://bhargava63.000webhostapp.com/history.php";
    LinearLayout eventLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main5);

        eventLayout = findViewById(R.id.scrollViewLayout);

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

                                    for (int i = 0; i < eventsArray.length(); i++) {
                                        JSONObject event = eventsArray.getJSONObject(i);
                                        String name = event.getString("name");
                                        String date = event.getString("date");
                                        String venue = event.getString("venue");
                                        String time = event.getString("time");
                                        String org = event.getString("org");
                                        String contact = event.getString("contact");

                                        addEventDetails(eventLayout, name, date, venue, time, org, contact, i);
                                    }
                                } else {
                                    // Handle no events found
                                    Toast.makeText(MainActivity5.this, "No events found", Toast.LENGTH_SHORT).show();
                                }
                            } else {
                                // Handle error status
                                Toast.makeText(MainActivity5.this, message, Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(MainActivity5.this, "Error parsing JSON", Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                        Toast.makeText(MainActivity5.this, "Error fetching data", Toast.LENGTH_SHORT).show();
                    }
                });

        // Add the request to the RequestQueue
        RequestQueue requestQueue = Volley.newRequestQueue(MainActivity5.this);
        requestQueue.add(stringRequest);
    }

    private void addEventDetails(LinearLayout parentLayout, String name, String date, String venue, String time, String org, String contact, int position) {
        // Create a LinearLayout to hold the event details
        LinearLayout eventWrapperLayout = new LinearLayout(this);
        eventWrapperLayout.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        eventWrapperLayout.setOrientation(LinearLayout.VERTICAL);
        eventWrapperLayout.setBackgroundResource(R.drawable.rounded_rectangle_shadow); // Add rectangle background

        // Choose background color based on position
        int backgroundColor;
        if (position % 2 == 0) {
            backgroundColor = getResources().getColor(R.color.pale_yellow);
        } else {
            backgroundColor = getResources().getColor(R.color.pale_orange);
        }
        eventWrapperLayout.setBackgroundColor(backgroundColor);

        // Create a LinearLayout to hold each detail of the event
        LinearLayout eventDetailsLayout = new LinearLayout(this);
        eventDetailsLayout.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        eventDetailsLayout.setOrientation(LinearLayout.VERTICAL);
        eventDetailsLayout.setPadding(16, 16, 16, 16);

        // Create TextViews for each detail
        TextView nameTextView = createTextView("Name: " + name);
        TextView dateTextView = createTextView("Date: " + date);
        TextView venueTextView = createTextView("Venue: " + venue);
        TextView timeTextView = createTextView("Time: " + time);
        TextView orgTextView = createTextView("Organisation: " + org);
        TextView contactTextView = createTextView("Contact: " + contact);

        // Add TextViews to the event details layout
        eventDetailsLayout.addView(nameTextView);
        eventDetailsLayout.addView(dateTextView);
        eventDetailsLayout.addView(venueTextView);
        eventDetailsLayout.addView(timeTextView);
        eventDetailsLayout.addView(orgTextView);
        eventDetailsLayout.addView(contactTextView);

        // Add event details layout to the wrapper layout
        eventWrapperLayout.addView(eventDetailsLayout);

        // Add event wrapper layout to the parent layout
        parentLayout.addView(eventWrapperLayout);

        // Add margin to the event wrapper layout
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) eventWrapperLayout.getLayoutParams();
        layoutParams.setMargins(0, 0, 0, 24); // Adjust margin as needed
        eventWrapperLayout.setLayoutParams(layoutParams);
    }

    private TextView createTextView(String text) {
        TextView textView = new TextView(this);
        textView.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        textView.setText(text);
        textView.setTextColor(getResources().getColor(android.R.color.black));
        textView.setTextSize(16);
        textView.setPadding(8, 8, 8, 8);
        return textView;
    }
}
