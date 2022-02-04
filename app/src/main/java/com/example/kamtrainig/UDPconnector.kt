package com.example.kamtrainig

import android.provider.Settings
import java.net.DatagramSocket

class UDPconnector {

    fun receiveUDP( size: Int): ByteArray {
        val ret = ByteArray(size)
        var socket: DatagramSocket? = null
        try {
            socket = DatagramSocket(2000, InetAddress.getByName(Settings.RemoteHost))
            socket.broadcast = true
            val Buffer = ByteArray(1500)
            val packet = DatagramPacket(Buffer, Buffer.size)
            socket.receive(packet)
            System.arraycopy(decode(packet), 0, ret, 0, size)

        } catch (e: Exception) {
            e.printStackTrace()
        } finally {
            socket?.close()
        }
        return ret
    }


    fun decode( size: DatagramPacket): ByteArray {
        val ret = ByteArray(size)
        return ret
    }
}