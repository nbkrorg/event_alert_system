package com.example.eas;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class MainActivity8 extends AppCompatActivity {

    EditText Eventid;
    Button del;
    String eveid;
    String url="here you have to paste ur URL from where you are retrieving it..."
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main8);
        Eventid=findViewById(R.id.eventid);

        del=findViewById(R.id.del);
        del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                eveid = Eventid.getText().toString().trim();


                StringRequest rq = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(MainActivity8.this, response, Toast.LENGTH_LONG).show();
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        Toast.makeText(MainActivity8.this, volleyError.getMessage(), Toast.LENGTH_LONG).show();
                    }
                }) {

                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String, String> para = new HashMap<String, String>();
                        para.put("id", eveid);


                        return para;
                    }
                };
                RequestQueue requestQueue = Volley.newRequestQueue(MainActivity8.this);
                requestQueue.add(rq);

                Eventid.setText("");

            }


        });
    }


}
