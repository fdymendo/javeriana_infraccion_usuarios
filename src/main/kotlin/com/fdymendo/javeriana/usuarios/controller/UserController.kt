package com.fdymendo.javeriana.usuarios.controller

import com.fdymendo.javeriana.usuarios.dto.UserDTO
import com.fdymendo.javeriana.usuarios.service.IUserService
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/users/v1")
class UserController(val iUserService: IUserService) {

    @GetMapping(name = "/{id}")
    fun getUser(@PathVariable id: String) = iUserService.getItem(id)

    @PostMapping(name = "/")
    fun saveUser(@RequestBody userDTO: UserDTO) = iUserService.saveItem(userDTO)

    @PutMapping(name = "/{id}")
    fun putUser(@PathVariable id: String, @RequestBody userDTO: UserDTO) = iUserService.updateItem(userDTO, id)

    @DeleteMapping(name = "/{id}")
    fun putUser(@PathVariable id: String) = iUserService.deleteItem(id)

}