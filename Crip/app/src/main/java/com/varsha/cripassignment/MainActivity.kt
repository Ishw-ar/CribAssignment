package com.varsha.cripassignment

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val mSmsinboxQueryUri = Uri.parse("content://sms/inbox")
        val cursor1 = contentResolver.query(
            mSmsinboxQueryUri,
            arrayOf("_id", "thread_id", "address", "person", "date", "body", "type"),
            null,
            null,
            null
        )
        startManagingCursor(cursor1)
        val columns = arrayOf("address", "person", "date", "body", "type")
        if (cursor1!!.count > 0) {
            val count = Integer.toString(cursor1.count)
            while (cursor1.moveToNext()) {
                val address = cursor1.getString(cursor1.getColumnIndex(columns[0]))
                if (address.equals("+917091434563", ignoreCase = true)) { //put your number here
                    val name = cursor1.getString(cursor1.getColumnIndex(columns[1]))
                    val date = cursor1.getString(cursor1.getColumnIndex(columns[2]))
                    val body = cursor1.getString(cursor1.getColumnIndex(columns[3]))
                    val type = cursor1.getString(cursor1.getColumnIndex(columns[4]))
                    Log.d("***", "body=$body")
                    testSms.text=body
                }
            }
        }
    }
}






