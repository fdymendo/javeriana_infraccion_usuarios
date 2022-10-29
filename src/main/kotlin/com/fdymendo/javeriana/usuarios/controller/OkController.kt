package com.fdymendo.javeriana.usuarios.controller

import com.fdymendo.javeriana.usuarios.utils.GenericMethods
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@CrossOrigin
@RestController
@RequestMapping("/usuarios")
class OKController {

    @GetMapping
    fun saveInfraction() = GenericMethods.responseOk()

}