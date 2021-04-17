package com.example.myscanner

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.google.zxing.integration.android.IntentIntegrator
import org.json.JSONException
import org.json.JSONObject

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnScan : Button = findViewById(R.id.btnScan)

        btnScan.setOnClickListener{
            val qrScan = IntentIntegrator(this)

            qrScan.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE)

            qrScan.initiateScan()
        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {

        val result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data)

        if(result != null){

            val obj: JSONObject = JSONObject(result.contents)

            val tvResult = findViewById<TextView>(R.id.tvResult)

            try{

                tvResult.text = obj.getString("name")

            }catch (e:JSONException){

                tvResult.text = result.contents

            }


        }else{
            Toast.makeText(this,"Empty Result", Toast.LENGTH_LONG).show()
        }

        super.onActivityResult(requestCode, resultCode, data)
    }
}