package com.example.petrecyclerview

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.applandeo.materialcalendarview.EventDay
import com.example.petrecyclerview.databinding.NoteFragmentBinding
import java.util.*

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
        //изменение календаря
        binding.calendarView.setDate(viewModel.calendar.value)
        Log.i("TAG", "create fragment")
        //binding.calendarView.setDate(viewModel.calendar)
        //binding.noteRecyclerView.layoutManager = LinearLayoutManager(this)
        binding.noteRecyclerView.adapter = viewModel.getAdapter(onClickListener)

        //получение аругмента из второго активити

        try {
            val args = NoteFragmentArgs.fromBundle(requireArguments())
            if(args.calendar.timeInMillis!=0L)
            binding.calendarView.setDate(args.calendar)
            Log.i("TAG", "год: ${args.calendar.time.year}, ${args.calendar.time}")
        }catch (e:Exception){

        }


        //вешаю обсервер на календарь НЕ РАБОТАЕТ
        /*
        viewModel.calendar.observe(viewLifecycleOwner, Observer { newCalendar ->
            binding.calendarView.setDate(newCalendar)
        })

         */


        binding.calendarView.setOnDayClickListener { eventDay ->
            //binding.calendarView.setDate(eventDay.calendar)
            viewModel.eventOfDay(eventDay)
        }
        viewModel.initRealm(this.requireActivity().applicationContext)


        //проверка на наличие дел текущего дня
        //этого тут не должно быть, грязно
        /*
        val currentDay = Calendar.getInstance()
        currentDay.set(Calendar.HOUR_OF_DAY, 0);
        currentDay.set(Calendar.MINUTE, 0);
        currentDay.set(Calendar.SECOND, 0);
        currentDay.set(Calendar.MILLISECOND, 0);
        viewModel.eventOfDay(EventDay(currentDay))

         */

        return binding.root //inflater.inflate(R.layout.note_fragment, container, false)
    }

    override fun onDestroy() {
        Log.i("TAG", "destroy fragment")
        super.onDestroy()
    }
    //viewModel = ViewModelProvider(this).get(NoteViewModel::class.java)
}