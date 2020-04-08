package com.github.ikovalyov.Transition

import com.github.ikovalyov.AutomataState.ResponseValidated
import com.github.ikovalyov.AutomataState.SearchDataReceived
import com.github.ikovalyov.AutomataState.SuggestDataReceived
import com.github.ikovalyov.AutomataState.SuggestRequestReceived
import com.github.ikovalyov.AutomataStateInterface
import com.github.ikovalyov.Input.ValidateResponse
import com.github.ikovalyov.TransitionAbstract

class ValidateSearchDataTransition: TransitionAbstract<ResponseValidated>(
        ValidateResponse, listOf(SearchDataReceived::class, SuggestDataReceived::class)
) {
    override fun performTransition(stateFrom: AutomataStateInterface): ResponseValidated {
        return when (stateFrom) {
            is SearchDataReceived -> ResponseValidated(stateFrom.receivedSearchData.isNotEmpty(), stateFrom)
            is SuggestDataReceived -> ResponseValidated(stateFrom.receivedSearchData.isNotEmpty(), stateFrom)
            else -> throw IllegalArgumentException("Transition can't be applied to the $stateFrom state")
        }
    }
}
