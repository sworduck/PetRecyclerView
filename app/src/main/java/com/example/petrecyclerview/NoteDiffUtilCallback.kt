package com.example.petrecyclerview

import androidx.recyclerview.widget.DiffUtil

class NoteDiffUtilCallback(_oldList: List<Note>,_newList:List<Note>): DiffUtil.Callback() {
    private var oldList:List<Note> = _oldList
    private var newList:List<Note> = _newList

    override fun getOldListSize(): Int {
        return oldList.size
    }

    override fun getNewListSize(): Int {
        return newList.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].id == newList[newItemPosition].id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return (oldList[oldItemPosition].name == newList[newItemPosition].name)&&
                (oldList[oldItemPosition].description == newList[newItemPosition].description)
    }
}