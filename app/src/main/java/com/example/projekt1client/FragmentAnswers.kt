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
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.firebasetest.currentQuestionID
import com.example.firebasetest.roomNumber
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.activity_main.*

/**
 * Created by VickY on 07-09-2017.
 */
class FragmentAnswers : Fragment(){

    val TAG = "FragmentLogin"
    private var mRecyclerView: RecyclerView? = null
    private var mAdapter: RecyclerView.Adapter<*>? = null

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

        val rootView = inflater.inflate(R.layout.fragment_answers,container,false)

        var txt_question : TextView = rootView.findViewById(R.id.txt_questionName)

        txt_question.setText("Question: " + currentQuestionID)

        mRecyclerView = rootView.findViewById(R.id.rvItems) as RecyclerView
        mRecyclerView!!.layoutManager = LinearLayoutManager(this.context, LinearLayoutManager.VERTICAL, false)

        val users : ArrayList<User> = loadwithdata()

        mAdapter = ResultAdapter(users)
        mRecyclerView!!.adapter = mAdapter

        return rootView
    }

    fun loadwithdata() : ArrayList<User>{
        val users : ArrayList<User> = ArrayList()

        val database = FirebaseDatabase.getInstance()
        val myRef = database.reference.child("Groups").child(roomNumber).child(currentQuestionID).child("Votes")


        myRef.addValueEventListener(object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onDataChange(dataSnapshot: DataSnapshot) {
                users.clear()
                for (ds in dataSnapshot.children) {
                    val current = ds.getValue(User::class.java)
                    val nameC = current?.userID
                    val voteC  = current?.Vote
                    users.add(User(nameC,voteC))
                    mAdapter?.notifyDataSetChanged()
                }

            }
        })
        return users
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