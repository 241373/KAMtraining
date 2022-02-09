package com.example.kamtrainig

//import android.provider.Settings
import java.net.DatagramSocket
import java.net.InetAddress
import java.net.DatagramPacket



object UDPconnector: Runnable{

    object Settings {
        var myAddress: String = "0.0.0.0";
        var myPort: Int = 5151;
        var MaxDataSize: Int = 1000;
    }

    // Global
//    val Settings = MyOptions()

    override fun run() {
        var socket: DatagramSocket? = null
        try {
            socket = DatagramSocket(Settings.myPort, InetAddress.getByName(Settings.myAddress))
            socket.broadcast = true
            val buffer = ByteArray(Settings.MaxDataSize)
            val packet = DatagramPacket(buffer, buffer.size)

            while(true){
                socket.receive(packet)
                managePacket(packet)
            }

        } catch (e: Exception) {
            e.printStackTrace()
        } finally {
            socket?.close()
        }
    }


    fun managePacket(packet: DatagramPacket) {

        val msg_type = Parser.check_type(packet)
        if (msg_type=="trianee") {
            val data = Parser.decodeTrainee(packet)
            var victim_id = data[2].toInt()
            TriageModel.sim_list.add(victim_id)
        }
        if (msg_type=="victim") {
            val data = Parser.decodeVictim(packet)
            var victim_id = data[1].toInt()
            TriageModel.monitorVictim()
        }
        if (msg_type=="traige") {
            val data = Parser.decodeTriage(packet)
            var victim_id = data[3].toInt()
            var victim_cat = data[1]
            val victim = VictimModel(victim_id, victim_cat)
            TriageModel.categorizeVictim(victim)
            TriageModel.sim_list.add(victim_id)
        }
    }
}