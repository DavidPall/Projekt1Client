package com.example.projekt1client.Presenter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import java.util.ArrayList
import com.example.projekt1client.Model.selectedPosition
import com.example.projekt1client.R


class VoteGridadapter(private val mDataList: ArrayList<String>) : RecyclerView.Adapter<VoteGridadapter.MyViewHolder>() {

    var selected_position = 0

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.recyclerview_item, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.info_text.text = mDataList[position]

        if (selectedPosition === position)
            holder.itemView.setBackgroundColor(Color.parseColor("#000000"))
        else
            holder.itemView.setBackgroundColor(Color.parseColor("#ffffff"))

        holder.itemView.setOnClickListener {
            selectedPosition = position
            notifyDataSetChanged()
        }


    }



    override fun getItemCount(): Int {
        return mDataList.size
    }

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        internal var info_text: TextView

        init {
            info_text = itemView.findViewById<View>(R.id.info_text) as TextView

        }


    }
}