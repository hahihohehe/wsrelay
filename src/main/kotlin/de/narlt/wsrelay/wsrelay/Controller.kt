package de.narlt.wsrelay.wsrelay

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@RestController
class Controller {

    @Autowired
    lateinit var socketHandler: SocketHandler

    @PostMapping("/display/{num}")
    fun display(@PathVariable num: String, @RequestBody data : String) : String {
        println("display called with char: $data")
        socketHandler.send(num, data)
        return "OK"
    }
}