package Pract1

class DecryptInt {
    private var message = "ВИБЧВЗНЯЕНМЦДСЧЧНЧЯВЗЭТЦБЩИЯВЩТНБЖУЯЕНЮЭЦНЧДЧЯЕНФНЧЫЖПЕЙЗВЭЦНАЯЕНБПШБЧЯЕНСЗСБИСЗСЯЕНИМЩНАНЧФБЖУЕЭЗББЗБЩСЧНЧЙЖУЯЕНРЧНАТСЧЭАСЧБИЫПЮАЕЙИЭИФБЖУТЙЧСНФСЕЧЯВЧЭАНЩШЩВЗТТЕЙЧТИНАЯЕНСЗСШЩМЧШЩНПЕЙЗВЭЦНАФЩЭЧВЩСЯЕНЩТЭИЧББЩНЧЭАСЧЭИКЩБВЧЯМЧШБЧТНИТЧТНЗВИНАСЗСЧУБИЫПЮАЕЭЗБРЧНЦЫЖБЗТМЩРЧНВЧЙБЧСЧЙЧНСИУТЙЧСЯЕНБПЯЕНЭЩНЯЕНТСЗЩЩМЯЕНВНЖТЦФПЯЕНБЧБЩМЧЩЩНЙПФЗНАТЦЮЗЩЩЦЯЗТВЧУТЧЫТНВЩББЖУЯЗВНЙЗКБИУЮЩБАИЯЕНВТЗМЧМЮЩЭЩЯЕННПНБЩИЯВЩТНБЖУЕЧВЩЙБПЭТЦСЫЩЙЭИЧЯПЯЕНВЧЧЫЙЗЯИНЩЯЕНФНЧВЖЯЕНБЗЕЙИМЩЙЯЕНБЗФБЩНЩ"


    private val arrayAlphabet = arrayOf('А', 'Б', 'В', 'Г', 'Д', 'Е', 'Ж', 'З', 'И', 'Й', 'К', 'Л', 'М', 'Н', 'О', 'П', 'Р',
        'С', 'Т', 'У', 'Ф', 'Х', 'Ц', 'Ч', 'Ш', 'Щ', 'Ъ', 'Ы', 'Ь', 'Э', 'Ю', 'Я')

    private val alphabetStat = "ОЕАИНТСРВЛЛКМДПУЯФЬГЗБЧЙХЖШЮЦЩЭФЪ"

    private var arrayInt = Array<Int>(32) {0}

    private var messageArray = ArrayList<String>()


    fun sortArray(arrayCouple: ArrayList<Couple>): ArrayList<Couple> {
        for (i in 0..<arrayCouple.size){
            for (j in i..<arrayCouple.size){
                if (arrayCouple[i].getInt() < arrayCouple[j].getInt()){
                    var temp = arrayCouple[j]
                    arrayCouple[j] = arrayCouple[i]
                    arrayCouple[i] = temp
                }
            }
        }
        return arrayCouple
    }

    fun locateWord(string: String, arrayCouple: ArrayList<Couple>): Int {
        for (i in 0..<arrayCouple.size){
            if (string == arrayCouple[i].getString()){
                return i
            }
        }
        return -1
    }

    fun printArray(arrayCouple: ArrayList<Couple>){
        for (i in 0..<arrayCouple.size){
            if (arrayCouple[i].getInt() > 1){
                print(arrayCouple[i].getString())
                print(" ")
                print(arrayCouple[i].getInt())
                println()
            }
        }
    }

    fun deleteWhitespaces(string: String): String {
        var finString: String = ""
        for (i in 0..<string.length){
            if (string[i] != ' '){
                finString += string[i]
            }
        }
        return finString
    }

    fun getLetters(arrayLetters: ArrayList<Couple>){
        var percent = 0.00
        for (i in 0..<arrayLetters.size){
            print(arrayLetters[i]._stringValue)
            print(" ")
            print(arrayLetters[i]._intValue)
            print(" ")

            percent = (arrayLetters[i]._intValue.toDouble() / message.length.toDouble())
            percent = Math.round(percent * 1000.0) / 1000.0
            percent = percent * 100
            print(percent)

            println()
        }
    }

    fun getStat(message: String){
        var message: String = this.deleteWhitespaces(message)

        //добавление количества каждой бкувы в список
        for(i in 0..<message.length){
            for (j in 0..<arrayAlphabet.size){
                if (message[i] == arrayAlphabet[j]){
                    arrayInt[j] += 1
                }
            }
        }

        //заполнение списка couple буквами
        var arrayLetters = ArrayList<Couple>()
        for (i in 0..<arrayAlphabet.size){
            arrayLetters.add(Couple(arrayAlphabet[i].toString(), 0))
        }

        for (i in 0..<message.length){
            for (j in 0..<arrayLetters.size){
                if (message[i].toString() == arrayLetters[j]._stringValue){
                    arrayLetters[j]._intValue += 1
                }
            }
        }

        //удалить запятые из статистики
//    for (i in 0..<arrayLetters.size){
//        if ((arrayLetters[i]._stringValue == "Я") or (arrayLetters[i]._stringValue == "Е") or (arrayLetters[i]._stringValue == "Н")){
//            arrayLetters[i]._intValue -= 20
//        }
//    }



        //поиск и добавление знаков препинания
        var arrayPunct = ArrayList<Couple>()

        var tempString = ""
        for (i in 3..<message.length){
            tempString = message.substring(i - 3, i)
            if (locateWord(tempString, arrayPunct) >= 0){
                arrayPunct[locateWord(tempString, arrayPunct)]._intValue += 1
            }
            else{
                arrayPunct.add(Couple(tempString, 1))
            }
        }
        println(" ")

        //сортировка возможных знаков препинания
        var arrayPunctSorted = sortArray(arrayPunct)
        printArray(arrayPunctSorted)

        for (i in 0..<message.length){
            messageArray.add(message[i].toString())
        }

        for (i in 0..<arrayLetters.size){
            if (arrayLetters[i].getString() == arrayPunctSorted[0].getString()[0].toString()){
                arrayLetters[i]._intValue -= arrayPunctSorted[0].getInt()
            }
            else if (arrayLetters[i].getString() == arrayPunctSorted[0].getString()[1].toString()){
                arrayLetters[i]._intValue -= arrayPunctSorted[0].getInt()
            }
            else if (arrayLetters[i].getString() == arrayPunctSorted[0].getString()[2].toString()){
                arrayLetters[i]._intValue -= arrayPunctSorted[0].getInt()
            }
        }

        var arrayLettersSorted: ArrayList<Couple> = sortArray(arrayLetters)
        getLetters(arrayLettersSorted)

        for (i in 2..<message.length){
            if ((message[i - 2] == arrayPunctSorted[0].getString()[0]) and (message[i - 1] == arrayPunctSorted[0].getString()[1]) and (message[i] == arrayPunctSorted[0].getString()[2])){
                messageArray[i - 2] = " "
                messageArray[i - 1] = ","
                messageArray[i] = " "
            }
    //        if ((message[i - 2] == 'Н') and (message[i - 1] == 'Ф') and (message[i] == 'C')){
    //            messageArray[i - 2] = " "
    //            messageArray[i - 1] = "."
    //            messageArray[i] = " "
    //        }
        }


        var messageOut = ""
        for (i in 0..<messageArray.size){
            if (messageArray[i] == " "){ messageOut += " "}
            else if(messageArray[i] == ","){ messageOut += "," }
//            else if(messageArray[i] == "."){ messageOut += "."}
//            else if(messageArray[i] == "Я"){ messageOut += "з"}
//            else if(messageArray[i] == "Е"){ messageOut += "п"}
//            else if(messageArray[i] == "Н"){ messageOut += "т"}
//
//            else if(messageArray[i] == "Н"){ messageOut += "т"}
//            else if(messageArray[i] == "Ф"){ messageOut += "ч"}
//            else if(messageArray[i] == "С"){ messageOut += "к"}
//
//            else if(messageArray[i] == "Ч"){ messageOut += "о"}
//            else if(messageArray[i] == "Щ"){ messageOut += "е"}
//            else if(messageArray[i] == "Н"){ messageOut += "т"}
//
//            else if(messageArray[i] == "Й"){ messageOut += "р"}
//            else if(messageArray[i] == "Ж"){ messageOut += "ы"}
//            else if(messageArray[i] == "В"){ messageOut += "в"}
//            else if(messageArray[i] == "А"){ messageOut += "ь"}
//            else if(messageArray[i] == "Э"){ messageOut += "л"}
//            else if(messageArray[i] == "Т"){ messageOut += "c"}
//            else if(messageArray[i] == "И"){ messageOut += "и"}
//            else if(messageArray[i] == "Б"){ messageOut += "н"}
//            else if(messageArray[i] == "У"){ messageOut += "й"}
//            else if(messageArray[i] == "З"){ messageOut += "а"}

            else{
//                messageOut += " "
                for (j in 0..<arrayLettersSorted.size){
                    if (messageArray[i] == arrayLettersSorted[j]._stringValue){
                        messageOut += alphabetStat[j]
                        break
                    }
                }
            }
        }

        println(messageOut)

        //правильный ответ: –Виноват, – мягко отозвался неизвестный, – для того, чтобы управлять, нужно, как-никак, иметь точный план на некоторый, хоть сколько-нибудь приличный срок. Позвольте же вас спросить, как же может управлять человек, если он не только лишен возможности составить какой-нибудь план хотя бы на смехотворно короткий срок, ну, лет, скажем, в тысячу, но не может ручаться даже за свой собственный завтрашний день?

        for (i in 0..<messageArray.size){
            print(messageArray[i])
        }
    }
}