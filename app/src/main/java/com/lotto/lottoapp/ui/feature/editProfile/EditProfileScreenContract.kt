package com.lotto.lottoapp.ui.feature.editProfile

import com.lotto.lottoapp.model.response.profile.User
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class EditProfileScreenContract {
    data class UserState(
        val user: User
    ) : StateFlow<UserState> {
        private val _state = MutableStateFlow(this)
        override val replayCache: List<UserState>
            get() = _state.replayCache

        override suspend fun collect(collector: FlowCollector<UserState>): Nothing {
            _state.collect(collector)
        }

        override val value: UserState
            get() = _state.value

    }


}