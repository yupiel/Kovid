package de.yupiel.kovid.gamedsl

import com.github.doyaaaaaken.kotlincsv.client.KotlinCsvExperimental
import com.github.doyaaaaaken.kotlincsv.dsl.csvWriter
import jdk.jshell.spi.ExecutionControl
import java.io.File
import java.nio.file.Path

class GamesExporter() {
    @KotlinCsvExperimental
    fun exportAsCSV(gamesList: ArrayList<Game>, savePath: Path, overwrite: Boolean = false) {
        if(overwrite && File(savePath.toUri()).exists()){
            File(savePath.toUri()).delete()
        }

        val writer = csvWriter().openAndGetRawWriter(savePath.toFile())
        writer.writeRow(listOf("Title", "Platform"))

        gamesList.forEach {
            writer.writeRow(listOf(it.title, it.platform))
        }
    }

    fun exportAsJSON(gamesList: ArrayList<Game>, savePath: Path){
        throw ExecutionControl.NotImplementedException("exportAsJSON has not been implemented yet");
    }
}
