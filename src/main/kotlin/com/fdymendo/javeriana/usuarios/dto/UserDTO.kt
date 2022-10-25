package com.fdymendo.javeriana.usuarios.dto

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty
import com.fdymendo.javeriana.usuarios.entity.TypeDocumentEntity
import com.fdymendo.javeriana.usuarios.entity.UserEntity
import java.util.*

@JsonIgnoreProperties(ignoreUnknown = true)
data class UserDTO(
    @JsonProperty("id") var id: String?,
    @JsonProperty("name") val name: String,
    @JsonProperty("surname") val surname: String,
    @JsonProperty("email") val email: String,
    @JsonProperty("typeDocumentEntity") val typeDocumentEntity: TypeDocumentDTO,
    @JsonProperty("document") val document: String,
    @JsonProperty("password") var password: String,
    @JsonProperty("active") var active: Boolean?,
    @JsonProperty("createDate") var createDate: Date?,
    @JsonProperty("updateDate") var updateDate: Date?,
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
    createDate = this.createDate ?: Date(),
    updateDate = this.updateDate ?: Date()
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
fun UserDTO.clean() = UserDTO(
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