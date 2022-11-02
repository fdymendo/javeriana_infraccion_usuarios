package com.fdymendo.javeriana.usuarios.controller

import com.fdymendo.javeriana.usuarios.service.ITypeDocumentService
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@CrossOrigin
@RestController
@RequestMapping("/typeDocument/v1")
class TypeDocumentController(
    val iTypeDocumentService: ITypeDocumentService
) {

    @GetMapping
    fun getAll() = iTypeDocumentService.allItems()

}