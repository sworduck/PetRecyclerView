package com.example.petrecyclerview

import androidx.lifecycle.ViewModel
import java.time.Instant
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*

class SingleNoteViewModel : ViewModel() {
    fun noteToStringForThisFragment(n:Note):String{
        return if(n.name != "0") {
            val ldt: LocalDateTime = LocalDateTime.ofInstant(
                Instant.ofEpochSecond(n.date_start.toLong()),
                TimeZone.getDefault().toZoneId()
            )
            "Название: ${n.name};\nОписание: ${n.description};\nВремя: ${ldt.format(
                DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm"))}"
        } else{
            "-"
        }
    }
    /*
            LocalDateTime now = LocalDateTime.now();
            int year = now.getYear();
            int month = now.getMonthValue();
            int day = now.getDayOfMonth();
            int hour = now.getHour();
            int minute = now.getMinute();
            int second = now.getSecond();
            int millis = now.get(ChronoField.MILLI_OF_SECOND); // Note: no direct getter available.
             */
}