package com.example.simpletodokotlin

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView

class NotesAdapter : RecyclerView.Adapter<NotesAdapter.NotesViewHolder>() {

    fun updateNotes (notes: List<Note>) {
        this.notes = notes as ArrayList<Note>
        notifyDataSetChanged()
    }

    fun getNotes(noteID: Int): Note{
        return notes[noteID]
    }

    private var notes = ArrayList<Note>()
    // how to create View from layout
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotesViewHolder {
        //layout
        //where to put layout (viewgroup)
        var view : View = LayoutInflater.from(parent.context).inflate(
            R.layout.note_item,
            parent,
            false
        )
        return NotesViewHolder(view)
    }
//which properties to set in view form layout(which we get from method above)
    override fun onBindViewHolder(holder: NotesViewHolder, position: Int) {


for (note in notes) {
    var textViewNote: TextView = holder.itemView.findViewById(R.id.textViewNote)
    textViewNote.setText(notes[position].text)


    var colorId:Int
    when(notes[position].priority){
        0 -> colorId= android.R.color.holo_green_light
        1 -> colorId = android.R.color.holo_orange_light
        else ->{
            colorId =  android.R.color.holo_red_light
        }
    }
    var color:Int = ContextCompat.getColor(holder.itemView.context, colorId)
    textViewNote.setTextColor(color)
}

    }
// q-ty of elements
    override fun getItemCount(): Int {
        return notes.size
    }

   inner class NotesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}