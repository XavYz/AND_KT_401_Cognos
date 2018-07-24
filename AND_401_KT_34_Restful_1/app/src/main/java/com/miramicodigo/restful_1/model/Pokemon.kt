package com.miramicodigo.restful_1.model

class Pokemon {
    var name: String? = null
    var url: String? = null

    var number: Int = 0
        get() {
            val urlParse = url!!.split("/".toRegex())
                    .dropLastWhile { it.isEmpty() }.toTypedArray()
            return Integer.parseInt(urlParse[urlParse.size - 1])
        }
}