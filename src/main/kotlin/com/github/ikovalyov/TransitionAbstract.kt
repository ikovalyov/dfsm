package com.github.ikovalyov

import kotlin.reflect.KClass

abstract class TransitionAbstract<out StateTo: AutomataStateInterface>(
        private val input: InputInterface,
        private val applicableStates: List<KClass<out AutomataStateInterface>>
) {
    abstract fun performTransition(stateFrom: AutomataStateInterface): StateTo
    open fun canHandle(stateFrom: AutomataStateInterface, input: InputInterface): Boolean {
        return this.input == input && applicableStates.indexOf(stateFrom::class) > -1
    }
}
