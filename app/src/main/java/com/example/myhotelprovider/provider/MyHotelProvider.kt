package com.example.myhotelprovider.provider

import android.content.ContentProvider
import android.content.ContentValues
import android.content.UriMatcher
import android.database.Cursor
import android.net.Uri
import androidx.room.Room
import com.example.myhotelprovider.database.HotelDatabase

class MyHotelProvider : ContentProvider() {

    val authority = "com.example.myhotelprovider.provider.MyHotelProvider"

    val uriMatcher = UriMatcher(UriMatcher.NO_MATCH)

    private val SINGLE_GUEST = 0
    private val ALL_GUESTS = 1

    private var database: HotelDatabase? = null


    override fun insert(uri: Uri, values: ContentValues?): Uri? {

        return null

    }

    override fun query(
        uri: Uri,
        projection: Array<out String>?,
        selection: String?,
        selectionArgs: Array<out String>?,
        sortOrder: String?
    ): Cursor? {

        var returnCursor: Cursor? = null

        when(uriMatcher.match(uri)) {
            ALL_GUESTS -> {
                returnCursor = database?.HotelDAO()?.retrieveAllGuests()
            }
            SINGLE_GUEST -> {
                //Homework
                returnCursor = null
            }
        }

        return returnCursor
    }

    override fun onCreate(): Boolean {

        uriMatcher.apply {
            addURI(authority, "Guests/#", SINGLE_GUEST)
            addURI(authority, "Guests", ALL_GUESTS)
        }

        context?.let { actualContext ->
            database = Room.databaseBuilder(actualContext,
                HotelDatabase::class.java,
                "hotel.db")
                .allowMainThreadQueries()
                .build()
        }
        return database != null

    }

    override fun update(
        uri: Uri,
        values: ContentValues?,
        selection: String?,
        selectionArgs: Array<out String>?
    ): Int {
        return 0

    }

    override fun delete(uri: Uri, selection: String?, selectionArgs: Array<out String>?): Int {
    return 0
    }

    override fun getType(uri: Uri): String? {

        return null

    }
}