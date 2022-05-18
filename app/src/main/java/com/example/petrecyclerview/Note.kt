package com.example.petrecyclerview

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import java.io.Serializable
import java.sql.Timestamp

open class Note():Serializable, RealmObject(){
    constructor(_id:Int,_name:String,_description:String):this(){
        id = _id
        name = _name
        description = _description
    }

    constructor(_id:Int,_name:String,_description:String,_timeStart:Long,_timeFinish:Long):this(){
        id = _id
        name = _name
        description = _description
        date_start = _timeStart
        date_finish = _timeFinish
    }
    @PrimaryKey
    var id = 0

    var name = "0"
    var description = "0"
    var date_start = 0L
    var date_finish = 0L
}