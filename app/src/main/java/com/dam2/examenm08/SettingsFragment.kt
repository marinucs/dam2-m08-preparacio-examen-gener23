package com.dam2.examenm08

import android.content.Context.MODE_PRIVATE
import android.content.res.Configuration
import android.content.res.Resources
import android.os.Bundle
import android.util.DisplayMetrics
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import android.widget.ToggleButton
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import java.util.*


class SettingsFragment : Fragment() {

    private lateinit var btnIdioma: ToggleButton

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {

        val v = inflater.inflate(R.layout.fragment_settings, container, false)
        btnIdioma = v.findViewById(R.id.btnIdioma)
        val sharedPreference =
            requireContext().getSharedPreferences("PREFERENCE_NAME", MODE_PRIVATE)

        if (sharedPreference.getString("lang", "").equals("en")) {
            btnIdioma.toggle()
        }

        btnIdioma.setOnClickListener {
            val localeCode: String

            if (btnIdioma.isChecked) {
                Log.i("idioma", "English")
                localeCode = "en"
            } else {
                localeCode = "ca"
                Log.i("idioma", "Català")
            }
            setAppLocale(localeCode)
        }

        val deletePrefsBtn: Button = v.findViewById(R.id.button3)

        deletePrefsBtn.setOnClickListener {

            val builder = AlertDialog.Builder(requireContext())
            builder.setMessage(getString(R.string.esborraPrefs))
                .setPositiveButton(getString(R.string.ok)) { _, _ ->
                    val editor = sharedPreference?.edit()
                    editor?.clear()?.apply()
                    setAppLocale(sharedPreference?.getString("lang", "ca")!!)
                    Toast.makeText(context, getString(R.string.prefsEsborrades), Toast.LENGTH_LONG)
                        .show()
                }.setNegativeButton(getString(R.string.cancela)) { _, _ ->
                    Toast.makeText(
                        context,
                        getString(R.string.prefsNoEsborrades),
                        Toast.LENGTH_LONG
                    ).show()
                }
            builder.create().show()
        }

        return v
    }

    @Suppress("DEPRECATION")
    private fun setAppLocale(localeCode: String) {
        val resources: Resources = resources
        val dm: DisplayMetrics = resources.displayMetrics

        val config: Configuration = resources.configuration
        config.setLocale(Locale(localeCode.lowercase(Locale.getDefault())))

        resources.updateConfiguration(config, dm)

        val sharedPreference = context?.getSharedPreferences(
            "PREFERENCE_NAME", MODE_PRIVATE
        )
        sharedPreference?.edit()?.putString("lang", localeCode)?.apply()
    }
}