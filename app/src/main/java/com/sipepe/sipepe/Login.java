package com.sipepe.sipepe;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.sipepe.sipepe.Util.AppController;
import com.sipepe.sipepe.Util.ServerAPI;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import static com.sipepe.sipepe.Util.ServerAPI.URL_MAHASISWA;
import static java.lang.Integer.parseInt;

public class Login extends AppCompatActivity {
    TextView login_mahasiswa,login_admin;
    EditText user,pass;
    String password,username;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        login_mahasiswa=findViewById(R.id.login_mahasiswa);
        login_admin=findViewById(R.id.login_admin);
        user=findViewById(R.id.user);
        pass=findViewById(R.id.pass);

        login_mahasiswa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                username = user.getText().toString();
                password = pass.getText().toString();

                if (username.isEmpty() || password.isEmpty()) {
                    Toast.makeText(Login.this, "Mohon isi Username dan Password", Toast.LENGTH_SHORT).show();
                } else {
                    login_mahasiswa(username, password);
                }
            }
        });
        login_admin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                username = user.getText().toString();
                password = pass.getText().toString();

                if (username.isEmpty() || password.isEmpty()) {
                    Toast.makeText(Login.this, "Mohon isi Username dan Password", Toast.LENGTH_SHORT).show();
                } else {
                    login_admin(username, password);
                }
            }
        });
    }
    public void login_mahasiswa(final String username, final String password){

        StringRequest reqData = new StringRequest(Request.Method.POST, ServerAPI.URL_MAHASISWA,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONArray event = new JSONArray(response);
                            Log.d("tampil","response : " + response);
                            for(int i = 0 ; i < event.length(); i++) {
                                try {
                                    JSONObject wow = event.getJSONObject(i);
                                    String nim = wow.getString("nim");
                                    String password = wow.getString("password");
                                    String username = wow.getString("username");
                                    String mahasiswa = wow.getString("mahasiswa");
                                    SharedPreferences shared = getSharedPreferences("Login", Context.MODE_PRIVATE);
                                    SharedPreferences.Editor editor = shared.edit();
                                    editor.putString("nim",nim);
                                    editor.putString("mahasiswa",mahasiswa);
                                    editor.putString("username",username);
                                    editor.putString("password",password);
                                    editor.putString("rule","mahasiswa");
                                    editor.commit();
                                    Intent intent = new Intent(Login.this,MainActivity.class);
                                    startActivity(intent);
                                    finish();

                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }

                            }}catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("tampil", "error : " + error.getMessage());
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> map = new HashMap<>();
                map.put("username",username);
                map.put("password",password);
                return map;
            }
        };

        AppController.getInstance().addToRequestQueue(reqData);

    }
    public void login_admin(final String username, final String password){

        StringRequest reqData = new StringRequest(Request.Method.POST, ServerAPI.URL_ADMIN,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONArray event = new JSONArray(response);
                            Log.d("tampil","response : " + response);
                            for(int i = 0 ; i < event.length(); i++) {
                                try {
                                    JSONObject wow = event.getJSONObject(i);
                                    String password = wow.getString("password");
                                    String username = wow.getString("username");
                                    String id = wow.getString("id");
                                    SharedPreferences shared = getSharedPreferences("Login", Context.MODE_PRIVATE);
                                    SharedPreferences.Editor editor = shared.edit();
                                    editor.putString("id",id);
                                    editor.putString("username",username);
                                    editor.putString("password",password);
                                    editor.putString("rule","admin");
                                    editor.commit();
                                    Intent intent = new Intent(Login.this,MainActivity.class);
                                    startActivity(intent);
                                    finish();

                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }

                            }}catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("tampil", "error : " + error.getMessage());
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> map = new HashMap<>();
                map.put("username",username);
                map.put("password",password);
                return map;
            }
        };

        AppController.getInstance().addToRequestQueue(reqData);

    }
}

