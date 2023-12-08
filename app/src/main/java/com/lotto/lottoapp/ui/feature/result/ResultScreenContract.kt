package com.lotto.lottoapp.ui.feature.result

import com.lotto.lottoapp.model.response.tickets.UserTicketsResponse
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class ResultScreenContract {
    data class UserTicketsList(
        val ticketsResponse: UserTicketsResponse
    ): StateFlow<UserTicketsList> {

        private val _state = MutableStateFlow(this)
        override val replayCache: List<UserTicketsList>
            get() = _state.replayCache

        override suspend fun collect(collector: FlowCollector<UserTicketsList>): Nothing {
            _state.collect(collector)
        }

        override val value: UserTicketsList
            get() = _state.value
    }

    data class ScreenTicketsList(
        val createdAt: String,
        val numbers: String,
        val isWinner: Boolean
    ): StateFlow<ScreenTicketsList> {

        private val _state = MutableStateFlow(this)
        override val replayCache: List<ScreenTicketsList>
            get() = _state.replayCache

        override suspend fun collect(collector: FlowCollector<ScreenTicketsList>): Nothing {
            _state.collect(collector)
        }

        override val value: ScreenTicketsList
            get() = _state.value
    }

}