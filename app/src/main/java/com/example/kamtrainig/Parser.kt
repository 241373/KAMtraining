package com.example.kamtrainig

import java.net.DatagramPacket

class Parser {


    fun check_type( datagram: DatagramPacket): String {
        var data = datagram.data;
        var text = String(data)
        if (text.startsWith("victim"))
            return "victim"
        if (text.startsWith("trianee"))
            return "trianee"
        if (text.startsWith("commander"))
            return "commander"
        var values = text.split(',')
        if(values.size == 12)
            return "triage"
        return "none"
    }

    fun decodeTriage( datagram: DatagramPacket): List<String> {
        var data = datagram.data;
        var text = String(data)
        var values = text.split(',')

        return values
    }
    fun decodeVictim( datagram: DatagramPacket): List<String> {
        var data = datagram.data;
        var text = String(data)
        var values = text.split(',')

        return values
    }
    fun decodeTrainee( datagram: DatagramPacket): List<String> {
        var data = datagram.data;
        var text = String(data)
        var values = text.split(',')

        return values
    }

}