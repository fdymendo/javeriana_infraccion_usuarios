package com.fdymendo.javeriana.usuarios.service.impl

import com.fdymendo.javeriana.usuarios.dto.UserDTO
import com.fdymendo.javeriana.usuarios.dto.clean
import com.fdymendo.javeriana.usuarios.dto.toEntity
import com.fdymendo.javeriana.usuarios.entity.UserEntity
import com.fdymendo.javeriana.usuarios.entity.toDTO
import com.fdymendo.javeriana.usuarios.model.ResponseDefault
import com.fdymendo.javeriana.usuarios.repository.TypeDocumentRepository
import com.fdymendo.javeriana.usuarios.repository.UserRepository
import com.fdymendo.javeriana.usuarios.service.ACrudServiceTemplate
import com.fdymendo.javeriana.usuarios.service.IUserService
import com.fdymendo.javeriana.usuarios.utils.GenericMethods
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import java.util.*

@Service
class UserServiceImpl(
    private val userRepository: UserRepository,
    private val typeDocumentRepository: TypeDocumentRepository
) :
    ACrudServiceTemplate<UserRepository, UserEntity>(userRepository), IUserService {

    override fun getUserByIdentity(document: String, typeDocument: String): ResponseEntity<ResponseDefault> {
        val typeDocument = typeDocumentRepository.findByAbbreviation(typeDocument)
        typeDocument.let {
            val user = userRepository.findByDocumentAndTypeDocument(document, typeDocument)
            user.let {
                return GenericMethods.responseOk(ResponseDefault(user.toDTO(), null))
            }
        }
    }

    override fun saveItem(item: UserDTO): ResponseEntity<ResponseDefault> {
        var itemToSave = item.toEntity()
        itemToSave.active = true;
        this.userRepository.save(itemToSave)
        return GenericMethods.responseOk(ResponseDefault(itemToSave.toDTO().clean(), null))
    }

    override fun getItem(id: String): ResponseEntity<ResponseDefault> {
        this.userRepository.getReferenceById(id).toDTO().let {
            return GenericMethods.responseOk(ResponseDefault(it, null))
        }
    }

    override fun allItems(): ResponseEntity<ResponseDefault> {
        TODO("Not yet implemented")
    }

    override fun updateItem(item: UserDTO, id: String): ResponseEntity<ResponseDefault> {
        this.userRepository.getReferenceById(id).toDTO().let {
            item.id = id
            item.createDate = it.createDate
            item.updateDate = Date()
            val itemToSave = item.toEntity()
            this.userRepository.save(itemToSave)
            return GenericMethods.responseOk(ResponseDefault(itemToSave.toDTO().clean(), null))
        }
    }

    override fun deleteItem(id: String): ResponseEntity<ResponseDefault> {
        this.userRepository.getReferenceById(id).toDTO().let {
            it.active = false
            this.userRepository.save(it.toEntity())
            return GenericMethods.responseOk(ResponseDefault(it, null))
        }
    }

}
