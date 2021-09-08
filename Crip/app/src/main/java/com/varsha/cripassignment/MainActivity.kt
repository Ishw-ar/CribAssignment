package com.varsha.cripassignment

import android.Manifest
import android.content.pm.PackageManager
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private val listView = ArrayList<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_SMS) != PackageManager.PERMISSION_GRANTED)
        {
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.READ_SMS), 0)
        }
        else{
            val uri = Uri.parse("content://sms/inbox")
            val cursor = contentResolver.query(uri, null, null, null, null)
             while (cursor!!.moveToNext() == true){
                 val PhoneMessage = cursor.getString(12)
                 val PhoneNumber = cursor.getString(2)
                 listView.add("$PhoneNumber \n $PhoneMessage")
             }
        }
        val listAdapter = ArrayAdapter(this,android.R.layout.simple_list_item_2,listView)
        list_view.adapter = listAdapter
    }
}






