package com.miramicodigo.recyclerviewcards

import java.io.Serializable

class Pokemon : Serializable {
    var nombre: String? = null
    var tipo: String? = null
    var imagen: Int = 0

    constructor() {

    }

    constructor(nombre: String, tipo: String, imagen: Int) {
        this.nombre = nombre
        this.tipo = tipo
        this.imagen = imagen
    }
}

