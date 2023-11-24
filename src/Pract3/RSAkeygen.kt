package Pract3

import Pract1.Couple
import kotlin.math.abs

class RSAkeygen {
    private var numP = 2735
    private var numQ = 6235
    private var numE = 7
    private var convert = BinaryConvert()
    private var PRSFinalOut: String = ""
    private var firstNumber = 10
    private var range = 1500

    constructor(numP: Int, numQ: Int, numE: Int, firstNumber: Int, range: Int){
        this.numP = numP
        this.numQ = numQ
        this.numE = numE
        this.firstNumber = firstNumber
        this.range = range
    }

    constructor(firstNumber: Int, range: Int){
        this.firstNumber = firstNumber
        this.range = range
    }

    public fun createNextNumber(){
        var index = 0
        var nextNumber: Long = firstNumber.toLong()
        var stringOut = ""
        var charCord = ' '
        var tempStringConvert = ""
        for (i in 0..<range){
            nextNumber = abs(convert.pow(nextNumber, this.numE) % (numP * numQ))
            tempStringConvert = convert.convert(10, nextNumber, 2)

            try {
                charCord = tempStringConvert[tempStringConvert.length - 1]
            }
            catch (e: StringIndexOutOfBoundsException){
                print("ERROR (Symbols amount: $index) ")
                break
            }
            finally {
                stringOut += charCord
                index += 1
            }
        }
        this.PRSFinalOut = stringOut
    }

    public fun getPRS(): String {
        return this.PRSFinalOut
    }

    public fun checkPRS(){
        print("PSQ String: ")
        println(this.getPRS())

        var nGramList = ArrayList<Couple>()
        var nGramListOut = ArrayList<Int>()
        var numZero = 0
        var numOne = 0
        var compareFlag = true
        var tempNGram = ""
        var tempNGramNumber = 0
        var currentSize = 3

        for (i in 0..<this.PRSFinalOut.length){
            if (this.PRSFinalOut[i] == '0'){ numZero += 1 }
            else if (this.PRSFinalOut[i] == '1'){ numOne += 1 }
        }

        for (i in 4..1023){
            nGramList.add(Couple(convert.convert(10, i.toLong(), 2), 0))
        }

        for (i in 0..<nGramList.size){
            for (j in 0..<this.PRSFinalOut.length - 9){
                compareFlag = true
                for (k in 0..<nGramList[i]._stringValue.length){
                    if (nGramList[i]._stringValue[k] != this.PRSFinalOut[j + k]){
                        compareFlag = false
                        break
                    }
                }
                if (compareFlag){
                    nGramList[i]._intValue += 1
                }
            }
        }

        println()

        for (i in 0..<nGramList.size){
            if (currentSize == nGramList[i]._stringValue.length){
                nGramListOut.add(nGramList[i]._intValue)
            }
            else{
                print("$currentSize: ")
                for (j in 0..<nGramListOut.size){
                    print(nGramListOut[j])
                    print(", ")
                }
                nGramListOut.clear()
                currentSize += 1
                println()
            }
//            tempNGram = nGramList[i]._stringValue
//            tempNGramNumber = nGramList[i]._intValue
//            println("$tempNGram: $tempNGramNumber")
        }

        println("Number of 0's: $numZero")
        println("Number of 1's: $numOne")
    }
}