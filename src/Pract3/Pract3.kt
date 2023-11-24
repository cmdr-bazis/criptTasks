package Pract3

fun main(args: Array<String>) {
//    val keygen = RSAkeygen(273, 623, 7, 10, 3000)
//
//    keygen.createNextNumber()
//    keygen.checkPRS()

    var encoder = Encoder()
    encoder.inputMessage("/home/bazis/IdeaProjects/message.txt")
    encoder.initializeParameters(273, 623, 7, 10)
    encoder.convertToBinary()
    encoder.encode()
    encoder.printEncodedMessage()

    var encoder2 = Encoder()
    encoder2.encoderInterface()
    println(encoder2.getEncodedMessage())
}