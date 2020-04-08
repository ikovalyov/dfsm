package com.github.ikovalyov.AutomataState

import com.github.ikovalyov.AutomataStateInterface

data class SearchRequestReceived(val searchTerm: String): AutomataStateInterface {
    override val previousState: AutomataStateInterface? = null
}
