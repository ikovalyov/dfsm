package com.github.ikovalyov.AutomataState

import com.github.ikovalyov.AutomataStateInterface

data class RequestValidated<T: AutomataStateInterface>(
        val validationResult: Boolean,
        override val previousState: T
): AutomataStateInterface
