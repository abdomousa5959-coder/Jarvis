package com.openjarvis.bridge

import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Test

class SocketServerTest {
    @Test
    fun requestJsonCanBeParsed() {
        val request = SocketProtocol.parseRequest("""{"cmd":"open chrome","requestId":"abc123"}""")
        assertEquals("abc123" to "open chrome", request)
    }

    @Test
    fun missingCommandIsRejectedByContract() {
        val request = SocketProtocol.parseRequest("""{"requestId":"abc123"}""")
        assertNull(request)
    }

    @Test
    fun malformedJsonIsRejected() {
        assertNull(SocketProtocol.parseRequest("""{"cmd":"""))
    }
}
