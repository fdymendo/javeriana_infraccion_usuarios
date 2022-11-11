package com.fdymendo.javeriana.usuarios.service

import com.fdymendo.javeriana.usuarios.dto.TypeDocumentDTO
import com.fdymendo.javeriana.usuarios.dto.UserDTO
import com.fdymendo.javeriana.usuarios.model.ResponseDefault
import org.springframework.http.ResponseEntity

interface IUserService : ICrudTemplate<UserDTO> {

    fun getUserByIdentity(document: String, typeDocument: String): ResponseEntity<ResponseDefault>
    fun login(email:String, pwd: String): ResponseEntity<ResponseDefault>
    fun validateToken(token:String): ResponseEntity<ResponseDefault>
}