package com.miramicodigo.restful_1.model

class Pokemon {
    var number: Int = 0
        get() {
            val urlPartes = url!!.split("/".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
            return Integer.parseInt(urlPartes[urlPartes.size - 1])
        }
    var name: String? = null
    var url: String? = null
}