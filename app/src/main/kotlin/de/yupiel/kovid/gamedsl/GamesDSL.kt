package de.yupiel.kovid.gamedsl

@GamesDSLMarker
class GameBuilder {
    var title: String = ""
    var platform: Platform = Platform.UNKNOWN

    fun build(): Game = Game(title, platform)
}

@GamesDSLMarker
class GamesList: ArrayList<Game>() {
    fun game(gameBuilder: GameBuilder.() -> Unit) {
        add(GameBuilder().apply(gameBuilder).build())
    }
}
fun gameList(games: GamesList.() -> Unit): ArrayList<Game> =
    GamesList().apply(games)

@DslMarker  // Avoid initialization of Game objects within game objects in the DSL
annotation class GamesDSLMarker
