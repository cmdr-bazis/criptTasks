package Pract3

fun main(args: Array<String>) {
//
    var ch = readln()
    if (ch == "e"){
        var encoder = Encoder() // /home/bazis/IdeaProjects/messageToEncryptEN.txt  273 623 7 10 RU
        encoder.menu()
        println(encoder.getFinalMessage())
    }
    else if (ch == "d") {
        var decoder = Decoder() // /home/bazis/IdeaProjects/messageToDecryptEN.txt  273 623 7 10 RU
        decoder.menu()
        println(decoder.getFinalMessage())
    }
}