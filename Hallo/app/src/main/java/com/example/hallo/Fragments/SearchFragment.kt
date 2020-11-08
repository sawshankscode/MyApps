package com.example.hallo.Fragments

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.hallo.AdapterClasses.UserAdapter
import com.example.hallo.ModelClasses.Users
import com.example.hallo.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.fragment_search.*

class SearchFragment :Fragment() {
    private var userAdapter: UserAdapter? = null
    private  var mUsers:List<Users>?=null
    private var recyclerView:RecyclerView?=null
    private var searchEditTxt:EditText?=null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view:View= inflater.inflate(R.layout.fragment_search, container, false)

        recyclerView=view.findViewById(R.id.searchList)
        recyclerView!!.setHasFixedSize(true)
        recyclerView!!.layoutManager=LinearLayoutManager(context)
        searchEditTxt=view.findViewById(R.id.searchUsersET)


        mUsers=ArrayList()
        retrieveAllUsers()
        searchEditTxt!!.addTextChangedListener(object :TextWatcher{
            override fun afterTextChanged(p0: Editable?) {

            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                searchForUsers(p0.toString().toLowerCase())
            }
        })
        return view
    }

    private fun retrieveAllUsers() {
        var firebaseUserID=FirebaseAuth.getInstance().currentUser!!.uid
        val refUsers= FirebaseDatabase.getInstance().reference.child("Users")
        refUsers.addValueEventListener(object: ValueEventListener{
            override fun onDataChange(p0: DataSnapshot) {
                (mUsers as ArrayList<Users>).clear()
                if(searchEditTxt!!.text.toString()=="") {
                    for (snapshot in p0.children) {
                        var user: Users? = snapshot.getValue(Users::class.java)
                        if (!(user!!.getUID()).equals(firebaseUserID))
                            (mUsers as ArrayList<Users>).add(user)
                    }

                    userAdapter = UserAdapter(context!!, mUsers!!, false)

                    recyclerView!!.adapter = userAdapter
                }
            }

            override fun onCancelled(p0: DatabaseError) {

            }

        })

    }
    private fun searchForUsers(str:String){
        var firebaseUserID=FirebaseAuth.getInstance().currentUser!!.uid
        val queryUsers= FirebaseDatabase.getInstance().reference.child("Users").child("Users").orderByChild("search")
            .startAt(str+"\uf8ff")
            .endAt(str+"\uf8ff")
        queryUsers.addValueEventListener(object :ValueEventListener{
            override fun onDataChange(p0: DataSnapshot) {
                (mUsers as ArrayList<Users>).clear()
                for(snapshot in p0.children){
                    var user:Users? =snapshot.getValue(Users::class.java)
                    if(!(user!!.getUID()).equals(firebaseUserID))
                        (mUsers as ArrayList<Users>).add(user)
                }
                userAdapter= UserAdapter(context!!,mUsers!!,false)
                recyclerView!!.adapter=userAdapter
            }

            override fun onCancelled(p0: DatabaseError) {

            }

        })

    }


}
