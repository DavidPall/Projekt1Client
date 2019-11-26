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
import com.example.firebasetest.roomNumber
import com.example.firebasetest.userID
import com.google.firebase.FirebaseApp
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_login.view.*


/**
 * Created by VickY on 07-09-2017.
 */
class FragmentLogin : Fragment(){

    val TAG = "FragmentLogin"

    private lateinit var database: DatabaseReference


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

        val rootView = inflater.inflate(R.layout.fragment_login,container,false)

        var roomExist = false

        database = FirebaseDatabase.getInstance().reference

        val databaseRef = FirebaseDatabase.getInstance()
        val myRef = databaseRef.reference.child("Groups").orderByKey()



        val loginBtn : Button = rootView.findViewById(R.id.btn_login)

        if(roomExist){
            TODO("not implemented") //Checking if the roomID is exist or not
        }


        loginBtn.setOnClickListener {
            if (rootView.edt_name.text.isNotEmpty() && rootView.edt_room_id.text.isNotEmpty()){
                roomNumber = rootView.edt_room_id.text.toString()
                userID = rootView.edt_name.text.toString()

                //database.child("Groups").child(roomNumber).child("Users").child(userID).child("userID").setValue(userID)

                var newFragment : Fragment = FragmentVote()
                var transaction : FragmentTransaction = fragmentManager!!.beginTransaction()
                transaction.replace(R.id.fragment_holder,newFragment)
                transaction.addToBackStack(null)
                transaction.commit()
            }
            //Toast.makeText(this.context,roomExist.toString(),Toast.LENGTH_SHORT).show()
        }

        return rootView
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