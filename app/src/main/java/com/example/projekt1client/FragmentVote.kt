package com.example.projekt1client

import android.content.Context
import android.content.Intent
import android.graphics.drawable.ClipDrawable.HORIZONTAL
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.GridLayout
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.firebasetest.currentQuestionID
import com.example.firebasetest.roomNumber
import com.example.firebasetest.userID
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_vote.view.*
import java.util.concurrent.TimeUnit

/**
 * Created by VickY on 07-09-2017.
 */
class FragmentVote : Fragment(){

    val TAG = "FragmentLogin"

    private var mRecyclerView: RecyclerView? = null
    private var mAdapter: RecyclerView.Adapter<*>? = null
    private var currentVote: String? = "Didn't vote"

    private lateinit var txtTimer : TextView

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

        txtTimer = rootView.findViewById(R.id.timer)

        rootView.btn_vote.setOnClickListener(){
            if(currentQuestionID != "Waiting for the question..." && currentVote != "Didn't vote") {
                //Toast.makeText(this@FragmentOne.context,"asdasd",Toast.LENGTH_SHORT)
                var newFragment: Fragment = FragmentAnswers()
                var transaction: FragmentTransaction = fragmentManager!!.beginTransaction()
                transaction.replace(R.id.fragment_holder, newFragment)
                transaction.addToBackStack(null)
                transaction.commit()
            } else {
                Toast.makeText(context,"You can't vote yet!",Toast.LENGTH_SHORT).show()
            }
        }

        rootView.txt_question.setText(currentQuestionID)

        val database = FirebaseDatabase.getInstance()
        val myRef = database.reference.child("Groups").child(roomNumber)

        myRef.addValueEventListener(object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onDataChange(dataSnapshot: DataSnapshot) {

                for (ds in dataSnapshot.children) {
                    val current = ds.getValue(Question::class.java)
                    val activeC = current?.active
                    val questionC = current?.question
                    val timeC = current?.time
                    if(activeC == "true"){
                        Toast.makeText(context,"Activated: " + questionC,Toast.LENGTH_SHORT).show()
                        currentQuestionID = questionC.toString()
                        rootView.txt_question.setText("Current question: " + currentQuestionID)
                        txtTimer.setText("Time remaining: " + timeC)
                        startTimer(timeC.toString())
                    }
                }
            }
        })




        for(i in 1..10){
            optionsList.add(i.toString())
        }
        optionsList.add("Coffee")
        optionsList.add("Skip")

        mAdapter = VoteGridadapter(optionsList)
        mRecyclerView!!.adapter = mAdapter

        mRecyclerView!!.addOnItemClickListener(object : OnItemClickListener {
            override fun onItemClicked(position: Int, view: View) {
                if(currentQuestionID != "Waiting for the question..."){
                    currentVote = optionsList.get(position)

                    addVoteData()
                    //Toast.makeText(context, "clicked on " + currentVote, Toast.LENGTH_SHORT).show()
                    rootView.btn_vote.setText("Vote:    " + currentVote.toString())
                } else {
                    Toast.makeText(context,"You can't vote yet!",Toast.LENGTH_SHORT).show()
                }

            }
        })

        return rootView
    }

    private fun startTimer(time : String){
        object : CountDownTimer(1000 * (time)!!.toLong(), 1000) {

            override fun onTick(millisUntilFinished: Long) {
                txtTimer.setText("Time remaining: "+(TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished)).toString())
            }

            override fun onFinish() {
                txtTimer.setText("Time remaining: 0")
                addVoteData()
                var newFragment: Fragment = FragmentAnswers()
                var transaction: FragmentTransaction = fragmentManager!!.beginTransaction()
                transaction.replace(R.id.fragment_holder, newFragment)
                transaction.addToBackStack(null)
                transaction.commit()
            }
        }.start()
    }



    fun addVoteData(){
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