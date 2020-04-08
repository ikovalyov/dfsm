package com.github.ikovalyov.Transition

import com.github.ikovalyov.AutomataState.RequestValidated
import com.github.ikovalyov.AutomataState.SearchRequestReceived
import com.github.ikovalyov.AutomataState.SuggestRequestReceived
import com.github.ikovalyov.AutomataStateInterface
import com.github.ikovalyov.Input.ValidateRequestInput
import com.github.ikovalyov.TransitionAbstract

class ValidateRequestTransition: TransitionAbstract<RequestValidated<*>>(
        ValidateRequestInput,
        listOf(SearchRequestReceived::class, SuggestRequestReceived::class)
) {
    private fun checkTerm(term: String) : Boolean {
        return term.isNotEmpty()
    }

    override fun performTransition(stateFrom: AutomataStateInterface): RequestValidated<*> {
        return when(stateFrom) {
            is SearchRequestReceived -> {
                val searchTerm = stateFrom.searchTerm
                RequestValidated(checkTerm(searchTerm), stateFrom)
            }
            is SuggestRequestReceived -> {
                val searchTerm = stateFrom.searchTerm
                RequestValidated(checkTerm(searchTerm), stateFrom)
            }
            else -> throw IllegalArgumentException("Transition can't be applied to the $stateFrom state")
        }
    }
}
