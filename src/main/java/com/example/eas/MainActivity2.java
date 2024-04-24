package com.example.eas;

import android.content.Intent;
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

public class MainActivity2 extends AppCompatActivity {
    EditText Uname,pwd;
    String url="https://bhargava63.000webhostapp.com/login.php";
    String uname,pass;
    Button Login;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Uname=findViewById(R.id.uname);
        pwd=findViewById(R.id.pwd);
        Login=findViewById(R.id.Login);
        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                uname=Uname.getText().toString().trim();
                pass=pwd.getText().toString().trim();
                StringRequest rq = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if(response.equals("user")){
                            Intent intent = new Intent(MainActivity2.this, MainActivity5.class);
                            startActivity(intent);
                        }
                        else if(response.equals("admin")){
                            Intent intent = new Intent(MainActivity2.this, MainActivity4.class);
                            startActivity(intent);
                        }
                        Toast.makeText(MainActivity2.this, response, Toast.LENGTH_SHORT).show();
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        Toast.makeText(MainActivity2.this, volleyError.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }){

                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String, String> para = new HashMap<String, String>();
                        para.put("name", uname);

                        para.put("password", pass);

                        return para;
                    }
                };
                RequestQueue requestQueue = Volley.newRequestQueue(MainActivity2.this);
                requestQueue.add(rq);


                Uname.setText("");

                pwd.setText("");

            }
        });
    }
}