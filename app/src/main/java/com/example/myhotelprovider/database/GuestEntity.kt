package com.example.myhotelprovider.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Guests")
data class GuestEntity(
    @PrimaryKey(autoGenerate = true) var guestID: Int?,
    @ColumnInfo(name = "guest_name") var guestName: String,
    @ColumnInfo(name = "room_number") var roomNumber: String,
    @ColumnInfo(name = "room_price") var roomPrice: Int) {

    constructor(guestName: String, roomNumber: String, roomPrice: Int): this(
        null,
        guestName,
        roomNumber,
        roomPrice
    )

}
