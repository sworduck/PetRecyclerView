package com.example.petrecyclerview

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.applandeo.materialcalendarview.CalendarView
import com.example.petrecyclerview.databinding.NoteFragmentBinding

class NoteFragment : Fragment() {



    private lateinit var viewModel: NoteViewModel

    private lateinit var binding: NoteFragmentBinding

    private val onClickListener: NoteAdapter.OnNoteClickListener = object :NoteAdapter.OnNoteClickListener{
        override fun onNoteClick(note: Note) {
            view?.findNavController()?.navigate(NoteFragmentDirections.actionNoteFragment2ToSingleNoteFragment(note))
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater,R.layout.note_fragment,container,false)

        viewModel = ViewModelProvider(this)[NoteViewModel::class.java]


        //binding.noteRecyclerView.layoutManager = LinearLayoutManager(this)
        binding.noteRecyclerView.adapter = viewModel.getAdapter(onClickListener)

        binding.calendarView.setOnDayClickListener { eventDay ->
            viewModel.eventOfDay(eventDay)
        }
        viewModel.initRealm(this.requireActivity().applicationContext)

        return binding.root //inflater.inflate(R.layout.note_fragment, container, false)
    }
    //viewModel = ViewModelProvider(this).get(NoteViewModel::class.java)
}