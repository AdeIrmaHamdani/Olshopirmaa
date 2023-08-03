package com.example.olshopirmaa1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.AdapterView
import android.widget.GridView
import android.widget.Toast
import java.util.*
import kotlin.collections.ArrayList


class MainActivity2 : AppCompatActivity() {
    lateinit var courseGRV: GridView
    lateinit var courseList: List<Gridview>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        courseGRV=findViewById(R.id.idGRV)
        courseList=ArrayList<Gridview>()

        // on below line we are adding data to
        // our course list with image and course name.
        courseList=courseList + Gridview("Menu", R.drawable.img_5)
        courseList=courseList + Gridview("Transaksi", R.drawable.img_1)
        courseList=courseList + Gridview("Developer", R.drawable.img_4)
        courseList=courseList + Gridview("Lokasi", R.drawable.img_2)
        courseList=courseList + Gridview("Berita", R.drawable.img_3)
        courseList=courseList + Gridview("Tentang PPKD", R.drawable.img_6)


        // on below line we are initializing our course adapter
        // and passing course list and context.
        val courseAdapter=GridviewAdapter(courseList=courseList, this@MainActivity2)

        // on below line we are setting adapter to our grid view.
        courseGRV.adapter=courseAdapter

        // on below line we are adding on item
        // click listener for our grid view.
        courseGRV.onItemClickListener=AdapterView.OnItemClickListener { _, _, position, _ ->
            // inside on click method we are simply displaying
            // a toast message with course name.
            Toast.makeText(
                applicationContext, courseList[position].courseName + " selected",
                Toast.LENGTH_SHORT
            ).show()

            if (courseList[position].courseName == "Menu") {
                val i = Intent(this, Menu::class.java)
                startActivity(i)
            }
            else if (courseList[position].courseName == "Transaksi") {
                val i = Intent(this, Transactions::class.java)
                startActivity(i)
            }

            else if (courseList[position].courseName == "Developer") {
                val intent = Intent(this, tentang_developer::class.java)
                startActivity(intent)
            }
            else if (courseList[position].courseName == "Tentang PPKD") {
                val intent = Intent(this, Ppkd::class.java)
                startActivity(intent)
            }

            else if (courseList[position].courseName == "Lokasi") {
                val i = Intent(this, Lokasi::class.java)
                startActivity(i)
            }

            else if (courseList[position].courseName == "Berita") {
                val i = Intent(this, Berita::class.java)
                startActivity(i)
            }

        }
    }
}
