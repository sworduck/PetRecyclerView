package com.example.petrecyclerview

import androidx.lifecycle.ViewModel
import io.realm.Realm
import java.util.*

class AddNoteViewModel : ViewModel() {
    private val realm = Realm.getDefaultInstance()
    fun addNote(
        noteTitle: String,
        noteDescription: String,
        startDate: Long,
        finishDate: Long
    ) {
        val newnote:Note? = null

        realm!!.executeTransaction { r: Realm ->
            val newnote = r.createObject(Note::class.java, UUID.randomUUID().hashCode())
            newnote.name = noteTitle
            newnote.description = noteDescription
            newnote.date_start = startDate/1000
            newnote.date_finish = finishDate/1000
            realm!!.insertOrUpdate(newnote!!)
        }
        //note!!.add(newnote)
        //getTimeListAllNote()
    }
}