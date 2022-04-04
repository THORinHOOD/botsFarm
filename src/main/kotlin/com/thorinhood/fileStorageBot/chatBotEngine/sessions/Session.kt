package com.thorinhood.fileStorageBot.chatBotEngine.sessions

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document("sessions")
class Session(@Id val chatId: Long,
              var token: String?,
              var cursor: Cursor,
              val fileTreeInfo: FileTreeInfo
)