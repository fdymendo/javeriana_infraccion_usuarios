package com.fdymendo.javeriana.usuarios.entity

import com.fdymendo.javeriana.usuarios.dto.TypeDocumentDTO
import com.fdymendo.javeriana.usuarios.dto.UserDTO
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "user")
data class UserEntity(
    @Id val id: String = UUID.randomUUID().toString(),
    val name: String,
    val surname: String,
    val email: String,
    @ManyToOne
    val typeDocument: TypeDocumentEntity,
    val document: String,
    val password: String,
    @Column(columnDefinition = "tinyint")
    var active: Boolean,
    val createDate: Date,
    val updateDate: Date,
)

fun UserEntity.toDTO() = UserDTO(
    id = this.id,
    name = this.name,
    surname = this.surname,
    email = this.email,
    typeDocument = TypeDocumentDTO(
        id = this.typeDocument.id,
        name = this.typeDocument.name,
        abbreviation = this.typeDocument.abbreviation
    ),
    document = this.document,
    password = this.password,
    active = this.active,
    createDate = this.createDate,
    updateDate = this.updateDate
)