package com.fdymendo.javeriana.usuarios.utils

import com.fdymendo.javeriana.usuarios.model.ResponseDefault
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity


class GenericMethods {
    companion object{

        fun responseOk(responseDefault: ResponseDefault): ResponseEntity<ResponseDefault> {
            return ResponseEntity.ok(responseDefault)
        }

        fun responseNotFound(): ResponseEntity<ResponseDefault> {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null)
        }

    }
}