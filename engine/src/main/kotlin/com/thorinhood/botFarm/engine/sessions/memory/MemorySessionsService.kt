package com.thorinhood.botFarm.engine.sessions.memory

import com.pengrad.telegrambot.model.Update
import com.thorinhood.botFarm.engine.sessions.Session
import com.thorinhood.botFarm.engine.sessions.SessionsService
import org.apache.logging.log4j.kotlin.Logging

class MemorySessionsService(
    private val procSpaceInit: String
) : SessionsService, Logging {

    private val allSessions: MutableMap<Long, MemorySession<Long>> = mutableMapOf()

    override fun getSession(update: Update): Session<Long> {
        val sessionId = extractSessionId(update)
        return getSession(sessionId)
    }

    override fun getSession(sessionId: Long): Session<Long> {
        return allSessions.computeIfAbsent(sessionId) {
            MemorySession(
                sessionId,
                procSpaceInit,
                mutableMapOf()
            )
        }
    }

    override fun updateSession(session: Session<Long>) { }

    override fun getAllSessions(): List<Session<Long>> = allSessions.values.toList()

}