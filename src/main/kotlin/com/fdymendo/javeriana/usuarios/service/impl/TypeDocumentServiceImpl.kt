package com.fdymendo.javeriana.usuarios.service.impl

import com.fdymendo.javeriana.usuarios.entity.TypeDocumentEntity
import com.fdymendo.javeriana.usuarios.model.ResponseDefault
import com.fdymendo.javeriana.usuarios.repository.TypeDocumentRepository
import com.fdymendo.javeriana.usuarios.service.ACrudServiceTemplate
import com.fdymendo.javeriana.usuarios.service.ITypeDocumentService
import com.fdymendo.javeriana.usuarios.utils.GenericMethods
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service

@Service
class TypeDocumentServiceImpl(
    private val typeDocumentRepository: TypeDocumentRepository
) : ACrudServiceTemplate<TypeDocumentRepository, TypeDocumentEntity>(typeDocumentRepository), ITypeDocumentService {

    override fun saveItem(item: TypeDocumentEntity): ResponseEntity<ResponseDefault> {
        TODO("Not yet implemented")
    }

    override fun deleteItem(id: String): ResponseEntity<ResponseDefault> {
        TODO("Not yet implemented")
    }

    override fun getItem(id: String): ResponseEntity<ResponseDefault> {
        TODO("Not yet implemented")
    }

    override fun updateItem(item: TypeDocumentEntity, id: String): ResponseEntity<ResponseDefault> {
        TODO("Not yet implemented")
    }

    override fun allItems() =
        GenericMethods.responseOk(ResponseDefault(null, typeDocumentRepository.findAll(), null, null))
}