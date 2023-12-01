package Pract3

import java.nio.file.Files
import java.nio.file.Paths

abstract class Cryptor {
    protected abstract var PRS: RSAkeygen
    protected abstract var messageInitial: ArrayList<Char> //Original message
    protected var alphabetRussian: Array<Char> = arrayOf(' ', 'А', 'Б', 'В', 'Г', 'Д', 'Е', 'Ж', 'З', 'И', 'К', 'Л', 'М', 'Н', 'О', 'П', 'Р', 'С', 'Т', 'У', 'Ф', 'Х', 'Ц', 'Ч', 'Ш', 'Щ', 'Ъ', 'Ы', 'Ь', 'Э', 'Ю', 'Я')
    protected var alphabetEnglish: Array<Char> = arrayOf(' ', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z')
    protected lateinit var alphabet: Array<Char>
    protected abstract var lettersDictionary: ArrayList<CoupleString>
    protected abstract var convert: BinaryConvert
    protected abstract var messageConverted: String //Message converted to binary state
    protected abstract var messageCrypted: String //Final message
    protected abstract var letterBinarySize: Int //Max size of binary value of letter in alphabet, used for dictionary filling

    protected fun findLetter(index: Int): Char {
        var substringLetter = messageCrypted.substring(index..<index + letterBinarySize)
        var finalLetter = ' '
        for (i in 0..<lettersDictionary.size){
            if (substringLetter == lettersDictionary[i]._binaryValue){
                finalLetter = lettersDictionary[i]._letterChar
                return finalLetter
            }
            else{
                finalLetter = ' '
            }
        }
        return finalLetter
    }

    protected fun fillLeft(string: String, number: Int): String {
        var stringOut = ""
        for (i in 0..<number - string.length){
            stringOut += "0"
        }
        stringOut += string
        return stringOut
    }

    protected fun replaceLetters(){
        for (i in 0..<messageInitial.size){
            if (messageInitial[i] == 'Й'){
                messageInitial[i] = 'И'
            }
        }
    }

    protected fun setLetterBinarySize(){
        for (i in alphabet.indices){
            letterBinarySize = convert.convert(10, i.toLong(), 2).length
        }
    }

    protected fun initializeParameters(numP: Int, numQ: Int, numE: Int, firstNumber: Int, language: String){
        var binaryValueOut = ""
        var binaryValueTemp = ""
        var languageString: String = ""

        when (language){ //Defining of what language will be used for message
            "RU" -> this.alphabet = this.alphabetRussian
            "EN" -> this.alphabet = this.alphabetEnglish
        }

        this.setLetterBinarySize()
        PRS = RSAkeygen(numP, numQ, numE, firstNumber, this.messageInitial.size * letterBinarySize)
        PRS.createNextNumber()

        for (i in alphabet.indices){
            binaryValueTemp = convert.convert(10, i.toLong(), 2)
            binaryValueOut = this.fillLeft(binaryValueTemp, letterBinarySize)
            lettersDictionary.add(CoupleString(alphabet[i], binaryValueOut))
        }
    }

    protected fun inputMessage(path: String){
        var lines = Files.readAllLines(Paths.get(path))
        var tempMessage = ""

        for (i in 0..<lines.size){
            tempMessage += lines[i]
        }

        for (i in 0..<tempMessage.length){
            messageInitial.add(tempMessage[i])
        }
    }

    protected abstract fun convert()
    protected abstract fun cryption()

    public fun printDictionary(){
        for (i in 0..<lettersDictionary.size){
            println()
            print(lettersDictionary[i]._letterChar)
            print(" ")
            print(lettersDictionary[i]._binaryValue)
        }
        println()
    }

    public open fun menu(){
        var stringPath = ""
        var key = ""
        var keyList = ArrayList<String>()

        println("Введите путь к сообщению: ")
        stringPath = readln()

        println("Введите ключ (четыре числа через пробел): ")
        key = readln()
        for (i in 0..<5){ keyList.add(key.split(" ")[i]) }

        this.inputMessage(stringPath)
        this.initializeParameters(keyList[0].toInt(), keyList[1].toInt(), keyList[2].toInt(), keyList[3].toInt(), keyList[4])
        this.convert()
        this.cryption()
    }

    public open fun getFinalMessage(): String {
        return this.messageCrypted
    }

    public fun getConvertedMessage(): String {
        return this.messageConverted
    }

    public fun getGamma(): String {
        return this.PRS.getPRS()
    }
}