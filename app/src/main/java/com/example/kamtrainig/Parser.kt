package com.example.kamtrainig

import java.net.DatagramPacket

class Parser {

    fun decode( datagram: DatagramPacket): ByteArray {
        val ret = datagram.data;
//        val ret = ByteArray(datagram.length)
        return ret
    }

}