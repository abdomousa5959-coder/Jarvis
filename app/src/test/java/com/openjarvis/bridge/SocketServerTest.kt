package com.openjarvis.bridge

import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Test
import org.json.JSONObject

class SocketServerTest {
    @Test
    fun requestJsonCanBeParsed() {
        val request = JSONObject("""{"cmd":"open chrome","requestId":"abc123"}""")
        assertEquals("open chrome", request.getString("cmd"))
        assertEquals("abc123", request.getString("requestId"))
    }

    @Test
    fun missingCommandIsRejectedByContract() {
        val request = JSONObject("""{"requestId":"abc123"}""")
        assertNull(request.optString("cmd", null))
    }
}
