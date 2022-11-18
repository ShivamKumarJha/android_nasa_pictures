package com.shivamkumarjha.nasagallery

import junit.framework.Assert.assertEquals
import org.junit.Test
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class DateTest {

    private fun getLocalDate(date: String): LocalDate? {
        return LocalDate.parse(date, DateTimeFormatter.ISO_DATE)
    }

    @Test
    fun `date parsing`() {
        assertEquals(getLocalDate("2019-12-31"), LocalDate.of(2019, 12, 31))
        assertEquals(getLocalDate("2019-12-31+05:30"), LocalDate.of(2019, 12, 31))
    }
}