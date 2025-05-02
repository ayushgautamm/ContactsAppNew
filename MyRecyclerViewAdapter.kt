package com.example.contactsmanger.view

import android.content.DialogInterface.OnClickListener
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.contactsmanger.R
import com.example.contactsmanger.databinding.CardItemBinding
import com.example.contactsmanger.room.Contacts

class MyRecyclerViewAdapter(private val contactList: List<Contacts>,
    private val clickListener: (Contacts)->Unit): RecyclerView.Adapter<MyRecyclerViewAdapter.MyViewHolder>() {

        class MyViewHolder(val binding:CardItemBinding) :
            RecyclerView.ViewHolder(binding.root){
                fun bind(contacts: Contacts,clickListener: (Contacts) -> Unit){
                   binding.nameTextView.text=contacts.contact_name
                    binding.emailTextView.text = contacts.contact_mail
                    binding.listItemLayout.setOnClickListener{}
                    clickListener(contacts)
                }

            }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val layoutInflater=LayoutInflater.from(parent.context)
        val binding:CardItemBinding=DataBindingUtil.
       inflate(layoutInflater,
           R.layout.card_item,
           parent,false)
        return MyViewHolder((binding))
    }

    override fun getItemCount(): Int {
      return contactList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(contactList[position],clickListener)
    }

}