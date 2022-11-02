package com.fdymendo.javeriana.usuarios.repository

import com.fdymendo.javeriana.usuarios.entity.TypeDocumentEntity
import com.fdymendo.javeriana.usuarios.entity.UserEntity
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository : JpaRepository<UserEntity, String> {
    fun findByDocumentAndTypeDocument(document: String, typeDocument: TypeDocumentEntity): UserEntity
}