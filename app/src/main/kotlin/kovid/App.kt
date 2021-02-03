package kovid

class App {
    val greeting: String
        get() {
            return "Really Basic Setup!"
        }
}

fun main() {
    println(App().greeting)
}
