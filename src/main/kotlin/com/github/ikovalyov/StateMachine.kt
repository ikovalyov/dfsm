package com.github.ikovalyov

import kotlin.reflect.KClass

class StateMachine(
        private val availableInputs: List<InputInterface>,
        private val states: List<KClass<out AutomataStateInterface>>,
        private val transitions: List<TransitionAbstract<*>>
) {
    private fun getAvailableTransition(state: AutomataStateInterface, input: InputInterface): TransitionAbstract<*> {
        val availableTransitions = transitions.filter {
            it.canHandle(state, input)
        }
        if (availableTransitions.count() > 1) {
            throw IllegalStateException("More then one transition found for $state and $input")
        }
        if (availableTransitions.count() == 0) {
            throw IllegalStateException("No transitions found for $state and $input")
        }
        return availableTransitions.first()
    }

    fun processInputs(initialState: AutomataStateInterface, inputs: List<InputInterface>) : AutomataStateInterface {
        var state = initialState
        if (!availableInputs.containsAll(inputs)) {
            throw IllegalStateException("$inputs has unknown input")
        }

        inputs.forEach {
            val transition = getAvailableTransition(state, it)
            state = transition.performTransition(state)
        }
        return state
    }
}
