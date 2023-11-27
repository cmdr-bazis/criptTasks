package Pract3

class Decoder : Cryptor() {
    override lateinit var PRS: RSAkeygen
    override var messageInitial = ArrayList<Char>()
    override var lettersDictionary = ArrayList<CoupleString>()
    override var convert = BinaryConvert()
    override var messageConverted: String = ""
    override var messageCrypted: String = ""
    override var letterBinarySize: Int = 0


    private fun findLetter(index: Int): Char {
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

    override fun cryption() {
        for (i in messageInitial.indices){
            if (((messageInitial[i] == '1') and (PRS.getPRS()[i] == '0')) or ((messageInitial[i] == '0') and (PRS.getPRS()[i] == '1'))){
                messageCrypted += '1'
            }
            else{
                messageCrypted += '0'
            }
        }
    }

    override fun convert() {
        var index = 0
        while (index <= messageCrypted.length - letterBinarySize){
            messageConverted += this.findLetter(index)
            index += letterBinarySize
        }
    }

    override fun menu() {
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
        this.cryption()
        this.convert()
    }

    override fun getEncodedMessage(): String {
        return this.messageConverted
    }

}