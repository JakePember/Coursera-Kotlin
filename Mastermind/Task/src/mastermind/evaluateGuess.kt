package mastermind

data class Evaluation(val rightPosition: Int, val wrongPosition: Int)

/*
Takes a list and finds all the duplicates and converts it to a map. The key being the char, and the
value being the amount of occurrences of its respective character
 */
fun MutableList<Char>.consolidate(): MutableMap<Char, Int>{
    var resultHM: MutableMap<Char, Int> = HashMap()

    for(secretItem in this){
        if(resultHM.containsKey(secretItem)){ //If we have an entry already, add to it
            resultHM[secretItem] = resultHM.getValue(secretItem) + 1
        } else{ //add a new char item to map
            resultHM[secretItem] = 1
        }
    }
    return resultHM
}

/*
Compares 2 maps, finds the duplicate key entries and determines which entry is smaller. This is done
for all duplicate keys, unique keys are ignored. The value of all the common smallest values are added
together then returned as an integer
 */
fun sumOfSmallestDuplicateValues(secret: MutableMap<Char, Int>, guess: MutableMap<Char, Int>): Int {
    var result = 0
    for(item in secret){
        if(guess.containsKey(item.key)){ //if the character exists in both guess and secret
            result += if(item.value <= guess.getValue(item.key)){ //secret is smaller
                item.value
            } else { //guess is smaller
                guess.getValue(item.key)
            }
        }
    }
    return result
}

/*
Evaluates the users guess against the actual values of the secret
 */
fun evaluateGuess(secret: String, guess: String): Evaluation {
    var mSecret = secret.toMutableList()
    var mGuess = guess.toMutableList()

    var correctCount = 0

    if(secret == guess) return Evaluation(rightPosition = 4,wrongPosition = 0)

    //get correctCount
    for(x in 0 until 4){
        if(secret[x] == guess[x]){
            correctCount++
            mSecret.remove(guess[x])
            mGuess.remove(guess[x])
        }
    }

    val hmSecret = mSecret.consolidate()
    val hmGuess = mGuess.consolidate()

    val incorrectCount = sumOfSmallestDuplicateValues(hmSecret, hmGuess)

    return Evaluation(correctCount, incorrectCount)
}
