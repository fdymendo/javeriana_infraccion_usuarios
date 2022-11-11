package com.fdymendo.javeriana.usuarios.controller

import com.fdymendo.javeriana.usuarios.dto.UserDTO
import com.fdymendo.javeriana.usuarios.service.IUserService
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.*

@CrossOrigin
@RestController
@RequestMapping("/usuarios/v1")
class UserController(val iUserService: IUserService) {

    @GetMapping("/{id}")
    fun getUser(@PathVariable("id") id: String) = iUserService.getItem(id)

    @GetMapping
    fun getUserByIdentity(@RequestParam cc: String, @RequestParam td: String) = iUserService.getUserByIdentity(cc, td)

    @PostMapping
    fun saveUser(@RequestBody userDTO: UserDTO) = iUserService.saveItem(userDTO)

    //@PutMapping("/{id}")
    //fun putUser(@PathVariable("id") id: String, @RequestBody userDTO: UserDTO) = iUserService.updateItem(userDTO, id)

    @DeleteMapping("/{id}")
    fun deleteUser(@PathVariable("id") id: String) = iUserService.deleteItem(id)

    @GetMapping("/login")
    fun login(@RequestHeader email: String, @RequestHeader pwd: String) = iUserService.login(email = email, pwd = pwd)

    @GetMapping("/valid")
    fun validateToken(@RequestHeader("Authorization") token: String) = iUserService.validateToken(token)

}