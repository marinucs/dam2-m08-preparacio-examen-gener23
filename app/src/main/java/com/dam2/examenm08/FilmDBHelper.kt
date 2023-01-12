package com.dam2.examenm08

import android.annotation.SuppressLint
import android.database.sqlite.SQLiteOpenHelper
import android.database.sqlite.SQLiteDatabase
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import com.dam2.examenm08.FilmContract.SQL_CREATE_ENTRIES
import com.dam2.examenm08.FilmContract.SQL_DELETE_ENTRIES
import com.dam2.examenm08.FilmContract.TABLE_NAME
import com.dam2.examenm08.FilmContract.COLUMN_NAME_TITLE
import com.dam2.examenm08.FilmContract.COLUMN_NAME_CATEGORY
import com.dam2.examenm08.FilmContract.COLUMN_NAME_RATING

class FilmDBHelper(context: Context?) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(SQL_CREATE_ENTRIES)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL(SQL_DELETE_ENTRIES)
        onCreate(db)
    }

    companion object {
        // If you change the database schema, you must increment the database version.
        const val DATABASE_VERSION = 2
        const val DATABASE_NAME = "filming.db"
    }

    fun insertFilm(film: Film) {
        val db = this.writableDatabase
        val values = ContentValues()
        values.put(COLUMN_NAME_TITLE, film.getTitle())
        values.put(COLUMN_NAME_RATING, film.getRating())
        values.put(COLUMN_NAME_CATEGORY, film.getCategory())
        db.insert(TABLE_NAME, null, values)
    }

    @SuppressLint("Range")
    fun getFilms(): MutableList<Film> {
        val db = this.readableDatabase
        val filmList : MutableList<Film> = mutableListOf()
        val query = "SELECT * FROM $TABLE_NAME"
        val cursor: Cursor = db.rawQuery(query, null)

        cursor.moveToFirst()

        if (cursor.count >= 1) {
            do {
                val film = Film(
                    cursor.getInt(cursor.getColumnIndex("ID")),
                    cursor.getString(cursor.getColumnIndex(COLUMN_NAME_TITLE)),
                    cursor.getInt(cursor.getColumnIndex(COLUMN_NAME_RATING)),
                    cursor.getString(cursor.getColumnIndex(COLUMN_NAME_CATEGORY))
                )

                filmList.add(film)

            } while (cursor.moveToNext())
            cursor.close()
        }
        return filmList
    }

    fun deleteFilms(id: Int) {
        val db = this.writableDatabase
        db.delete(TABLE_NAME, "id>=$id", null)
    }

}