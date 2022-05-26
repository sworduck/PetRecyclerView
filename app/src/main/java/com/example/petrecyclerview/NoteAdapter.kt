package com.example.petrecyclerview

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import java.time.Instant
import java.time.format.DateTimeFormatter
import java.util.*

class NoteAdapter(private var notes: List<Note>, private val onClickListener: NoteAdapter.OnNoteClickListener):RecyclerView.Adapter<NoteAdapter.ViewHolder>() {
    private val nowTime: List<String> = listOf("1:00","2:00","3:00","4:00","5:00","6:00","7:00","8:00"
        ,"9:00","10:00","11:00","12:00","13:00","14:00","15:00","16:00","17:00","18:00","19:00","20:00"
        ,"21:00","22:00","23:00","00:00")
    private val defaultNote = Note(1111,"0","0")

    interface OnNoteClickListener {
        fun onNoteClick(note: Note)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.note_item, parent, false)
        return ViewHolder(itemView)
    }


    //никакой логики тут!!!
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.noteTime.text = nowTime[position]
                if(notes[position].description != "0") {
                    holder.noteDescription.text = "${notes[position].description} ${
                        Instant.ofEpochMilli(notes[position].date_start * 1000).atZone(
                            TimeZone.getDefault().toZoneId()
                        ).toLocalDateTime().format(DateTimeFormatter.ofPattern("HH:mm"))
                    }"
                    holder.noteDescription.setOnClickListener {
                        //viewModel.functionInsideAdapter()
                        onClickListener.onNoteClick(notes[position])
                    }
                }
                else {
                    holder.noteDescription.text = "-"
                    holder.noteDescription.setOnClickListener {
                        //viewModel.functionInsideAdapter()
                        onClickListener.onNoteClick(notes[position])
                    }
                }

    }

    override fun getItemCount(): Int {
        return 24
    }

    fun getData(): List<Note> {
        return notes
    }

    fun setData(noteList: List<Note>){
        notes = noteList
    }

    class ViewHolder(itemView:View):RecyclerView.ViewHolder(itemView){
        val noteTime:Button = itemView.findViewById(R.id.time)
        val noteDescription: Button = itemView.findViewById(R.id.description)
    }
}