package com.github.ikovalyov.Transition

import com.github.ikovalyov.AutomataState.RequestValidated
import com.github.ikovalyov.AutomataState.SuggestDataReceived
import com.github.ikovalyov.AutomataState.SuggestRequestReceived
import com.github.ikovalyov.AutomataStateInterface
import com.github.ikovalyov.Input.ReceiveDataInput
import com.github.ikovalyov.InputInterface
import com.github.ikovalyov.TransitionAbstract

class ReceiveSuggestDataTransition: TransitionAbstract<SuggestDataReceived>(
        ReceiveDataInput, listOf(RequestValidated::class)
) {
    override fun canHandle(stateFrom: AutomataStateInterface, input: InputInterface): Boolean {
        val parentCheck = super.canHandle(stateFrom, input)
        if (!parentCheck) return parentCheck
        return checkIfClassesCanBeHandled(stateFrom as RequestValidated<*>)
    }

    private fun checkIfClassesCanBeHandled(stateFrom: RequestValidated<*>): Boolean {
        // In kotlin 1.4 we will be able use contracts to ensure types safety
        return stateFrom.previousState is SuggestRequestReceived
    }

    override fun performTransition(stateFrom: AutomataStateInterface): SuggestDataReceived {
        if (stateFrom !is RequestValidated<*> || !checkIfClassesCanBeHandled(stateFrom)) {
            throw IllegalArgumentException("Transition can't be applied to the $stateFrom state")
        }
        // Assume some calls to the data store were executed
        @Suppress("UNCHECKED_CAST") // Check performed in the checkIfClassesCanBeHandled fun
        return SuggestDataReceived(
                "search results",
                stateFrom as RequestValidated<SuggestRequestReceived>
        )
    }
}
