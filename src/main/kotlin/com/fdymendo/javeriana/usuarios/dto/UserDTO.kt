package com.fdymendo.javeriana.usuarios.dto

import com.fdymendo.javeriana.usuarios.entity.TypeDocumentEntity
import com.fdymendo.javeriana.usuarios.entity.UserEntity
import java.util.*

data class UserDTO(
    var id: String?,
    val name: String,
    val surname: String,
    val email: String,
    val typeDocumentEntity: TypeDocumentDTO,
    val document: String,
    val password: String,
    var active: Boolean?,
    val createDate: Date?,
    val updateDate: Date?,
)

fun UserDTO.toEntity() = UserEntity(
    name = this.name,
    surname = this.surname,
    email = this.email,
    typeDocument = TypeDocumentEntity(
        id = this.typeDocumentEntity.id,
        name = this.typeDocumentEntity.name ?: "",
        abbreviation = this.typeDocumentEntity.abbreviation ?: ""
    ),
    document = this.document,
    password = this.password,
    active = this.active ?: true,
    createDate = Date(),
    updateDate = Date()
)

fun UserDTO.clean(id: String) = UserDTO(
    id = id,
    name = this.name,
    surname = this.surname,
    email = this.email,
    typeDocumentEntity = this.typeDocumentEntity,
    document = this.document,
    password = "",
    active = this.active,
    createDate = Date(),
    updateDate = Date()
)