package com.example.projekt1client

import android.content.Context
import android.content.Intent
import android.graphics.drawable.ClipDrawable.HORIZONTAL
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.GridLayout
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.firebasetest.currentQuestionID
import com.example.firebasetest.roomNumber
import com.example.firebasetest.userID
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_vote.view.*

/**
 * Created by VickY on 07-09-2017.
 */
class FragmentVote : Fragment(){

    val TAG = "FragmentLogin"

    private var mRecyclerView: RecyclerView? = null
    private var mAdapter: RecyclerView.Adapter<*>? = null
    private var currentVote: String? = null

    override fun onAttach(context: Context) {
        Log.d(TAG,"onAttach")
        super.onAttach(context)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d(TAG,"onCreate")
        super.onCreate(savedInstanceState)




    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        Log.d(TAG,"onCreateView")

        val rootView = inflater.inflate(R.layout.fragment_vote,container,false)

        mRecyclerView = rootView.findViewById(R.id.rvNumbers) as RecyclerView
        mRecyclerView!!.layoutManager = GridLayoutManager(this.context,3)

        var optionsList : ArrayList<String> = ArrayList()


        rootView.btn_vote.setOnClickListener(){
            //Toast.makeText(this@FragmentOne.context,"asdasd",Toast.LENGTH_SHORT)
            var newFragment : Fragment = FragmentAnswers()
            var transaction : FragmentTransaction = fragmentManager!!.beginTransaction()
            transaction.replace(R.id.fragment_holder,newFragment)
            transaction.addToBackStack(null)
            transaction.commit()
        }


        //HARDCODE
        currentQuestionID = "who is this?"
        //HARDCODE
        rootView.txt_question.setText("Current question: "+currentQuestionID)


        for(i in 1..10){
            optionsList.add(i.toString())
        }
        optionsList.add("Coffee")
        optionsList.add("Skip")

        mAdapter = VoteGridadapter(optionsList)
        mRecyclerView!!.adapter = mAdapter

        mRecyclerView!!.addOnItemClickListener(object : OnItemClickListener {
            override fun onItemClicked(position: Int, view: View) {
                currentVote = optionsList.get(position)

                addVoteData(currentVote.toString())
                //Toast.makeText(context, "clicked on " + currentVote, Toast.LENGTH_SHORT).show()
                rootView.btn_vote.setText("Vote:    " + currentVote.toString())

            }
        })

        return rootView
    }

    fun addVoteData(voteString: String){
        val database = FirebaseDatabase.getInstance()
        val userReference = database.reference

        userReference.child("Groups").child(roomNumber).child(currentQuestionID).child("Votes").child(userID).child("userID").setValue(userID)
        userReference.child("Groups").child(roomNumber).child(currentQuestionID).child("Votes").child(userID).child("Vote").setValue(currentVote)
    }

    interface OnItemClickListener {
        fun onItemClicked(position: Int, view: View)
    }

    fun RecyclerView.addOnItemClickListener(onClickListener: OnItemClickListener) {
        this.addOnChildAttachStateChangeListener(object : RecyclerView.OnChildAttachStateChangeListener {
            override fun onChildViewDetachedFromWindow(view: View) {
                view.setOnClickListener(null)

            }

            override fun onChildViewAttachedToWindow(view: View) {
                view.setOnClickListener {
                    val holder = getChildViewHolder(view)
                    onClickListener.onItemClicked(holder.adapterPosition, view)
                }
            }
        })
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        Log.d(TAG,"onActivityCreated")
        super.onActivityCreated(savedInstanceState)
    }

    override fun onStart() {
        Log.d(TAG,"onStart")
        super.onStart()
    }

    override fun onResume() {
        Log.d(TAG,"onResume")
        super.onResume()
    }

    override fun onPause() {
        Log.d(TAG,"onPause")
        super.onPause()
    }

    override fun onStop() {
        Log.d(TAG,"onStop")
        super.onStop()
    }

    override fun onDestroyView() {
        Log.d(TAG,"onDestroyView")
        super.onDestroyView()
    }

    override fun onDestroy() {
        Log.d(TAG,"onDestroy")
        super.onDestroy()
    }

    override fun onDetach() {
        Log.d(TAG,"onDetach")
        super.onDetach()
    }
}