package com.example.petrecyclerview

import java.io.Serializable
import java.sql.Timestamp

class Note(id:Int,name:String,description:String):Serializable{
    constructor(id:Int,name:String,description:String,timeStartс:String,timeFinishс:String):this(id,name,description){
        timeStart = timeStartс
        timeFinish = timeFinishс
    }
    var description = description
    var timeStart = ""
    var timeFinish = ""
}