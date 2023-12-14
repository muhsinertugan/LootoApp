package com.lotto.lottoapp.ui.feature.profile.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.unit.dp
import com.lotto.lottoapp.ui.feature.profile.ProfileScreenContract
import com.lotto.lottoapp.utils.TimeUtil

@Composable
fun ProfileDataColumn(profileState: State<ProfileScreenContract.ProfileState>) {
    val time = TimeUtil()

    Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {

        ProfileData(
            title = profileState.value.title.name,
            data = profileState.value.data.name
        )
        ProfileData(
            title = profileState.value.title.lastName,
            data = profileState.value.data.lastName
        )
        ProfileData(
            title = profileState.value.title.email,
            data = profileState.value.data.email
        )
        ProfileData(
            title = profileState.value.title.phoneNumber,
            data = profileState.value.data.phoneNumber
        )
        ProfileData(
            title = profileState.value.title.city,
            data = profileState.value.data.city.name
        )
        ProfileData(
            title = profileState.value.title.birthDate,
            data = if (profileState.value.data.birthDate !== "") time.convertDateFormat(
                profileState.value.data.birthDate
            ) else ""
        )

    }
}