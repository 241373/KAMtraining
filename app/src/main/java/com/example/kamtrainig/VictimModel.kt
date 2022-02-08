package com.example.kamtrainig

import kotlin.properties.Delegates

class VictimModel {

    val id: Int;
    val cat: String;

    constructor(id: Int, cat: String){
        this.id = id
        this.cat = cat
    }
}