package com.example.petrecyclerview

import org.junit.Assert.assertEquals
import org.junit.Test
import java.sql.Timestamp
import java.time.Instant
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        val a = Timestamp(1652295802000)
        val triggerTime: LocalDateTime = LocalDateTime.ofInstant(
            Instant.ofEpochMilli(a.time),
            TimeZone.getDefault().toZoneId()
        )
        val formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm")
        formatter.format(triggerTime)

        //новое
        var time = triggerTime.format(DateTimeFormatter.ofPattern("HH:mm"))

        //новое новое
        var time2 = Instant.ofEpochMilli(1652295802000).atZone(TimeZone.getDefault().toZoneId()).toLocalDateTime().format(DateTimeFormatter.ofPattern("HH:mm"))
        var num1 = "10001"
        var num2 = "10000"
        if (num1>num2)
            println("num1 больше чем num2")

        //val ldt:LocalDateTime = LocalDateTime.ofInstant(Calendar.toInstant(), Calendar);

        println(a.time)
        println(triggerTime)
        println(time)
        println(time2)

        assertEquals(4, 2 + 2)
    }
}