package com.example.petrecyclerview

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.example.petrecyclerview.databinding.AddNoteFragmentBinding
import com.example.petrecyclerview.databinding.SingleNoteFragmentBinding
import io.realm.Realm
import java.sql.Timestamp
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import java.util.*

class AddNoteFragment : Fragment() {

    private lateinit var viewModel: AddNoteViewModel

    private lateinit var binding: AddNoteFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater,R.layout.add_note_fragment,container,false)

        viewModel = ViewModelProvider(this)[AddNoteViewModel::class.java]

        val datePicker = binding.datePicker
        var date:LocalDate? = null
        datePicker.init(2020, 2, 1)
        { view, year, monthOfYear, dayOfMonth ->
            // Отсчет месяцев начинается с нуля. Для отображения добавляем 1.
            date = LocalDate.of(view.year,view.month+1,view.dayOfMonth)
            //date = "${view.dayOfMonth}-${view.month}-${view.year}"
            // альтернативная запись
            // dateTextView.setText("Дата: " + dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
        }

        val timePickerStart = binding.timePickerStart
        timePickerStart.setIs24HourView(true)
        var timeStart: LocalTime? = null
        val timePickerFinish = binding.timePickerFinish
        timePickerFinish.setIs24HourView(true)
        var timeFinish:LocalTime? = null

        timePickerStart.setOnTimeChangedListener { view, hourOfDay, minute ->
            //timeTextView.setText("Время: $hourOfDay:$minute")
            //timeStart = "$hourOfDay:$minute"
            timeStart = LocalTime.of(hourOfDay,minute)
            // или так
            // timeTextView.setText("Время: " + view.getHour() + ":" + view.getMinute());
        }
        timePickerFinish.setOnTimeChangedListener { view, hourOfDay, minute ->
            //timeTextView.setText("Время: $hourOfDay:$minute")
            //timeFinish = "$hourOfDay:$minute"
            timeFinish = LocalTime.of(hourOfDay,minute)
            // или так
            // timeTextView.setText("Время: " + view.getHour() + ":" + view.getMinute());
        }

        val button = binding.saveButton
        button.setOnClickListener {
            /*
            serviceLayer!!.addNote(name.text.toString(), description.text.toString(),Timestamp.valueOf(
                LocalDateTime.of(date,timeStart).toString()).time.toString(),Timestamp.valueOf(
                LocalDateTime.of(date,timeFinish).toString()).time.toString())


             */
            date = LocalDate.of(datePicker.year,datePicker.month+1,datePicker.dayOfMonth)
            timeStart = LocalTime.of(timePickerStart.hour,timePickerStart.minute,0)
            timeFinish = LocalTime.of(timePickerFinish.hour,timePickerFinish.minute,0)
            //val a = Timestamp.valueOf(LocalDateTime.of(date,timeStart).toString()).time.toString()
            //val a = Timestamp.valueOf(LocalDateTime.of(date,timeStart).format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")))


            viewModel.addNote(binding.titleEditText.text.toString(),binding.descriptionEditText.text.toString(), Timestamp.valueOf(
                LocalDateTime.of(date,timeStart).format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))).time,Timestamp.valueOf(
                LocalDateTime.of(date,timeFinish).format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))).time)

            /*
            intent.putExtra("dataNote",DataNote("1",Timestamp.valueOf(
                LocalDateTime.of(date,timeStart).format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))).time,
                Timestamp.valueOf(
                    LocalDateTime.of(date,timeFinish).format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))).time,
                name.text.toString(),
                description.text.toString())
            )
             */


            view?.findNavController()?.navigate(AddNoteFragmentDirections.actionAddNoteFragmentToNoteFragment2(
                Calendar.getInstance()))

        }

        return binding.root
    }

}
