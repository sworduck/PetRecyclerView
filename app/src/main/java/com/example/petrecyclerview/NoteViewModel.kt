package com.example.petrecyclerview

import android.content.Intent
import android.util.Log
import androidx.lifecycle.ViewModel

class NoteViewModel : ViewModel() {

    lateinit var onClickListener: NoteAdapter.OnNoteClickListener

    private var wordList: MutableList<String> = mutableListOf()

    private var noteList: MutableList<Note> = mutableListOf()

    private lateinit var noteAdapter:NoteAdapter

    init {
        resetList()
    }

    fun initOnClickListener(listner: NoteAdapter.OnNoteClickListener){
        onClickListener = listner
    }

    fun getAdapter():NoteAdapter{
        noteAdapter = NoteAdapter(noteList,this)
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
        wordList.shuffle()
        for(i in 0..23){
            noteList.add(i,Note(i,"$i",wordList[i]))
        }
    }
}