package com.example.contactsmanger

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import com.example.contactsmanger.databinding.ActivityMainBinding
import com.example.contactsmanger.room.Contacts
import com.example.contactsmanger.room.ContactsDB
import com.example.contactsmanger.view.MyRecyclerViewAdapter
import com.example.contactsmanger.viewmodel.ContactsViewmodel
import com.example.contactsmanger.viewmodel.ViewModelFactory

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var contactsViewmodel: ContactsViewmodel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main)

        val dao = ContactsDB.getInstance(applicationContext).contactDAO
        val repository= ContactsRepository(dao)
        val factory= ViewModelFactory(repository)

        contactsViewmodel= ViewModelProvider(
            this
        ).get(contactsViewmodel::class.java)
        binding.contactsViewmodel=contactsViewmodel
        binding.lifecycleOwner=this
        initRecyclerView()


    }
    private fun initRecyclerView(){
     binding.recyclerView.layoutManager=LinearLayoutManager(this)
        DisplayUsersList()
    }
    private fun  DisplayUsersList(){
        contactsViewmodel.contacts.observe(this,{
            binding.recyclerView.adapter=MyRecyclerViewAdapter(it,
                {selectedItem : Contacts -> listItemClicked(selectedItem)} )
        })
    }
    private fun listItemClicked(selectedItem:Contacts){
        Toast.makeText(
            this,
            " Selected Item is$(selectedItem.contact_name)",
            Toast.LENGTH_LONG).show()

        contactsViewmodel.initUpdateAndDelete((selectedItem)

        )
    }


}