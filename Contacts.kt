package com.example.contactsmanger.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("contacts_table")
data class Contacts(
    @PrimaryKey(true)
    val contact_id : Int,
    var contact_name : String,
    var contact_mail : String

)
