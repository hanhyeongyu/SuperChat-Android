package com.example.superchat.core.socket.stomp

object StompCommand{
    //send
    const val CONNECT = "CONNECT"
    const val DISCONNECT = "DISCONNECT"
    const val SUBSCRIBE = "SUBSCRIBE"
    const val UNSUBSCRIBE = "UNSUBSCRIBE"
    const val SEND = "SEND"
    const val ACK = "ACK"

    //receive
    const val CONNECTED = "CONNECTED"
    const val MESSAGE = "MESSAGE"
    const val RECEIPT = "RECEIPT"
    const val PONG = ""
    const val ERROR = "ERROR"
}


object StompHeader{
    //Send

    //common
    const val RECEIPT = "receipt"
    const val DESTINATION = "destination"

    //connect
    const val ACCEPT_VERSION = "accept-version"
    const val HOST = "host"
    const val HEART_BEAT = "heart-beat"
    const val AUTHORIZATION = "Authorization"

    //subscribe
    const val ID = "id"
    const val ACK = "ack"
    const val CLIENT_INDIVIDUAL = "client-individual"
    const val CLIENT = "client"
    const val AUTO = "auto"

    //send
    const val CONTENT_LENGTH = "content-length"
    const val CONTENT_TYPE = "content-type"


    //receive
    const val RECEIPT_ID = "receipt-id"
    const val MESSAGE = "message"

}

enum class AckMode {
    AutoMode,
    ClientMode,
    ClientIndividualMode;


    fun value(): String{
        return when(this){
            AutoMode -> StompHeader.AUTO
            ClientMode -> StompHeader.CLIENT
            ClientIndividualMode -> StompHeader.CLIENT_INDIVIDUAL
        }
    }
}