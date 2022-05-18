package com.example.petrecyclerview

import androidx.lifecycle.ViewModel
import java.time.Instant
import java.time.LocalDateTime
import java.util.*

class SingleNoteViewModel : ViewModel() {
    fun noteToStringForThisFragment(n:Note):String{
        return if(n.name != "0") {
            val ldt: LocalDateTime = LocalDateTime.ofInstant(
                Instant.ofEpochSecond(n.date_start.toLong()),
                TimeZone.getDefault().toZoneId()
            )
            "Описание: ${n.description} время: ${
                String.format(
                    "%02d",
                    ldt.hour
                )
            }:${String.format("%02d", ldt.minute)} --- ${ldt.toString()}"
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