package Pract3

import java.nio.file.Files
import java.nio.file.Paths

class Encoder {
    private lateinit var PRS: RSAkeygen
    private var messageToEncode = ArrayList<Char>()
    private var alphabet = arrayOf(' ', 'А', 'Б', 'В', 'Г', 'Д', 'Е', 'Ж', 'З', 'И', 'К', 'Л', 'М', 'Н', 'О', 'П', 'Р', 'С', 'Т', 'У', 'Ф', 'Х', 'Ц', 'Ч', 'Ш', 'Щ', 'Ъ', 'Ы', 'Ь', 'Э', 'Ю', 'Я')
    private var lettersDictionary = ArrayList<CoupleString>()
    private var convert = BinaryConvert()
    private var messageBinary = ""
    private var messageEncoded = ""


    private fun fillLeft(string: String, number: Int): String {
        var stringOut = ""
        for (i in 0..<number - string.length){
            stringOut += "0"
        }
        stringOut += string
        return stringOut
    }

    private fun replaceLetters(){
        for (i in 0..<messageToEncode.size){
            if (messageToEncode[i] == 'Й'){
                messageToEncode[i] = 'И'
            }
        }
    }

    public fun initializeParameters(numP: Int, numQ: Int, numE: Int, firstNumber: Int){
        var binaryValueOut = ""
        var binaryValueTemp = ""
        PRS = RSAkeygen(numP, numQ, numE, firstNumber, this.messageToEncode.size * 5)
        PRS.createNextNumber()

        for (i in 0..<alphabet.size){
            binaryValueTemp = convert.convert(10, i.toLong(), 2)
            binaryValueOut = this.fillLeft(binaryValueTemp, 5)
            lettersDictionary.add(CoupleString(alphabet[i], binaryValueOut))
        }

    }

    public fun inputMessage(path: String){
        var lines = Files.readAllLines(Paths.get(path))
        var tempMessage = ""

        for (i in 0..<lines.size){
            tempMessage += lines[i]
        }

        for (i in 0..<tempMessage.length){
            messageToEncode.add(tempMessage[i])
        }
    }

    public fun convertToBinary(){
        this.replaceLetters()
        for (i in 0..<messageToEncode.size){
            for (j in 0..<lettersDictionary.size){
                if (messageToEncode[i] == lettersDictionary[j]._letterChar){
                    messageBinary += lettersDictionary[j]._binaryValue
                    break
                }
            }
        }
    }

    public fun printDictionary(){
        for (i in 0..<lettersDictionary.size){
            println()
            print(lettersDictionary[i]._letterChar)
            print(" ")
            print(lettersDictionary[i]._binaryValue)
        }
        println()
    }

    public fun encode(){
        println(PRS.getPRS().length)
        println(this.messageBinary.length)
        println(this.messageToEncode.size * 5)

        for (i in 0..<messageBinary.length){
            if (((PRS.getPRS()[i] == '0') and (messageBinary[i] == '1')) or ((PRS.getPRS()[i] == '1') and (messageBinary[i] == '0'))){
                messageEncoded += '1'
            }
            else{
                messageEncoded += '0'
            }
        }
    }

    public fun printEncodedMessage(){
        println(messageEncoded)
        println(PRS.getPRS())
        println(messageBinary)
    }

    public fun encoderInterface(){
        var stringPath = ""
        var key = ""
        var keyList = ArrayList<String>()

        println("Введите путь к сообщению: ")
        stringPath = readln()

        println("Введите ключ (четыре числа через пробел): ")
        key = readln()
        for (i in 0..<4){ keyList.add(key.split(" ")[i]) }

        this.inputMessage(stringPath)
        this.initializeParameters(keyList[0].toInt(), keyList[1].toInt(), keyList[2].toInt(), keyList[3].toInt())
        this.convertToBinary()
        this.encode()
    }

    public fun getEncodedMessage(): String {
        return this.messageEncoded
    }
}