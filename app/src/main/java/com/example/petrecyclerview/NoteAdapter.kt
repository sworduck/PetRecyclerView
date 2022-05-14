package com.example.petrecyclerview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.recyclerview.widget.RecyclerView

class NoteAdapter(private val notes: List<Note>,val viewModel: NoteViewModel):RecyclerView.Adapter<NoteAdapter.ViewHolder>() {
    private val nowTime: List<String> = listOf("1:00","2:00","3:00","4:00","5:00","6:00","7:00","8:00"
        ,"9:00","10:00","11:00","12:00","13:00","14:00","15:00","16:00","17:00","18:00","19:00","20:00"
        ,"21:00","22:00","23:00","00:00")

    interface OnNoteClickListener {
        fun onNoteClick(note: Note)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.note_item, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.noteTime.text = nowTime[position]
        holder.noteDescription.text = notes[position].description
        holder.noteDescription.setOnClickListener {
            //viewModel.functionInsideAdapter()
            viewModel.onClickListener.onNoteClick(notes[position])
        }
    }

    override fun getItemCount(): Int {
        return notes.count()
    }
    class ViewHolder(itemView:View):RecyclerView.ViewHolder(itemView){
        val noteTime:Button = itemView.findViewById(R.id.time)
        val noteDescription: Button = itemView.findViewById(R.id.description)
    }
}