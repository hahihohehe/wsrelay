package de.narlt.wsrelay.wsrelay

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class WsrelayApplication

fun main(args: Array<String>) {
	runApplication<WsrelayApplication>(*args)
}
