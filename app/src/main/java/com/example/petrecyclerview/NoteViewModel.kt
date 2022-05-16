package com.example.petrecyclerview

import android.content.Intent
import android.util.Log
import androidx.lifecycle.ViewModel
import java.sql.Timestamp

class NoteViewModel : ViewModel() {

    private var wordList: MutableList<String> = mutableListOf()
    private var timeStampList:MutableList<String> = mutableListOf()

    private var noteList: MutableList<Note> = mutableListOf()

    private lateinit var noteAdapter:NoteAdapter

    init {
        resetList()
    }

    fun getAdapter(onClickListener: NoteAdapter.OnNoteClickListener):NoteAdapter{
        noteAdapter = NoteAdapter(noteList,onClickListener)
        return noteAdapter
    }

    fun roflFunction(){
        wordList.shuffle()
        for (i in 0 until wordList.count()) {
            noteAdapter!!.notifyItemChanged(i)
        }
    }

    fun functionInsideAdapter(){
        Log.i("TAG","functionInsideAdapter is called")
    }

    // TODO: Implement the ViewModel
    private fun resetList() {
        wordList = mutableListOf(
            "queen",
            "hospital",
            "basketball",
            "cat",
            "change",
            "snail",
            "soup",
            "calendar",
            "sad",
            "desk",
            "guitar",
            "home",
            "railway",
            "zebra",
            "jelly",
            "car",
            "crow",
            "trade",
            "bag",
            "roll",
            "bubble",
            "bag",
            "roll",
            "bubble"
        )
        timeStampList = mutableListOf(
            "1652213002",
            "1652216602",
            "1652220202",
            "1652223802",
            "1652227402",
            "1652231002",
            "1652234602",
            "1652238202",
            "1652241802",
            "1652245402",
            "1652249002",
            "1652252602",
            "1652256202",
            "1652259802",
            "1652263402",
            "1652267002",
            "1652270602",
            "1652274202",
            "1652277802",
            "1652281402",
            "1652285002",
            "1652288602",
            "1652292202",
            "1652295802"
        )
        wordList.shuffle()
        for(i in 0..23){
            noteList.add(i,Note(i,"$i",wordList[i],timeStampList[i],timeStampList[i]))
        }
    }
}