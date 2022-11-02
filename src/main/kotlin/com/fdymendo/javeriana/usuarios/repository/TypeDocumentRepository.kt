package com.fdymendo.javeriana.usuarios.repository

import com.fdymendo.javeriana.usuarios.entity.TypeDocumentEntity
import com.fdymendo.javeriana.usuarios.entity.UserEntity
import org.springframework.data.jpa.repository.JpaRepository

interface TypeDocumentRepository: JpaRepository<TypeDocumentEntity, String>{

    fun findByAbbreviation(abbreviation: String): TypeDocumentEntity

}