package com.example.projekt1client.Presenter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.projekt1client.Model.User
import com.example.projekt1client.R


class ResultAdapter(var userList:ArrayList<User>) : RecyclerView.Adapter<ResultAdapter.ViewHolder>() {



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