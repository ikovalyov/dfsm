package com.github.ikovalyov.AutomataState

import com.github.ikovalyov.AutomataStateInterface

data class ResponseValidated(
        val validationResult: Boolean,
        override val previousState: ResponseDataReceived
): AutomataStateInterface
