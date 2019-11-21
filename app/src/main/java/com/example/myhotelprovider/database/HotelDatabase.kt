package com.example.myhotelprovider.database

import androidx.room.Database
import androidx.room.RoomDatabase


@Database(version = 1, entities = [GuestEntity::class])
abstract class HotelDatabase : RoomDatabase() {
    abstract fun HotelDAO() : HotelDAO
}