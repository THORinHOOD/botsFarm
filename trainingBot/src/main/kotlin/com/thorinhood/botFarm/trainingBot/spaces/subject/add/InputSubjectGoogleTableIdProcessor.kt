package com.thorinhood.botFarm.trainingBot.spaces.subject.add

import com.pengrad.telegrambot.model.Update
import com.thorinhood.botFarm.engine.data.services.SessionArgumentsDataService
import com.thorinhood.botFarm.engine.processors.BaseProcessor
import com.thorinhood.botFarm.engine.processors.Processor
import com.thorinhood.botFarm.engine.processors.data.ProcessResult
import com.thorinhood.botFarm.engine.processors.data.Session
import com.thorinhood.botFarm.engine.processors.data.Transition
import com.thorinhood.botFarm.trainingBot.statics.ArgKey
import com.thorinhood.botFarm.trainingBot.statics.ProcSpace
import com.thorinhood.botFarm.trainingBot.domain.Subject

@Processor
class InputSubjectGoogleTableIdProcessor(
    private val sessionArgumentsDataService: SessionArgumentsDataService
) : BaseProcessor(
    "input_google_table_id",
    ProcSpace.INPUT_SUBJECT_GOOGLE_TABLE_ID
) {
    override fun processInner(session: Session, update: Update): ProcessResult {
        val googleTableId = update.message()?.text() ?: throw Exception("Попробуй ещё раз")
        sessionArgumentsDataService.maintainWrap(session.sessionId) { args ->
            (args.get<Subject.Builder>(ArgKey.SUBJECT_BUILDER)).googleTableId(googleTableId)
        }
        return ProcessResult(
            null,
            Transition(
                ProcSpace.INPUT_SUBJECT_GOOGLE_TABLE_SHEET,
                "Отлично!\nА теперь пришли названия листа в таблице"
            )
        )
    }

    override fun isThisProcessorInner(session: Session, update: Update): Boolean =
        isNotCancel(update)
}