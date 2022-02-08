package com.example.kamtrainig

import java.util.LinkedHashSet

object TriageModel {

    val sim_list: LinkedHashSet<Int> = LinkedHashSet()

    var green : Int = 0;
    var orange: Int = 0;
    var red   : Int = 0;
    var black : Int = 0;

    fun reset(){
        green = 0;
        orange = 0;
        red = 0;
        black = 0;
    }

    fun incrementGreen(){green++}
    fun incrementOrange(){orange++}
    fun incrementRed(){red++}
    fun incrementBlack(){black++}



}