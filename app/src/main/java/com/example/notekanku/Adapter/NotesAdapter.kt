package com.example.notekanku.Adapter

import android.content.Context
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.notekanku.R
import com.example.notekanku.model.Note
import kotlin.random.Random

class NotesAdapter(private val context: Context, val listener: noteitemClickListener) : RecyclerView.Adapter<NotesAdapter.NoteViewHolder>() {

    private val Noteslist = ArrayList<Note>()
    private val fullList = ArrayList<Note>()



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        return NoteViewHolder(
            LayoutInflater.from(context).inflate(R.layout.list_item,parent,false)
        )
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        val currentNote = Noteslist[position]
        holder.title.text = currentNote.title
        holder.title.isSelected = true
        holder.Note_tv.text = currentNote.note
        holder.date.text = currentNote.date
        holder.date.isSelected = true

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            holder.notes_layout.setCardBackgroundColor(holder.itemView.resources.getColor(randomColor(),null))
        }

        holder.notes_layout.setOnClickListener{

            listener.onItemclicked(Noteslist[holder.adapterPosition])

        }

        holder.notes_layout.setOnLongClickListener {

            listener.onLongItemClicked(Noteslist[holder.adapterPosition],holder.notes_layout)
            true

        }

    }

    override fun getItemCount(): Int {
        return Noteslist.size
    }

    fun updateList(newList : List<Note>){

        fullList.clear()
        fullList.addAll(newList)

        Noteslist.clear()
        Noteslist.addAll(fullList)
        notifyDataSetChanged()

    }

    fun filterList(search : String){

        Noteslist.clear()

        for(item in fullList){

            if(item.title?.lowercase()?.contains(search.lowercase()) == true ||
                    item.note?.lowercase()?.contains(search.lowercase()) == true){

                Noteslist.add(item)

            }
        }

        notifyDataSetChanged()

    }


    fun randomColor() : Int{

        val list = ArrayList<Int>()
        list.add(R.color.white)

        val seed = System.currentTimeMillis().toInt()
        val randomIndex = Random(seed).nextInt(list.size)
        return list[randomIndex]

    }


    inner class NoteViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){

        val notes_layout = itemView.findViewById<CardView>(R.id.card_layout)
        val title = itemView.findViewById<TextView>(R.id.tv_title)
        val Note_tv = itemView.findViewById<TextView>(R.id.tv_note)
        val date = itemView.findViewById<TextView>(R.id.tv_date)

    }

    interface noteitemClickListener{

        fun onItemclicked(note:Note)
        fun onLongItemClicked(note:Note,cardView: CardView)

    }

}