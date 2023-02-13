import domain.Car
import domain.Driver
import domain.RandomNumberGenerator
import domain.Referee
import view.InputView
import view.OutputView

private const val EXCEPTION_NULL = "[ERROR] exception 메시지가 null입니다."

class GameController() {
    private val inputView = InputView()
    private val outputView = OutputView()
    private var cars: List<Car> = emptyList()
    private var tryCount: Int = 0

    fun startGame() {
        val driver = Driver(RandomNumberGenerator())
        cars = getValidCar(driver)
        tryCount = getValidTryCount()
        playInGame()
    }

    private fun playInGame() {
        outputView.printResultMessage()
        playRace()
        endGame()
    }

    private fun endGame() {
        val winnersResult = Referee.judgeWinner(cars)
        outputView.printWinner(winnersResult)
    }

    private fun getValidTryCount(): Int {
        return try {
            enterTryCount()
        } catch (e: IllegalArgumentException) {
            outputView.printErrorMessage(e.message ?: EXCEPTION_NULL)
            getValidTryCount()
        }
    }

    private fun getValidCar(driver: Driver): List<Car> {
        return try {
            val carNames = enterCarName()
            carNames.map { Car(it, driver) }
        } catch (e: IllegalArgumentException) {
            outputView.printErrorMessage(e.message ?: EXCEPTION_NULL)
            getValidCar(driver)
        }
    }

    private fun playRace() {
        repeat(tryCount) {
            moveAllCar()
            outputView.printResult(cars)
        }
    }

    private fun moveAllCar() {
        cars.forEach {
            it.move()
        }
    }

    private fun enterTryCount(): Int {
        outputView.printEnterTryCount()
        tryCount = inputView.enterTryCount()
        return tryCount
    }

    private fun enterCarName(): List<String> {
        outputView.printEnterCarName()
        return inputView.enterCarName()
    }
}
