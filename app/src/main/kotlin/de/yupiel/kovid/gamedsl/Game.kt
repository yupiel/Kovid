package de.yupiel.kovid.gamedsl

data class Game(var title: String = "", var platform: Platform = Platform.UNKNOWN)

enum class Platform {
    STEAM, ORIGIN, GOG, EPIC, UNKNOWN
}
