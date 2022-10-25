package com.fdymendo.javeriana.usuarios.service.impl

import com.fdymendo.javeriana.usuarios.dto.UserDTO
import com.fdymendo.javeriana.usuarios.dto.clean
import com.fdymendo.javeriana.usuarios.dto.toEntity
import com.fdymendo.javeriana.usuarios.entity.UserEntity
import com.fdymendo.javeriana.usuarios.entity.toDTO
import com.fdymendo.javeriana.usuarios.model.ResponseDefault
import com.fdymendo.javeriana.usuarios.repository.UserRepository
import com.fdymendo.javeriana.usuarios.service.ACrudServiceTemplate
import com.fdymendo.javeriana.usuarios.service.IUserService
import com.fdymendo.javeriana.usuarios.utils.GenericMethods
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import java.util.*

@Service
class UserServiceImpl(private val userRepository: UserRepository) :
    ACrudServiceTemplate<UserRepository, UserEntity>(userRepository), IUserService {

    override fun saveItem(item: UserDTO): ResponseEntity<ResponseDefault> {
        var itemToSave = item.toEntity()
        itemToSave.active = true;
        this.userRepository.save(itemToSave)
        return GenericMethods.responseOk(ResponseDefault(itemToSave.toDTO().clean()))
    }

    override fun getItem(id: String): ResponseEntity<ResponseDefault> {
        this.userRepository.getReferenceById(id).toDTO().let {

            return  GenericMethods.responseOk(ResponseDefault(it))
        }
        return GenericMethods.responseNotFound()
    }

    override fun updateItem(item: UserDTO, id: String): ResponseEntity<ResponseDefault> {
        this.userRepository.getReferenceById(id).toDTO().let {
            item.id = id
            item.createDate = it.createDate
            item.updateDate = Date()
            val itemToSave = item.toEntity()
            this.userRepository.save(itemToSave)
            return GenericMethods.responseOk(ResponseDefault(itemToSave.toDTO().clean()))
        }
        return GenericMethods.responseNotFound()
    }

    override fun deleteItem(id: String): ResponseEntity<ResponseDefault> {
        this.userRepository.getReferenceById(id).toDTO().let {
            it.active = false
            this.userRepository.save(it.toEntity())
            return GenericMethods.responseOk(ResponseDefault(it))
        }
        return GenericMethods.responseNotFound()
    }


    override val allItems: ResponseEntity<Any>
        get() = TODO("Not yet implemented")

}
