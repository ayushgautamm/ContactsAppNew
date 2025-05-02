package com.example.contactsmanger.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import com.example.contactsmanger.ContactsRepository

class ViewModelFactory(private val repository: ContactsRepository):
    ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>, extras: CreationExtras): T {
       if (modelClass.isAssignableFrom((ContactsViewmodel::class.java))){
           return ContactsViewmodel(repository) as T
       }
        throw IllegalArgumentException("Unknown View Model Class")
    }
}