package com.petamind.example.jettrivia.util

import android.content.Context
import androidx.test.platform.app.InstrumentationRegistry
import org.junit.Assert.*

import org.junit.Before
import org.junit.Test

class NetworkHelperTest {

    lateinit var appContext: Context

    @Before
    fun setUp() {
        appContext = InstrumentationRegistry.getInstrumentation().targetContext

    }

    @Test
    fun networkAvailable() {
        assertEquals(false, NetworkHelper.networkAvailable(appContext))
    }
}