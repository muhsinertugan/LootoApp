package com.lotto.lottoapp.ui.feature.result

import com.lotto.lottoapp.model.response.tickets.SingleTicketResponse
import com.lotto.lottoapp.model.response.tickets.UserTicketsResponse
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class ResultScreenContract {
    data class UserTicketsList(
        val ticketsResponse: UserTicketsResponse,
    ) : StateFlow<UserTicketsList> {

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
        val isWinner: Boolean,
    ) : StateFlow<ScreenTicketsList> {

        private val _state = MutableStateFlow(this)
        override val replayCache: List<ScreenTicketsList>
            get() = _state.replayCache

        override suspend fun collect(collector: FlowCollector<ScreenTicketsList>): Nothing {
            _state.collect(collector)
        }

        override val value: ScreenTicketsList
            get() = _state.value
    }

    data class SingleTicket(
        val ticket: SingleTicketResponse,
    ) : StateFlow<SingleTicket> {

        private val _state = MutableStateFlow(this)
        override val replayCache: List<SingleTicket>
            get() = _state.replayCache

        override suspend fun collect(collector: FlowCollector<SingleTicket>): Nothing {
            _state.collect(collector)
        }

        override val value: SingleTicket
            get() = _state.value
    }

    data class UserSingleTicketSearch(
        val search: String,
    ) : StateFlow<UserSingleTicketSearch> {

        private val _state = MutableStateFlow(this)
        override val replayCache: List<UserSingleTicketSearch>
            get() = _state.replayCache

        override suspend fun collect(collector: FlowCollector<UserSingleTicketSearch>): Nothing {
            _state.collect(collector)
        }

        override val value: UserSingleTicketSearch
            get() = _state.value
    }

    data class ErrorState(
        val code: Int,
        val message: String,
        val success: Boolean,
        val id: String,
    ) : StateFlow<ErrorState> {

        private val _state = MutableStateFlow(this)
        override val replayCache: List<ErrorState>
            get() = _state.replayCache

        override suspend fun collect(collector: FlowCollector<ErrorState>): Nothing {
            _state.collect(collector)
        }

        override val value: ErrorState
            get() = _state.value

    }

}