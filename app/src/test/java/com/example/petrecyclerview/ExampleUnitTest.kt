package com.example.petrecyclerview

import org.junit.Assert.*
import org.junit.Test
import java.sql.Timestamp
import java.time.Instant
import java.time.LocalDateTime
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

        println(a.time)
        println(triggerTime)

        assertEquals(4, 2 + 2)
    }
}