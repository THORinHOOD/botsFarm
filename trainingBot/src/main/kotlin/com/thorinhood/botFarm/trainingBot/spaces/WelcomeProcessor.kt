package com.thorinhood.botFarm.trainingBot.spaces

import com.pengrad.telegrambot.model.Update
import com.thorinhood.botFarm.engine.processors.BaseProcessor
import com.thorinhood.botFarm.engine.processors.Processor
import com.thorinhood.botFarm.engine.processors.data.ProcessResult
import com.thorinhood.botFarm.engine.processors.data.Transition
import com.thorinhood.botFarm.engine.sessions.Session

@Processor
class WelcomeProcessor : BaseProcessor(
    "welcome",
    ""
) {
    override fun processInner(session: Session<Long>, update: Update): ProcessResult =
        ProcessResult(
            null,
            Transition(
                "input_google_table_id",
                "Привет!\nПришли пожалуйста id google таблицы"
            )
        )

    override fun isThisProcessorInner(session: Session<Long>, update: Update): Boolean =
        isUpdateMessageEqualsLabel(update, LABEL)

    companion object {
        private val LABEL = "/start"
    }
}