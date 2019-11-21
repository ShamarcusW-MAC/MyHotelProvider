package com.example.myhotelprovider.database

import android.database.Cursor
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface HotelDAO {

    @Insert
    fun addNewGuest(guestEntity: GuestEntity)


    @Query("SELECT * FROM Guests")
    fun retrieveAllGuests() : Cursor

    @Delete
    fun deleteFromGuests(guestEntity: GuestEntity)
}