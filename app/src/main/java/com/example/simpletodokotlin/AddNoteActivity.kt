package com.example.simpletodokotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton

class AddNoteActivity : AppCompatActivity() {
    private lateinit var editTextAddNote: EditText
    private lateinit var lowPriorityRB: RadioButton
    private lateinit var medPriorityRB: RadioButton
    private lateinit var button: Button
    private lateinit var db: NotesDataBase
    private var handler:Handler = Handler(Looper.getMainLooper())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_note)
       db= NotesDataBase.getInstance(application)
        initViews()
        button.setOnClickListener(View.OnClickListener {
            saveNote()
            finish()
        })


    }

    private fun initViews(){
        editTextAddNote = findViewById(R.id.editTextAddNote)
        lowPriorityRB = findViewById(R.id.lowPriorityRB)
        medPriorityRB = findViewById(R.id.medPriorityRB)
        button = findViewById(R.id.buttonAdd)
    }

    private fun saveNote(){
       var text: String = editTextAddNote.text.toString().trim()
       var priority: Int =  getPriority()

        var thread : Thread = Thread(Runnable {
            db.notesDAO().addNote(Note(0,text,priority))
            handler.post(Runnable {
                finish()
            })

        })
        thread.start()


    }

    private fun getPriority(): Int{
        if (lowPriorityRB.isChecked){
            return 0
        } else if (medPriorityRB.isChecked){
            return 1
        } else return 2
    }
}