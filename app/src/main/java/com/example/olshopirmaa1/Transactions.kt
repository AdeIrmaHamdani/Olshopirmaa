package com.example.olshopirmaa1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.widget.EditText
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_dbtransaksi.*
import java.util.*
import kotlinx.android.synthetic.main.activity_main.*

class Transactions : AppCompatActivity() {
    lateinit var date1: EditText
    lateinit var time: EditText

    @SuppressLint("Range")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dbtransaksi)

        // on below line we are initializing our variables.
        date1=findViewById(R.id.enterDate)
        time=findViewById(R.id.enterJam)

        // on below line we are adding
        // click listener for our edit text.
        date1.setOnClickListener {

            // on below line we are getting
            // the instance of our calendar.
            val c=Calendar.getInstance()

            // on below line we are getting
            // our day, month and year.
            val year=c.get(Calendar.YEAR)
            val month=c.get(Calendar.MONTH)
            val day=c.get(Calendar.DAY_OF_MONTH)

            // on below line we are creating a
            // variable for date picker dialog.
            val datePickerDialog=DatePickerDialog(
                // on below line we are passing context.
                this,
                { view, year, monthOfYear, dayOfMonth ->
                    // on below line we are setting
                    // date to our edit text.
                    val dat=(dayOfMonth.toString() + "-" + (monthOfYear + 1) + "-" + year)
                    date1.setText(dat)
                },
                // on below line we are passing year, month
                // and day for the selected date in our date picker.
                year,
                month,
                day
            )
            // at last we are calling show
            // to display our date picker dialog.
            datePickerDialog.show()
        }

        time.setOnClickListener {
            val c=Calendar.getInstance()

            // on below line we are getting our hour, minute.
            val hour=c.get(Calendar.HOUR_OF_DAY)
            val minute=c.get(Calendar.MINUTE)

            // on below line we are initializing
            // our Time Picker Dialog
            val timePickerDialog=TimePickerDialog(
                this,
                { view, hourOfDay, minute ->
                    // on below line we are setting selected
                    // time in our text view.
                    time.setText("$hourOfDay:$minute")
                },
                hour,
                minute,
                false
            )
            // at last we are calling show to
            // display our time picker dialog.
            timePickerDialog.show()
        }

        // below code is to add on click
        // listener to our add name button
        addName.setOnClickListener {

            // below we have created
            // a new DBHelper class,
            // and passed context to it
            val db=DBTransaksi(this, null)

            // creating variables for values
            // in name and age edit texts
            val tanggal=enterDate.text.toString()
            val jam=enterJam.text.toString()
            val kode=enterKode.text.toString()
            val Makan=enterMakan.text.toString()
            val Price=enterPrice.text.toString()

            // calling method to add
            // name to our database
            db.addName(tanggal, jam, kode, Makan, Price)


            // Toast to message on the screen
            Toast.makeText(this, kode + " added to database", Toast.LENGTH_LONG).show()

            // at last, clearing edit texts
            enterDate.text.clear()
            enterJam.text.clear()
            enterKode.text.clear()
            enterMakan.text.clear()
            enterPrice.text.clear()
        }

        // below code is to add on  click
        // listener to our print name button
        printName.setOnClickListener {

            // creating a DBHelper class
            // and passing context to it
            val db=DBTransaksi(this, null)

            // below is the variable for cursor
            // we have called method to get
            // all names from our database
            // and add to name text view
            val cursor=db.getName()

            // moving the cursor to first position and
            // appending value in the text view
            cursor!!.moveToFirst()
            Tanggal.append(cursor.getString(cursor.getColumnIndex(DBTransaksi.TANGGAL_COl)) + "\n")
            Waktu.append(cursor.getString(cursor.getColumnIndex(DBTransaksi.JAM_COl)) + "\n")
            Kode.append(cursor.getString(cursor.getColumnIndex(DBTransaksi.KODE_COl)) + "\n")
            Makan.append(cursor.getString(cursor.getColumnIndex(DBTransaksi.MAKANAN_COL)) + "\n")
            Price.append(cursor.getString(cursor.getColumnIndex(DBTransaksi.PRICE_COL)) + "\n")

            // moving our cursor to next
            // position and appending values
            while (cursor.moveToNext()) {
                Tanggal.append(cursor.getString(cursor.getColumnIndex(DBTransaksi.TANGGAL_COl)) + "\n")
                Waktu.append(cursor.getString(cursor.getColumnIndex(DBTransaksi.JAM_COl)) + "\n")
                Kode.append(cursor.getString(cursor.getColumnIndex(DBTransaksi.KODE_COl)) + "\n")
                Makan.append(cursor.getString(cursor.getColumnIndex(DBTransaksi.MAKANAN_COL)) + "\n")
                Price.append(cursor.getString(cursor.getColumnIndex(DBTransaksi.PRICE_COL)) + "\n")
            }

            // at last we close our cursor
            cursor.close()
        }
    }
}