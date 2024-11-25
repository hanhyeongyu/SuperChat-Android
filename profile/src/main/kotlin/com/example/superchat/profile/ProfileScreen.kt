package com.example.superchat.profile

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.superchat.core.designsystem.component.AppBackground
import com.example.superchat.core.designsystem.component.AppButton
import com.example.superchat.core.designsystem.theme.AppTheme

@Composable
fun ProfileScreen(
    modifier: Modifier = Modifier,
    viewModel: ProfileViewModel = hiltViewModel()
){

    val profileUIState by viewModel.profileUIState.collectAsStateWithLifecycle()

    ProfileScreen(
        profileStatus = profileUIState,
        buttonOnClick = viewModel::buttonOnTap
    )
}

@Composable
fun ProfileScreen(
    profileStatus: ProfileUIState,
    buttonOnClick: () -> Unit,
    modifier: Modifier = Modifier
){
    Box {
        Column(modifier = Modifier.align(Alignment.Center)){
            val text = when(profileStatus){
                ProfileUIState.Initialized -> "Initialized"
                ProfileUIState.Error -> "Error"
                ProfileUIState.Loading -> "Loading"
                is ProfileUIState.Success -> profileStatus.result
            }

            Text(
                text = text,
                modifier = Modifier
            )


            AppButton(
                onClick = buttonOnClick
            ){
                Text("fetch")
            }
        }
    }
}


@Preview
@Composable
fun ProfileScreenPreview(){
    AppTheme {
        AppBackground {
            ProfileScreen(
                profileStatus = ProfileUIState.Success("fetch success"),
                buttonOnClick = {}
            )
        }
    }
}