package com.example.myhotelprovider.view

import android.content.DialogInterface
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.room.Room
import com.example.myhotelprovider.R
import com.example.myhotelprovider.util.HotelStringUtil.toRoom
import com.example.myhotelprovider.database.GuestEntity
import com.example.myhotelprovider.database.HotelDatabase
import kotlinx.android.synthetic.main.activity_main.*

class HomeActivity : AppCompatActivity() {

    lateinit var hotelDatabase: HotelDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        hotelDatabase = Room.databaseBuilder(
            this,
            HotelDatabase::class.java,
            "hotel.db"
        )
            .allowMainThreadQueries()
            .build()


        add_guest_button.setOnClickListener {

            checkForEmptyField()
        }

    }


    override fun onResume() {
        super.onResume()
        readFromProvider()
    }

    private fun readFromProvider() {
        val contentResolver = getContentResolver()
        val contentUrl = "content://com.example.myhotelprovider.provider.MyHotelProvider/Guests"

        val uri = Uri.parse(contentUrl)

        val cursor = contentResolver.query(uri, null, null, null, null)

        cursor?.moveToFirst()
        while(cursor?.moveToNext() == true){
            Log.d("TAG_X", cursor.getString(cursor.getColumnIndex("guest_name")))
        }

        cursor?.close()
    }

    private fun checkForEmptyField() {
        if((guest_name_edittext.text.isNullOrBlank() || room_number_edittext.text.isNullOrBlank() || room_price_edittext.text.isNullOrBlank())){

            AlertDialog.Builder(this)
                .setTitle("Empty Fields!")
                .setMessage("None of the fields should be empty. Please fill in all the necessary information.")
                .setPositiveButton("Ok", object : DialogInterface.OnClickListener {
                    override fun onClick(dialog: DialogInterface?, which: Int) {
                        dialog?.cancel()
                    }
                }
                )
                .create()
                .show()

        }
        else {
            insertGuest()
            clearFields()
        }
    }

    private fun clearFields(){
        guest_name_edittext.text.clear()
        room_number_edittext.text.clear()
        room_price_edittext.text.clear()
    }

    private fun insertGuest(){

        val guestName = guest_name_edittext.text.toString()
        val roomNumber = room_number_edittext.text.toString().toRoom()
        val roomPrice = Integer.parseInt(room_price_edittext.text.toString())
        val guest = GuestEntity(guestName, roomNumber, roomPrice)
        hotelDatabase.HotelDAO().addNewGuest(guest)
        Toast.makeText(this, "Guest : " + guest.guestName + " is checked into " + guest.roomNumber, Toast.LENGTH_SHORT).show()

    }
}
