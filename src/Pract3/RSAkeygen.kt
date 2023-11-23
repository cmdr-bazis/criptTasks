package Pract3

import Pract1.Couple
import kotlin.math.abs

class RSAkeygen {
    private var numP = 2735
    private var numQ = 6235
    private var numE = 7
    private var convert = BinaryConvert()
    private var PRSFinalOut: String = ""

    private fun findGCD(numA: Int, numB: Int): Int {
        var _numA: Int
        var _numB: Int
        var remaider: Int

        if (numA > numB){
            _numA = numA
            _numB = numB
        }
        else{
            _numB = numA
            _numA = numB
        }

        remaider = _numA % _numB
        while (remaider != 0){
            remaider = _numA % remaider
            _numA = remaider
        }
        return remaider
    }

    public fun createNextNumber(firstNumber: Int, range: Int){
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
        var currentSize = 2


        var nGramList = ArrayList<Couple>()

        var numZero = 0
        var numOne = 0
        for (i in 0..<this.PRSFinalOut.length){
            if (this.PRSFinalOut[i] == '0'){ numZero += 1 }
            else if (this.PRSFinalOut[i] == '1'){ numOne += 1 }
        }

        for (i in 3..1023){
            nGramList.add(Couple(convert.convert(10, i.toLong(), 2), 0))
        }

        for (i in 0..<nGramList.size){
            print(nGramList[i]._stringValue)
            print(" ")
        }

        for (i in 0..<this.PRSFinalOut.length){
            for (j in 0..<nGramList.size){
                if ()
            }
        }

        println("Number of 0's: $numZero")
        println("Number of 1's: $numOne")
    }
}