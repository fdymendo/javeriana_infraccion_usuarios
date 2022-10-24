package com.fdymendo.javeriana.usuarios.entity

import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "type_document")
data class TypeDocumentEntity(
    @Id
    val id: String,
    val name: String,
    val abbreviation: String
)