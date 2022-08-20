package de.narlt.wsrelay.wsrelay

import org.springframework.context.annotation.Configuration
import org.springframework.stereotype.Component
import org.springframework.web.socket.SubProtocolCapable
import org.springframework.web.socket.TextMessage
import org.springframework.web.socket.WebSocketSession
import org.springframework.web.socket.config.annotation.EnableWebSocket
import org.springframework.web.socket.config.annotation.WebSocketConfigurer
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry
import org.springframework.web.socket.handler.TextWebSocketHandler
import java.io.IOException
import java.util.concurrent.CopyOnWriteArrayList


@Configuration
@EnableWebSocket
class WebSocketConfig : WebSocketConfigurer {
    override fun registerWebSocketHandlers(registry: WebSocketHandlerRegistry) {
        registry.addHandler(SocketHandler(), "/ws/*").setAllowedOrigins("*")
    }
}

@Component
class SocketHandler : TextWebSocketHandler() {
    var sessions: MutableList<WebSocketSession> = CopyOnWriteArrayList()

    @Throws(InterruptedException::class, IOException::class)
    override fun handleTextMessage(session: WebSocketSession, message: TextMessage) {
        for (webSocketSession in sessions) {
            val text = message.payload
            webSocketSession.sendMessage(TextMessage("Reply: $text"))
        }
    }

    @Throws(Exception::class)
    override fun afterConnectionEstablished(session: WebSocketSession) {
        //the messages will be broadcasted to all users.
        sessions.add(session)
    }
}