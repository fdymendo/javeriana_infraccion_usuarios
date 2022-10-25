package com.fdymendo.javeriana.usuarios.service

import com.fdymendo.javeriana.usuarios.model.ResponseDefault
import com.fdymendo.javeriana.usuarios.handlers.ApplicationException
import org.springframework.http.ResponseEntity


interface ICrudTemplate<T> {
    /**
     *
     * @param item objeto a guardar
     *
     * @return retorna el objeto guardado
     */
    fun saveItem(item: T): ResponseEntity<ResponseDefault>

    @Throws(ApplicationException::class)
    fun updateItem(item: T, id: String): ResponseEntity<ResponseDefault>
    fun deleteItem(id: String): ResponseEntity<ResponseDefault>
    fun getItem(id: String): ResponseEntity<ResponseDefault>
    val allItems: ResponseEntity<Any>
}
