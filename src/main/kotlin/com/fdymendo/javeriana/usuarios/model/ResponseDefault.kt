package com.fdymendo.javeriana.usuarios.model

import com.fdymendo.javeriana.usuarios.dto.UserDTO
import com.fdymendo.javeriana.usuarios.entity.TypeDocumentEntity

data class ResponseDefault(
    var user: UserDTO?,
    var typeDocuments: List<TypeDocumentEntity>?) {


}
