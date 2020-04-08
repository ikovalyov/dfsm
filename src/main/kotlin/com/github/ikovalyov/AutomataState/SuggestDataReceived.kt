package com.github.ikovalyov.AutomataState

data class SuggestDataReceived(
        override val receivedSearchData: String,
        override val previousState: RequestValidated<SuggestRequestReceived>
): ResponseDataReceived(receivedSearchData)
