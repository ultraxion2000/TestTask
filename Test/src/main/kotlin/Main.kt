import kotlinx.coroutines.*


suspend fun main(args: Array<String>)= coroutineScope{

    launch{ Work1()}

    val exit: Job = launch{ Work2() }

    launch { Work3(exit) }



    Unit
}


suspend fun Work1() {
    repeat(Int.MAX_VALUE) {
        delay(60000L)
        println("Прошла одна минута")
    }
}
suspend fun Work2() {
        repeat(Int.MAX_VALUE) {
            delay(300000L)
            println("Прошло 5 минут и тебе пора меня выключать")
        }
    }

suspend fun Work3(exit: Job) {
    repeat(Int.MAX_VALUE) {
        delay(300010L)
        println("Продолжить выполнение Work2 y/n ?: ")
        var age: String? = null
        while (age !== "y" || age !== "n") {
            val age = readLine()
            if (age == "n") {
                exit.cancel()    // отменяем корутину
                exit.join()      // ожидаем завершения корутины
                println("Работа Work2 завершена")

            } else if (age == "y") {
                exit.start()
            } else {
                println("Некорректный ввод")
            }
        }
    }
}

