package com.sopt.now.compose.item

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sopt.now.compose.R
import com.sopt.now.compose.ui.theme.NOWSOPTAndroidTheme


@Composable
fun FriendItem(profile: Profile) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 10.dp, vertical = 20.dp),
    ) {
        Image(
            painter = painterResource(profile.userProfile),
            contentDescription = stringResource(id = R.string.app_name),
            Modifier
                .size(50.dp)
                .aspectRatio(1f)
        )
        Spacer(modifier = Modifier.width(10.dp))
        Text(
            text = profile.userName,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
        Spacer(modifier = Modifier.weight(1f))
        Text(
            text = profile.userDescription,
            fontSize = 20.sp,
            textAlign = TextAlign.End,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
    }
}

@Preview(showBackground = true)
@Composable
fun FriendPreview() {
    NOWSOPTAndroidTheme {
        FriendItem(Profile(R.drawable.iv_user_profile, "youjin", "welcome"))
    }
}