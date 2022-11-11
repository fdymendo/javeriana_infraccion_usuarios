package com.fdymendo.javeriana.usuarios.service.impl

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
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
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import java.lang.Exception
import java.time.LocalDateTime
import java.time.ZoneOffset
import java.util.*

@Service
class UserServiceImpl(
    private val userRepository: UserRepository,
    private val typeDocumentRepository: TypeDocumentRepository,
    @Value("\${api.internal.jwt}")
    private val secretPwd: String
) :
    ACrudServiceTemplate<UserRepository, UserEntity>(userRepository), IUserService {
    companion object {
        val log: Logger = LoggerFactory.getLogger(UserServiceImpl::class.java)
    }
    override fun getUserByIdentity(document: String, typeDocument: String): ResponseEntity<ResponseDefault> {
        val typeDocument = typeDocumentRepository.findByAbbreviation(typeDocument)
        typeDocument.let {
            val user = userRepository.findByDocumentAndTypeDocument(document, typeDocument)
            user.let {
                return GenericMethods.responseOk(ResponseDefault(user.toDTO(), null, null, null))
            }
        }
    }

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
        return GenericMethods.responseOk(ResponseDefault(itemToSave.toDTO().clean(), null, null, null))
    }

    override fun getItem(id: String): ResponseEntity<ResponseDefault> {
        this.userRepository.getReferenceById(id).toDTO().let {
            return GenericMethods.responseOk(ResponseDefault(it, null, null, null))
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
            return GenericMethods.responseOk(ResponseDefault(itemToSave.toDTO().clean(), null, null, null))
        }
    }

    override fun deleteItem(id: String): ResponseEntity<ResponseDefault> {
        this.userRepository.getReferenceById(id).toDTO().let {
            it.active = false
            this.userRepository.save(it.toEntity())
            return GenericMethods.responseOk(ResponseDefault(it, null, null, null))
        }
    }

    override fun login(email: String, pwd: String): ResponseEntity<ResponseDefault> {
        return try {

            val person = this.userRepository.findByEmailAndPassword(email = email, pwd = pwd)
            val jws = JWT.create().withIssuer("auth0")
                .withClaim("email", person.email)
                .withExpiresAt(Date.from(LocalDateTime.now().plusDays(1L).toInstant(ZoneOffset.UTC)))
                .sign(Algorithm.HMAC256(secretPwd))
            GenericMethods.responseOk(
                ResponseDefault(
                    user = null,
                    typeDocuments = null,
                    token = jws.toString(),
                    valid = null
                )
            )
        } catch (e: Exception) {
            GenericMethods.responseBadRequest()
        }
    }

    override fun validateToken(token: String): ResponseEntity<ResponseDefault> {
        return try {
            JWT.require(Algorithm.HMAC256(secretPwd)).build().verify(token.substring(7))
            GenericMethods.responseOk(ResponseDefault(user = null, typeDocuments = null, token = null, valid = true))
        }catch (e: Exception){
            GenericMethods.responseOk(ResponseDefault(user = null, typeDocuments = null, token = null, valid = false))
        }
    }
}
