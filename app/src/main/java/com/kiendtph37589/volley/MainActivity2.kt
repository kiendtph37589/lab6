package com.kiendtph37589.volley

import android.content.Context
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.Volley

class MainActivity2 : AppCompatActivity() {
    var context : Context = this
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        //anh xa
        var tv_demoKQ = findViewById<TextView>(R.id.tv_demoKQ)
        var btn_demo = findViewById<Button>(R.id.btn_demo)
        btn_demo!!.setOnClickListener{
            docDuLieu(context,tv_demoKQ)// goi ham
        }
    }
    // viet ham doc du lieu tu API
    var strJSON="";
    fun docDuLieu(context: Context, tv : TextView){
        //b1. tao request
        val queue = Volley.newRequestQueue(context)
        // b2. xac dinh url
        var url = "https://hungnttg.github.io/array_json_new.json"
        //b3. goi request
        //JsonArrayRequest(url,thanhcong,thatbai
                //jonObject
        val  request = JsonArrayRequest(url,{response->
            for (i in 0 until response.length()){
                    try {
                        val person = response.getJSONObject(i)//lay ve doi tuong
                        val id = person.getString("id")//lay id
                        val name = person.getString("name")//lay name
                        val email = person.getString("email")//lay email
                        //noi chuoi(cho don gian)
                        strJSON += "Id: $id\n"
                        strJSON += "Name: $name\n"
                        strJSON += "Email: $email\n"

                    }catch (e: Exception){
                        e.printStackTrace()
                    }
                                            }
            // dua du lieu len textView
            tv.text=strJSON
        },{ error -> tv.text=error.message })
        //b4. thuc thi request
        queue.add(request)
    }
}