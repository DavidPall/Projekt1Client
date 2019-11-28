package com.example.projekt1client

import android.content.IntentSender
import android.os.Bundle
import android.os.CountDownTimer
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.RecyclerView
import com.example.firebasetest.roomNumber
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import java.util.concurrent.TimeUnit

class ResultAdapter(var userList:ArrayList<User>) : RecyclerView.Adapter<ResultAdapter.ViewHolder>() {

    private lateinit var database: DatabaseReference


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.result_list, parent, false)



        return ViewHolder(v)
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val currentUser : User = userList[position]

        holder.user.setText("User: " + currentUser.userID)
        holder.vote.setText("Voted: " + currentUser.Vote)

    }



    override fun getItemCount(): Int {
        return userList.size
    }


    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        internal var user: TextView
        internal var vote : TextView

        init {
            user = itemView.findViewById<View>(R.id.name_text) as TextView
            vote = itemView.findViewById<View>(R.id.vote_text) as TextView

        }


    }
}