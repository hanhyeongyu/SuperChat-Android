package com.example.superchat.core.socket.stomp

interface StompProtocol {
    fun connect(url: String, header: Map<String, String>)
    fun disConnect()
    fun subscribe(dest: String, ackMode: AckMode, header: Map<String, String>)
    fun unSubscribe(dest: String, header: Map<String, String>)
    fun send(message: String, dest: String, header: Map<String, String>)
    fun ack(ack: String, header: Map<String, String>)
}