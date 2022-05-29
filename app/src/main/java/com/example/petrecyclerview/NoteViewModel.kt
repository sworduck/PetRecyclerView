package com.example.petrecyclerview

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.DiffUtil
import com.applandeo.materialcalendarview.EventDay
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.JsonElement
import com.google.gson.JsonParser.parseReader
import com.google.gson.reflect.TypeToken
import io.realm.Realm
import io.realm.RealmConfiguration
import java.io.IOException
import java.io.InputStream
import java.io.InputStreamReader
import java.sql.Timestamp
import java.time.Instant
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*


class NoteViewModel : ViewModel() {
    val calendar = MutableLiveData<Calendar>()
    var calendar2 = Calendar.getInstance()

    private var noteList: MutableList<Note> = mutableListOf()

    private var noteList2: MutableList<Note> = mutableListOf()

    private var noteAdapter:NoteAdapter? = null

    private lateinit var realm: Realm

    private val defaultNote = Note(1111,"0","0")

    init {
        //пробую менять календарь
        if(calendar.value==null)
        calendar.value = Calendar.getInstance()


        //noteAdapter = NoteAdapter(noteList,  )
        //resetList()
        resetNoteList()
        Log.i("TAG", "create viewModel")
    }

    override fun onCleared() {
        Log.i("TAG", "clear viewModel")
        super.onCleared()
    }
    private fun resetNoteList(){
        noteList.clear()
        for (i in 0..23){
            noteList.add(i,defaultNote)
        }
    }

    fun initRealm(context: Context){
        //noteList2 = loadNotes(context)!!
        Realm.init(context)
        Realm.setDefaultConfiguration(
            RealmConfiguration.Builder()
                .allowWritesOnUiThread(true)
                .allowQueriesOnUiThread(true)
                .deleteRealmIfMigrationNeeded()
                .build()
        )
        realm = Realm.getDefaultInstance()
        val list = realm.where(Note::class.java).findAll()
        val a = 1+1
        /*
        realm.executeTransaction {
            it.insertOrUpdate(noteList2)
        }

         */
    }

    private fun loadNotes(context: Context): MutableList<Note>? {
        // In this case we're loading from local assets.
        // NOTE: could alternatively easily load from network.
        // However, that would need to happen on a background thread.
        val stream: InputStream = try {
            context.assets.open("notelist.json")
        } catch (e: IOException) {
            return null
        }
        val gson: Gson = GsonBuilder().create()
        val json: JsonElement = parseReader(InputStreamReader(stream))
        return gson.fromJson(json, object : TypeToken<MutableList<Note>?>() {}.type)
    }

    fun eventOfDay(eventDay: EventDay?) {
        //изменяю календарь
        calendar2 =eventDay!!.calendar
        //изменяю календарь
        val ldt = LocalDateTime.ofInstant(eventDay!!.calendar.toInstant(),eventDay!!.calendar.timeZone.toZoneId())
        var noteListRealm = realm.where(Note::class.java).between("date_start",eventDay.calendar.timeInMillis/1000,eventDay.calendar.timeInMillis/1000+86400).findAll()
        //resetNoteList()
        var newList:MutableList<Note> = mutableListOf()
        for (i in 0..23){
            newList.add(i,defaultNote)
        }
        for(i in 0 until noteListRealm.count()){
            newList.removeAt(Timestamp(noteListRealm[i]!!.date_start*1000).hours)
            newList.add(Timestamp(noteListRealm[i]!!.date_start*1000).hours,noteListRealm[i]!!)
        }

        /*
        val noteDiffUtilCallback = NoteDiffUtilCallback(noteAdapter!!.getData(), newList)
        val noteDiffResult = DiffUtil.calculateDiff(noteDiffUtilCallback)
        noteAdapter!!.setData(newList)
        noteDiffResult.dispatchUpdatesTo(noteAdapter!!)//вообще не вызывает нотифай

         */
        noteAdapter!!.setData(newList)
        noteAdapter!!.notifyDataSetChanged()

    }

    fun getAdapter(onClickListener: NoteAdapter.OnNoteClickListener):NoteAdapter?{

        //noteList.add(0,Note(21125,"name","description"))

        noteAdapter = NoteAdapter(noteList,onClickListener,this)
        return noteAdapter
    }

    fun holderNoteDescriptionText(note:Note):String{
        return if(note.description != "0") {
            "${note.name} ${
                Instant.ofEpochMilli(note.date_start * 1000).atZone(
                    TimeZone.getDefault().toZoneId()
                ).toLocalDateTime().format(DateTimeFormatter.ofPattern("HH:mm"))
            }"
        } else{
            "-"
        }
    }
    fun holderNoteDescriptionSetOnClickListenerLine(note: Note):String{
        return if(note.description != "0") {
            val ldt: LocalDateTime = LocalDateTime.ofInstant(
                Instant.ofEpochSecond(note.date_start.toLong()),
                TimeZone.getDefault().toZoneId()
            )
            "Название: ${note.name};\nОписание: ${note.description};\nВремя: ${ldt.format(
                DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm"))}"
        } else{
            "-"
        }
    }
    fun holderNoteDescriptionSetOnClickListenerCalendar(note: Note):Calendar{
        return if(note.description != "0") {
            val calendar: Calendar = Calendar.getInstance()
            calendar.timeInMillis = note.date_start * 1000
            calendar
        } else{
            calendar2
        }
    }

}