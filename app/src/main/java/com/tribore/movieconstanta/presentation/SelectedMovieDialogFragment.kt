package com.tribore.movieconstanta.presentation

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import com.tribore.movieconstanta.R

class SelectedMovieDialogFragment: DialogFragment() {

    companion object {
        private const val TITLE_KEY = "KeyTitleMovieDialogFragment"
    }


    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val movieTitle = arguments?.getString(TITLE_KEY) ?: ""

        return activity?.let {
            val builder = AlertDialog.Builder(it)
            builder.setMessage(
                requireActivity().getString(
                    R.string.txt_selected_movie,
                    movieTitle
                )
            )
                .setPositiveButton(R.string.txt_ok) { _, _ -> }
            builder.create()
        } ?: throw IllegalStateException("Активити не может быть нулабельным.")
    }

}