package com.example.contactsmanger.viewmodel

import androidx.databinding.Bindable
import androidx.databinding.Observable
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.contactsmanger.ContactsRepository
import com.example.contactsmanger.room.Contacts
import kotlinx.coroutines.launch

class ContactsViewmodel (private val repository: ContactsRepository): ViewModel(), Observable{
      val contacts = repository.contacts
    private  var isupdateorDelete = false
    private lateinit var contactsToupdateorDelete : Contacts
    @Bindable
    val inputname = MutableLiveData<String?>()
     @Bindable
    val inputEmail = MutableLiveData<String?>()
     @Bindable
    val saveorUpdateButtonNext = MutableLiveData<String>()
     @Bindable
    val ClearalltextorDeleteButtonNext = MutableLiveData<String>()
     init{
         saveorUpdateButtonNext.value="Save"
         ClearalltextorDeleteButtonNext.value ="Clear All"
     }
    fun insert(contacts: Contacts)= viewModelScope.launch {
        repository.insertContact(contacts)
    }

    fun delete(contacts: Contacts)=viewModelScope.launch {
        repository.deleteContact(contacts)
        inputname.value=null
        inputEmail.value=null
        isupdateorDelete=false
        saveorUpdateButtonNext.value="save"
        ClearalltextorDeleteButtonNext.value="clearALL"
    }

    fun update(contacts: Contacts)= viewModelScope.launch {
        repository.updateContact(contacts)

        inputname.value=null
        inputEmail.value=null
        isupdateorDelete=false
        saveorUpdateButtonNext.value="save"
        ClearalltextorDeleteButtonNext.value="clearALL"
    }
    fun clearALl()=viewModelScope.launch {
        repository.deleteAllContacts()
    }
    fun saveorupdate(){
        if(isupdateorDelete){
            contactsToupdateorDelete.contact_name = inputname.value!!
            contactsToupdateorDelete.contact_mail= inputEmail.value!!
            update(contactsToupdateorDelete)
        }
        else{
            val name = inputname.value!!
            val email= inputEmail.value!!
            insert(Contacts(0,name,email))
            inputname.value=null
            inputEmail.value=null
        }
    }
    fun clearALlorDelete (){
        if(isupdateorDelete){
            delete(contactsToupdateorDelete)
        }
        else{
            clearALl()
        }
    }
    fun initUpdateAndDelete(contacts: Contacts){
        inputname.value=contacts.contact_name
        inputEmail.value=contacts.contact_mail
        isupdateorDelete=true
        contactsToupdateorDelete=contacts
        saveorUpdateButtonNext.value="Update"
        ClearalltextorDeleteButtonNext.value="Delete"
    }

    override fun addOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {
        TODO("Not yet implemented")
    }

    override fun removeOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {
        TODO("Not yet implemented")
    }
}
