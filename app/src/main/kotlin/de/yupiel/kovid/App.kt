package de.yupiel.kovid

import com.github.doyaaaaaken.kotlincsv.client.KotlinCsvExperimental
import de.yupiel.kovid.gamedsl.*
import java.nio.file.Paths

@KotlinCsvExperimental
fun main() {
    val listOfGames: ArrayList<Game> =
        gameList {
            game {
                title = "Cyberpunk 2077"
                platform = Platform.GOG
            }
            game {
                title = "Death Stranding"
                platform = Platform.STEAM
            }
            game {
                title = "Metro Last Light Redux"
                platform = Platform.EPIC
            }
            game {
                title = "Sims 4"
                platform = Platform.ORIGIN
            }
        }

    val exporter = GamesExporter()

    println(listOfGames)

    exporter.exportAsCSV(listOfGames, Paths.get("./output/games.csv"), overwrite = true)
}
