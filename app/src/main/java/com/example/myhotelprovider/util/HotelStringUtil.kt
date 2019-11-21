package com.example.myhotelprovider.util

object HotelStringUtil {

    fun String.toRoom() : String {
        return "Room - $this"
    }
}