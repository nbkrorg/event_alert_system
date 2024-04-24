package com.example.eas;


import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity3 extends AppCompatActivity {
    EditText FullName, UserName, Email, Password, ConfirmPassword;
    Button SingnUp;
    String Fname, Uname, email, pwd, cpwd;
    String url = "here you have to paste ur URL from where you are retrieving it..."

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        FullName = findViewById(R.id.FullName);
        UserName = findViewById(R.id.UserName);
        Email = findViewById(R.id.Email);
        Password = findViewById(R.id.Password);
        ConfirmPassword = findViewById(R.id.confirmPassword);
        SingnUp = findViewById(R.id.SignUp);
        SingnUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fname = FullName.getText().toString().trim();
                Uname = UserName.getText().toString().trim();
                email = Email.getText().toString().trim();
                pwd = Password.getText().toString().trim();
                cpwd = ConfirmPassword.getText().toString().trim();

                if (TextUtils.isEmpty(Fname) || TextUtils.isEmpty(Uname) || TextUtils.isEmpty(email) || TextUtils.isEmpty(pwd) || TextUtils.isEmpty(cpwd)) {
                    Toast.makeText(MainActivity3.this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                    Toast.makeText(MainActivity3.this, "Please enter a valid email address", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (!pwd.equals(cpwd)) {
                    Toast.makeText(MainActivity3.this, "Passwords do not match", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Check password complexity
                if (!isPasswordValid(pwd)) {
                    Toast.makeText(MainActivity3.this, "Password must contain at least one uppercase letter, one lowercase letter, one special symbol, one digit, and have a minimum length of 8 characters", Toast.LENGTH_SHORT).show();
                    return;
                }

                // If all validations pass, proceed with sending data to server
                sendUserDataToServer();
            }
        });
    }

    private boolean isPasswordValid(String password) {
        Pattern pattern;
        Matcher matcher;
        final String PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$";
        pattern = Pattern.compile(PASSWORD_PATTERN);
        matcher = pattern.matcher(password);
        return matcher.matches();
    }

    private void sendUserDataToServer() {
        StringRequest rq = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(MainActivity3.this, response, Toast.LENGTH_LONG).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Toast.makeText(MainActivity3.this, volleyError.getMessage(), Toast.LENGTH_LONG).show();
            }
        }) {

            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> para = new HashMap<String, String>();
                para.put("name", Uname);
                para.put("fname", Fname);
                para.put("email", email);
                para.put("password", pwd);

                return para;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(MainActivity3.this);
        requestQueue.add(rq);

        // Clear input fields after sending data
        clearInputFields();
    }

    private void clearInputFields() {
        FullName.setText("");
        UserName.setText("");
        Email.setText("");
        Password.setText("");
        ConfirmPassword.setText("");
    }
}
