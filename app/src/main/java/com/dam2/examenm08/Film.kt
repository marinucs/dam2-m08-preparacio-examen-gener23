package com.dam2.examenm08

class Film(private var id:Int = 0, private val title:String, private var rating:Int, private var category:String) {

    fun getTitle() = title

    fun getRating() = rating

    fun getCategory() = category

    override fun toString() = "$title. $rating. $category"

}