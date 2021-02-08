package mastermind

data class Evaluation(val rightPosition: Int, val wrongPosition: Int)

fun evaluateGuess(secret: String, guess: String): Evaluation {
    if(secret == guess) return Evaluation(rightPosition = 4,wrongPosition = 0)
    return Evaluation(0,0)
}

fun main(){
    println(evaluateGuess("ABCD", "ABCD"))
}
