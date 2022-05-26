package com.example.petrecyclerview

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.example.petrecyclerview.databinding.SingleNoteFragmentBinding
import java.time.Instant
import java.time.LocalDateTime
import java.util.*

class SingleNoteFragment : Fragment() {

    private lateinit var viewModel: SingleNoteViewModel

    private lateinit var binding: SingleNoteFragmentBinding

        override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

            binding = DataBindingUtil.inflate(inflater,R.layout.single_note_fragment,container,false)

            viewModel =ViewModelProvider(this).get(SingleNoteViewModel::class.java)

            val args = SingleNoteFragmentArgs.fromBundle(requireArguments())

            binding.textView2.text = viewModel.noteToStringForThisFragment(args.note)


                val calendar: Calendar = Calendar.getInstance()
                calendar.timeInMillis = args.note.date_start * 1000
                Log.i("TAG", "год: ${calendar.time.year}, ${calendar.time}")


            binding.button2.setOnClickListener {
                view?.findNavController()?.navigate(SingleNoteFragmentDirections.actionSingleNoteFragmentToNoteFragment2(calendar))
            }

        return binding.root //inflater.inflate(R.layout.single_note_fragment, container, false)
    }
}