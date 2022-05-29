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
        override fun onNoteClick(line:String,calendarNoteFragment: Calendar) {
            view?.findNavController()?.navigate(NoteFragmentDirections.actionNoteFragment2ToSingleNoteFragment(calendarNoteFragment, line))
        }
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater,R.layout.note_fragment,container,false)

        viewModel = ViewModelProvider(this)[NoteViewModel::class.java]
        viewModel.initRealm(this.requireActivity().applicationContext)
        //изменение календаря
        binding.calendarView.setDate(viewModel.calendar.value)
        Log.i("TAG", "create fragment")
        //binding.calendarView.setDate(viewModel.calendar)
        //binding.noteRecyclerView.layoutManager = LinearLayoutManager(this)
        binding.noteRecyclerView.adapter = viewModel.getAdapter(onClickListener)

        //получение аругмента из второго активити

        try {
            val args = NoteFragmentArgs.fromBundle(requireArguments())
            binding.calendarView.setDate(args.calendar)
            Log.i("TAG", "год: ${args.calendar.time.year}, ${args.calendar.time}")
            val calendar = args.calendar
            calendar.set(Calendar.HOUR_OF_DAY, 0);
            calendar.set(Calendar.MINUTE, 0);
            calendar.set(Calendar.SECOND, 0);
            calendar.set(Calendar.MILLISECOND, 0);
            viewModel.eventOfDay(EventDay(calendar))
        }catch (e:Exception){
            val calendar = Calendar.getInstance()
            calendar.set(Calendar.HOUR_OF_DAY, 0);
            calendar.set(Calendar.MINUTE, 0);
            calendar.set(Calendar.SECOND, 0);
            calendar.set(Calendar.MILLISECOND, 0);
            viewModel.eventOfDay(EventDay(calendar))
        }




        //вешаю обсервер на календарь НЕ РАБОТАЕТ
        /*
        viewModel.calendar.observe(viewLifecycleOwner, Observer { newCalendar ->
            binding.calendarView.setDate(newCalendar)
        })

         */

        binding.button.setOnClickListener {
            view?.findNavController()?.navigate(NoteFragmentDirections.actionNoteFragment2ToAddNoteFragment())
        }

        binding.calendarView.setOnDayClickListener { eventDay ->
            //binding.calendarView.setDate(eventDay.calendar)
            viewModel.eventOfDay(eventDay)
        }

        return binding.root //inflater.inflate(R.layout.note_fragment, container, false)
    }

    override fun onDestroy() {
        Log.i("TAG", "destroy fragment")
        super.onDestroy()
    }
    //viewModel = ViewModelProvider(this).get(NoteViewModel::class.java)
}