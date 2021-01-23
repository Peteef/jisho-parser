package pl.peteef.jishoparser

import com.xenomachina.argparser.mainBody
import pl.peteef.jishoparser.cli.ArgumentParser.parseArguments
import pl.peteef.jishoparser.cli.Arguments
import pl.peteef.jishoparser.flow.Controller
import pl.peteef.jishoparser.flow.ProcessingSettings
import pl.peteef.jishoparser.flow.SearchCriteria

object App {
    @JvmStatic
    fun main(args: Array<String>) = mainBody {
        val arguments = parseArguments(args)
        run(arguments)
    }

    private fun run(arguments: Arguments) {
        println("Processing...")
        val searchCriteria = SearchCriteria(arguments.jlptLevels)
        val processingSettings = ProcessingSettings(arguments.filename, arguments.processingType, arguments.withRomaji)
        Controller.newJob(searchCriteria, processingSettings).perform()
        println("Done.")
    }
}