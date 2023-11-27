package Pract3

fun main(args: Array<String>) {
//    val keygen = RSAkeygen(273, 623, 7, 10, 3000)
//
//    keygen.createNextNumber()
//    keygen.checkPRS()

//    var encoder2 = Encoder() // /home/bazis/IdeaProjects/message.txt
//    encoder2.menu()
//    encoder2.printDictionary()
//    println(encoder2.getConvertedMessage())
//    println(encoder2.getGamma())
//    println(encoder2.getEncodedMessage())


    var decoder2 = Decoder() // /home/bazis/IdeaProjects/messageToDecrypt.txt
    decoder2.menu()
    println(decoder2.getEncodedMessage())
}