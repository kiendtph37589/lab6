package com.kiendtph37589.volley;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {
    TextView tv_demo1Kq;
    Button btn_demo1;
    Context context =this;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv_demo1Kq = findViewById(R.id.tv_demo1Kq);
        btn_demo1 = findViewById(R.id.btn_demo1);
        btn_demo1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                docDuLieu(context,tv_demo1Kq);
            }
        });
    }
    String strJSON="";
    void docDuLieu(Context context,TextView tv_demo1Kq){
        //b1. tao request
        RequestQueue queue = Volley.newRequestQueue(context);
        //b2. url
        String url = "https://hungnttg.github.io/array_json_new.json";
        //b3-Tao request
        //tao mang cua cac doi tuong
        //JsonArrayRequest(url,thanhcong,thatbai)
        JsonArrayRequest request = new JsonArrayRequest(url, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray jsonArray) {
                for (int i = 0; i < jsonArray.length(); i++) {
                    try {
                        JSONObject person = jsonArray.getJSONObject(i);
                        String id = person.getString("id");
                        String name = person.getString("name");
                        String gender = person.getString("gender");
                        String email = person.getString("email");

                        strJSON += "Id: "+id+"\n";
                        strJSON += "Name: "+name+"\n";
                        strJSON += "Gender: "+gender+"\n";
                        strJSON += "Email: "+email+"\n";
                    }catch (Exception e){
                        e.printStackTrace();
                    }

                }
                tv_demo1Kq.setText(strJSON);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                tv_demo1Kq.setText(volleyError.getMessage());
            }
        });
        //b4 thuc thi
        queue.add(request);
    }
}