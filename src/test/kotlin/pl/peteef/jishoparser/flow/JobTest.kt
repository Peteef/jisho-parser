package pl.peteef.jishoparser.flow

import io.mockk.every
import io.mockk.junit5.MockKExtension
import io.mockk.mockkObject
import io.mockk.verify
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import pl.peteef.jishoparser.client.HttpClient
import pl.peteef.jishoparser.client.JlptLevel.N5
import pl.peteef.jishoparser.processing.ProcessingType.JSON_TO_FILE
import pl.peteef.jishoparser.processing.Processor
import pl.peteef.jishoparser.test.response
import pl.peteef.jishoparser.test.singleEntry

@ExtendWith(MockKExtension::class)
internal class JobTest {
    init {
        mockkObject(HttpClient)
        every { HttpClient.get(any()) }.returns(response)
        mockkObject(Processor)
    }

    @Test
    fun itShouldPerformJob() {
        val job = Job(SearchCriteria(setOf(N5)), ProcessingSettings("filename", JSON_TO_FILE))
        job.perform()
        verify(exactly = 1) { HttpClient.get(any()) }
        verify(exactly = 1) { Processor.process(singleEntry, JSON_TO_FILE, "filename") }
    }

    @Test
    fun itShouldThrowErrorWhenNotSupportedProcessType() {
        val job = Job(SearchCriteria(setOf(N5)), ProcessingSettings("filename", JSON_TO_FILE))
        every { Processor.supports(JSON_TO_FILE) }.returns(false)
        val exception = assertThrows(IllegalArgumentException::class.java) { job.perform() }
        assertNotNull(exception)
    }
}