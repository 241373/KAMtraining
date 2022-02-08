package com.example.kamtrainig

import java.util.*

object TriageModel: Observable(){

//    val sim_list: LinkedHashSet<Int> = LinkedHashSet()
    val sim_list: TreeSet<Int> = TreeSet()

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

    fun monitorVictim(){}
    fun categorizeVictim(victim: VictimModel){
        if(victim.cat == "green")
            green++
        if(victim.cat == "orange")
            orange++
        if(victim.cat == "red")
            red++
        if(victim.cat == "black")
            black++

    }



}