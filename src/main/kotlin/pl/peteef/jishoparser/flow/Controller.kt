package pl.peteef.jishoparser.flow


object Controller {
    fun newJob(searchCriteria: SearchCriteria, processingSettings: ProcessingSettings): Job {
        return Job(searchCriteria, processingSettings)
    }
}