package domain

class Car(val carName: String, private val driver: Driver) {

    var distance = BLANK
        private set

    init {
        require(carName.length in RANGE) { NAME_CONVENTION_ERROR_MESSAGE }
    }

    fun move() {
        val decision = driver.decideMovement()
        distance += decision.distance
    }

    companion object {
        private const val BLANK = ""
        const val NAME_CONVENTION_ERROR_MESSAGE = "[ERROR] 이름은 1~5글자여야합니다."
        private const val NAME_LENGTH_LOWER_INCLUSIVE = 1
        private const val NAME_LENGTH_UPPER_INCLUSIVE = 5
        val RANGE = NAME_LENGTH_LOWER_INCLUSIVE..NAME_LENGTH_UPPER_INCLUSIVE
    }
}
