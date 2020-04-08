package com.github.ikovalyov

import com.github.ikovalyov.AutomataState.*
import com.github.ikovalyov.Input.ReceiveDataInput
import com.github.ikovalyov.Input.ValidateRequestInput
import com.github.ikovalyov.Input.ValidateResponse
import com.github.ikovalyov.Transition.ReceiveSearchDataTransition
import com.github.ikovalyov.Transition.ReceiveSuggestDataTransition
import com.github.ikovalyov.Transition.ValidateRequestTransition
import com.github.ikovalyov.Transition.ValidateSearchDataTransition
import org.junit.jupiter.api.Test
import kotlin.test.assertNotNull

class StateMachineTest {
    @Test
    fun stateMachineCanBeCreated() {
        val sm = StateMachine(emptyList(), emptyList(), emptyList())
        assertNotNull(sm)
    }

    @Test
    fun stateMachineTest() {
        val sm = StateMachine(
                availableInputs = listOf(ValidateRequestInput, ReceiveDataInput, ValidateResponse),
                states = listOf(
                        SearchRequestReceived::class,
                        SuggestRequestReceived::class,
                        RequestValidated::class,
                        SearchDataReceived::class,
                        SuggestDataReceived::class,
                        ResponseValidated::class
                ),
                transitions = listOf(
                        ValidateRequestTransition(),
                        ReceiveSearchDataTransition(),
                        ReceiveSuggestDataTransition(),
                        ValidateSearchDataTransition()
                )
        )

        val resultSearch = sm.processInputs(SearchRequestReceived("search request"), listOf(
                ValidateRequestInput, ReceiveDataInput, ValidateResponse
        ))
        require(resultSearch is ResponseValidated)
        assert(resultSearch.previousState.receivedSearchData.isNotEmpty())

        val resultSuggest = sm.processInputs(SuggestRequestReceived("suggest request"), listOf(
                ValidateRequestInput, ReceiveDataInput, ValidateResponse
        ))
        require(resultSuggest is ResponseValidated)
        assert(resultSuggest.previousState.receivedSearchData.isNotEmpty())
    }
}