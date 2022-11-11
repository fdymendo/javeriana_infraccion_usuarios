package com.fdymendo.javeriana.usuarios.repository

import com.fdymendo.javeriana.usuarios.entity.TypeDocumentEntity
import com.fdymendo.javeriana.usuarios.entity.UserEntity
import com.fdymendo.javeriana.usuarios.model.ResponseDefault
import org.apache.catalina.User
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository : JpaRepository<UserEntity, String> {
    fun findByDocumentAndTypeDocument(document: String, typeDocument: TypeDocumentEntity): UserEntity
    fun findByEmailAndPassword(email: String, pwd: String): UserEntity
}