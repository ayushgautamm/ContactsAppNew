package com.example.contactsmanger

import androidx.lifecycle.LiveData
import com.example.contactsmanger.room.Contacts
import com.example.contactsmanger.room.ContactsDAO

class ContactsRepository(private val contactsDAO: ContactsDAO) {

    val contacts = contactsDAO.getallContactsinDB()
    // Insert a contact and return the inserted row ID
    suspend fun insertContact(contact: Contacts): Long {
        return contactsDAO.insertContacts(contact)
    }

    // Update an existing contact
    suspend fun updateContact(contact: Contacts) {
        contactsDAO.updateContacts(contact)
    }

    // Delete a specific contact
    suspend fun deleteContact(contact: Contacts) {
        contactsDAO.deleteContacs(contact)
    }

    // Delete all contacts from the table
    suspend fun deleteAllContacts() {
        contactsDAO.deleteAll()
    }

    // Get all contacts from the database as LiveData
    fun getAllContacts(): LiveData<List<Contacts>> {
        return contactsDAO.getallContactsinDB()
    }
}
