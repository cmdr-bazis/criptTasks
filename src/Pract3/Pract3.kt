package Pract3

fun main(args: Array<String>) {
//    var convert1 = BinaryConvert()
//    var num1 = 347369432
//    var base1 = 10
//    var base2 = 2
//
//    print(convert1.convert(base1, num1, base2))

    var keygen = RSAkeygen()
//    for (i in 1..30){
//        print("$i: ")
//        keygen.createNextNumber(i, 10, 1500)
//        keygen.checkPRS()
//        println()
//    }
    keygen.createNextNumber(10, 2000)
    keygen.checkPRS()
}