package com.fdymendo.javeriana.usuarios.controller

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/")
class OKController {

    @GetMapping
    fun saveInfraction() = ResponseEntity.ok()

}