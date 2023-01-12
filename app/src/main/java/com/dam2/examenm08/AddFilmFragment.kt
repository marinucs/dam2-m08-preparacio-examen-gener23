package com.dam2.examenm08

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import com.dam2.examenm08.MainActivity.Companion.filmDbHelper

class AddFilmFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val v = inflater.inflate(R.layout.fragment_add_film, container, false)

        val titleET = v.findViewById<EditText>(R.id.txtNom)
        val ratingET = v.findViewById<EditText>(R.id.ratingET)
        val spinner = v.findViewById<Spinner>(R.id.catSpinner)
        val saveBtn = v.findViewById<Button>(R.id.saveBtn)

        val adapter = ArrayAdapter.createFromResource(
            v.context, R.array.categories, android.R.layout.simple_spinner_item
        )

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = adapter

        saveBtn.setOnClickListener {
            if (titleET.text.toString().isNotEmpty() && ratingET.text.toString().isNotEmpty() && spinner.selectedItem != null) {
                filmDbHelper.insertFilm(
                    Film(
                        0,
                        titleET.text.toString(),
                        ratingET.text.toString().toInt(),
                        spinner.selectedItem.toString()
                    )
                )
            } else {
                Toast.makeText(
                    context, getString(R.string.no_blank_fields), Toast.LENGTH_LONG).show()
            }
        }

        val clearBtn : Button = v.findViewById(R.id.btnClear)

        clearBtn.setOnClickListener {

            val builder = AlertDialog.Builder(requireContext())
            builder.setMessage("Are you sure you want to delete all entries?")
                .setPositiveButton("OK") { _, _ ->
                    filmDbHelper.deleteFilms(0)
                    Toast.makeText(activity, getString(R.string.totEliminat), Toast.LENGTH_SHORT)
                        .show()
                }.setNegativeButton("Cancel") { _, _ ->
                    Toast.makeText(
                        activity,
                        getString(R.string.resEliminat),
                        Toast.LENGTH_SHORT
                    ).show()
                }
            builder.create().show()

        }

        return v

    }
}