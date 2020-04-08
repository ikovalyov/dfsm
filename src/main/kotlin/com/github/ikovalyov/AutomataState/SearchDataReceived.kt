package com.github.ikovalyov.AutomataState

data class SearchDataReceived(
        override val receivedSearchData: String,
        override val previousState: RequestValidated<SearchRequestReceived>
): ResponseDataReceived(receivedSearchData)
