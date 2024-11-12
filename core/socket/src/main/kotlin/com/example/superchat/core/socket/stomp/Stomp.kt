package com.example.superchat.core.socket.stomp

import android.util.Log
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.WebSocket
import okhttp3.WebSocketListener


interface StompListener{
    fun connected()
    fun message(header: Map<String, String>, body: String)
    fun receipt(id: String)
    fun pong()
    fun error(message: String)
}

class Stomp(
): StompProtocol, WebSocketListener(){

    private var webSocket: WebSocket?
    var listener: StompListener?

    private var url: String?
    private var connectedHeader: Map<String, String>?


    init {
        listener = null
        webSocket = null
        url = null
        connectedHeader = null
    }

    override fun onOpen(webSocket: WebSocket, response: okhttp3.Response) {
        Log.i("WebSocketListener", "WebSocket open")
        sendConnectFrame()
    }

    override fun onMessage(webSocket: WebSocket, text: String) {
        processString(text)
    }

    override fun onClosed(webSocket: WebSocket, code: Int, reason: String) {
        Log.i("WebSocketListener", "WebSocket 연결 종료")
    }

    override fun onFailure(webSocket: WebSocket, t: Throwable, response: okhttp3.Response?) {
        Log.i("WebSocketListener","WebSocket 에러: ${t.message}")
    }


    override fun connect(url: String, header: Map<String, String>){
        this.url = url
        this.connectedHeader = header
        openWebSocket()
    }

    override fun disConnect(){
        sendDisconnectedFrame()
    }

    override fun subscribe(dest: String, ackMode: AckMode, header: Map<String, String>){
        sendSubscribeFrame(dest, ackMode, header)
    }

    override fun unSubscribe(dest: String, header: Map<String, String>){
        sendUnSubscribeFrame(dest, header)
    }


    override fun send(message: String, dest: String, header: Map<String, String>){
        sendMessageFrame(message, dest, header)
    }

    override fun ack(ack: String, header: Map<String, String>){
        sendAckFrame(ack, header)
    }

    private fun openWebSocket(){
        if(webSocket == null){
            val httpClient = OkHttpClient.Builder()
                .build()
            val request = Request.Builder().url(url!!).build()
            this.webSocket = httpClient.newWebSocket(request, this)
        }
    }

    private fun closeWebSocket(){
        webSocket?.close(1000, "Close")
        webSocket = null
        url = null
        connectedHeader = null
    }

    private fun sendConnectFrame(){
        val headers: MutableMap<String, String> = mutableMapOf(
            StompHeader.ACCEPT_VERSION to "1.2,1.1,1.0",
            StompHeader.HOST to url!!,
        )

        connectedHeader!!.forEach { (key, value) ->
            headers[key] = value
        }

        sendFrame(
            command = StompCommand.CONNECT,
            header = headers,
            body = null
        )
    }

    private fun sendDisconnectedFrame(){
        sendFrame(
            command = StompCommand.DISCONNECT,
            header = mapOf(StompHeader.RECEIPT to "77"),
            body =  null
        )
    }

    private fun sendSubscribeFrame(dest: String, ackMode: AckMode, withHeader: Map<String, String>){
        val header = withHeader.toMutableMap()
        header[StompHeader.DESTINATION] = dest
        header[StompHeader.ID] = dest
        header[StompHeader.ACK] = ackMode.value()

        sendFrame(
            command = StompCommand.SUBSCRIBE,
            header = header,
            body = null
        )
    }

    private fun sendUnSubscribeFrame(dest: String, withHeader: Map<String, String>){
        val header = withHeader.toMutableMap()
        header[StompHeader.ID] = dest
        sendFrame(
            command = StompCommand.UNSUBSCRIBE,
            header = header,
            body = null
        )
    }


    private fun sendMessageFrame(message: String, dest: String, withHeader: Map<String, String>){
        val header = withHeader.toMutableMap()

        header[StompHeader.DESTINATION] = dest
        header[StompHeader.CONTENT_LENGTH] = message.toByteArray(Charsets.UTF_8).size.toString()
        header[StompHeader.CONTENT_TYPE] = "application/json;charset=UTF-8"

        sendFrame(
            command = StompCommand.SEND,
            header = header,
            body = message
        )
    }


    private fun sendFrame(command: String?, header: Map<String, String>?, body: Any?) {
        val frameString = StringBuilder()

        if (command != null) {
            frameString.append(command).append("\n")
        }

        header?.forEach { (key, value) ->
            frameString.append(key).append(":").append(value).append("\n")
        }

        if (body is String) {
            frameString.append("\n").append(body)
        } else if (body == null) {
            frameString.append("\n")
        }

        frameString.append("\u0000")
        webSocket?.send(frameString.toString())
    }

    private fun sendAckFrame(ack: String, withHeader: Map<String, String>){
        val header = withHeader.toMutableMap()
        header[StompHeader.ID] = ack
        sendFrame(
            command = StompCommand.ACK,
            header = header,
            body = null
        )
    }


    private fun processString(string: String) {
        val contents = string.split("\n").toMutableList()
        if (contents.firstOrNull() == "") {
            contents.removeAt(0)
        }

        val command = contents.firstOrNull()
        if (command != null) {
            val headers = mutableMapOf<String, String>()
            var body = ""
            var hasHeaders = false

            contents.removeAt(0)

            for (line in contents) {
                if (hasHeaders) {
                    body += line
                } else {
                    if (line == "") {
                        hasHeaders = true
                    } else {
                        val parts = line.split(":")
                        val key = parts.firstOrNull()
                        if (key != null) {
                            headers[key] = parts.drop(1).joinToString(":")
                        }
                    }
                }
            }

            // Remove the garbage from body
            if (body.endsWith("\u0000")) {
                body = body.replace("\u0000", "")
            }


            receiveFrame(command, headers, body)
        }
    }

    private fun receiveFrame(command: String, headers: Map<String, String>, body: String?){
        when(command){
            StompCommand.CONNECTED -> {
                Log.i("stomp", "Connected")
                listener?.connected()
            }

            StompCommand.MESSAGE ->{
                Log.i("stomp", "MSG: $body")
                listener?.message(headers, body!!)
            }

            StompCommand.RECEIPT ->{
                val receiptId = headers["receipt-id"]
                receiptId?.let {
                    Log.i("socket", "Receipt: ${it}")
                    if (it == "77"){
                        closeWebSocket()
                    }else{
                        listener?.receipt(it)
                    }
                }
            }

            StompCommand.PONG ->{
                listener?.pong()
                webSocket?.send("\n")
            }

            StompCommand.ERROR ->{
                val message = headers["message"]
                message?.let {
                    Log.e("socket", "Error: ${message}")
                    listener?.error(message)
                }
            }

        }
    }
}