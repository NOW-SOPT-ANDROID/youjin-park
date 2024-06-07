package com.sopt.now.compose.presentation

import androidx.compose.runtime.livedata.observeAsState

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sopt.now.compose.BottomNavigationItem
import com.sopt.now.compose.ui.theme.NOWSOPTAndroidTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NOWSOPTAndroidTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val viewModel: UserInfoViewModel = remember { UserInfoViewModel() }

                    MainView(viewModel)
                }
            }
        }
    }
}

@Composable
fun MainView(viewModel: UserInfoViewModel) {
    var selectedItem by remember { mutableIntStateOf(0) }
    val items = listOf(
        BottomNavigationItem(
            icon = Icons.Filled.Home,
            label = "Home"
        ),
        BottomNavigationItem(
            icon = Icons.Filled.Search,
            label = "Search"
        ),
        BottomNavigationItem(
            icon = Icons.Filled.Person,
            label = "Profile"
        )
    )

    Scaffold(
        bottomBar = {
            NavigationBar {
                items.forEachIndexed { index, item ->
                    NavigationBarItem(
                        icon = { Icon(item.icon, contentDescription = item.label) },
                        label = { Text(item.label) },
                        selected = selectedItem == index,
                        onClick = { selectedItem = index }
                    )
                }
            }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier.padding(innerPadding),
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            when(selectedItem) {
                0 -> SetHomeView(viewModel)
                1 -> Text(text ="Search")
                2 -> SetMyPageView(viewModel)
            }
        }
    }
}

// 사용자 데이터 적용
@Composable
fun SetHomeView(viewModel: UserInfoViewModel){

    viewModel.userInfo()

    viewModel.userInfoLiveData.observeAsState().value?.let { userInfo ->
        HomeView(
            userName = userInfo.data.nickname,
            userPhone = userInfo.data.phone,
        )
    }
}

@Composable
fun SetMyPageView(viewModel: UserInfoViewModel){

    viewModel.userInfo()

    viewModel.userInfoLiveData.observeAsState().value?.let { userInfo ->
        MyPageView(
            userId = userInfo.data.authenticationId,
            userPw = userInfo.data.nickname,
            userPhone = userInfo.data.phone,
        )
    }
}

@Preview(showBackground = true)
@Composable
fun MainPreview() {
    NOWSOPTAndroidTheme {
        MyPageView("", "", "")
    }
}