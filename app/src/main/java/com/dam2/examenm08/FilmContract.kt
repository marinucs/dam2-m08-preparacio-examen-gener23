package com.dam2.examenm08

object FilmContract {
    const val TABLE_NAME = "Films"
    const val COLUMN_NAME_TITLE = "Title"
    const val COLUMN_NAME_RATING = "Rating"
    const val COLUMN_NAME_CATEGORY = "Category"

    const val SQL_CREATE_ENTRIES =
        "CREATE TABLE $TABLE_NAME (" +
                "ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                "$COLUMN_NAME_TITLE TEXT," +
                "$COLUMN_NAME_RATING INTEGER," +
                "$COLUMN_NAME_CATEGORY TEXT)"

    const val SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS $TABLE_NAME"
}