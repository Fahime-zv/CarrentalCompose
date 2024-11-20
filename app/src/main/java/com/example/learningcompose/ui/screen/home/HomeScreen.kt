package com.example.learningcompose.ui.screen.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.learningcompose.R
import com.example.learningcompose.ui.components.GearButton
import com.example.learningcompose.ui.provider.StringProvider
import com.example.learningcompose.ui.screen.home.comonent.ShowCountryDialog

@Composable
fun HomeScreen() {
    // Setup Screen
    HalfScreenImageWithOverlay()
}

@Composable
fun HalfScreenImageWithOverlay() {

    // Parameters
    val screenHeight = LocalConfiguration.current.screenHeightDp.dp

    // Setup Background Image
    Box(modifier = Modifier.fillMaxSize().systemBarsPadding()) {
        Image(
            painter = painterResource(id = R.drawable.home_background),
            contentDescription = "",
            modifier = Modifier
                .fillMaxWidth()
                .height(screenHeight / 2),
            contentScale = ContentScale.Crop
        )
        // Setup Text Image
        Image(
            painter = painterResource(id = R.drawable.be_gear_up), // Replace with your image resource
            contentDescription = "",
            modifier = Modifier
                .width(144.dp)
                .height(175.dp).padding(start = 20.dp),
            contentScale = ContentScale.FillBounds
        )
        // Setup Car Image
        Image(
            painter = painterResource(id = R.drawable.home_image), // Replace with your image resource
            contentDescription = "",
            modifier = Modifier
                .align(Alignment.TopEnd)
                .fillMaxWidth(0.7f)
                .fillMaxHeight(0.45f).padding(top = 30.dp),
            contentScale = ContentScale.FillBounds
        )
        // Setting
        Column(
            verticalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.6f)
                .padding(start = 20.dp, end = 20.dp, bottom = 10.dp)
                .align(Alignment.BottomCenter) // Align component at the bottom
                .background(
                    color = Color.White, shape = RoundedCornerShape(18.dp)
                ).border(0.5.dp, Color.LightGray, shape = RoundedCornerShape(18.dp))
        ) {
            // Parameters
            var showComposable by remember { mutableStateOf(false) }
            // Show Country Dialog
            SelectCountry(onCountryClickListener = {
                showComposable = true
            })
            if (showComposable) {
                ShowCountryDialog(
                    onDismissListener = {
                        showComposable = false
                    },
                    onCountryCLickListener = {
                        //No implemented
                    }
                )
            }
            // Add PickupDate View
            SelectDateAndTime(StringProvider.pickupDate, onDateClickListener = {}, onTimeClickListener = {})
            // Add DropOffDate View
            SelectDateAndTime(StringProvider.dropOffDate, onDateClickListener = {}, onTimeClickListener = {})
            // Add Space View
            Spacer(modifier = Modifier.height(16.dp))
            // Add StartNow Button
            GearButton(StringProvider.startNow) {/*Nothing now */ }
        }
    }
}


@Composable
fun SelectDateAndTime(
    title: String,
    onDateClickListener: () -> Unit,
    onTimeClickListener: () -> Unit
) {
    // Setting root view
    Column(
        modifier = Modifier.padding(start = 24.dp, end = 24.dp),
    ) {
        // Setup Title TextView
        Text(
            text = title,
            fontSize = 16.sp,
            color = Color.Black,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 12.dp, top = 12.dp)
        )
        // Setting Layout
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()
                .border(1.dp, Color.LightGray, shape = RoundedCornerShape(12.dp))
                .padding(top = 16.dp, start = 16.dp, end = 12.dp, bottom = 16.dp),
        ) {
            //Setup Layout
            Row {
                // Setup Icon View
                Icon(
                    imageVector = ImageVector.vectorResource(R.drawable.ic_calendar),
                    contentDescription = "",
                    modifier = Modifier.padding(end = 8.dp).size(24.dp), // Padding to separate the icon from the text
                    tint = Color.Black
                )
                // Setup SelectDate TextView
                Text(
                    text = StringProvider.selectDate,
                    fontSize = 16.sp,
                    color = Color.Black,
                )
            }
            // Setup Divider Icon
            Icon(
                imageVector = ImageVector.vectorResource(R.drawable.ic_divider),
                contentDescription = "",
                modifier = Modifier.size(4.dp, 20.dp),
                tint = Color.Gray
            )
            // Setup Time TextView
            Text(
                text = "13:00",
                fontSize = 16.sp,
                color = Color.Black,
                )
            // Setup Divider Arrow
            Icon(
                imageVector = ImageVector.vectorResource(R.drawable.ic_arrow_down),
                contentDescription = "",
                modifier = Modifier.padding(end = 8.dp), // Padding to separate the icon from the text
                tint = Color.Black,
            )
        }
    }

}


@Composable
fun SelectCountry(onCountryClickListener: () -> Unit) {
    // Setup Root Layout
    Column(modifier = Modifier.padding(start = 24.dp, end = 24.dp)) {
        // Setup DropOffLocation TextView
        Text(
            text = StringProvider.dropOffLocation,
            fontSize = 16.sp,
            color = Color.Black,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 12.dp, top = 24.dp)
        )
        // Setup Layout
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth().clickable { onCountryClickListener.invoke() }
                .border(1.dp, Color.LightGray, shape = RoundedCornerShape(12.dp))
                .padding(top = 16.dp, start = 16.dp, end = 12.dp, bottom = 16.dp),

            ) {
            Row {
                // Setup Location IconView
                Icon(
                    imageVector = ImageVector.vectorResource(R.drawable.ic_location),
                    contentDescription = "",
                    modifier = Modifier.padding(end = 8.dp).size(24.dp), // Padding to separate the icon from the text
                    tint = Color.Black
                )
                // Setup SelectCountry TextView
                Text(
                    text = StringProvider.selectYourCountry,
                    fontSize = 16.sp,
                    color = Color.Black,
                )
            }
            // Setup Arrow Down IconView
            Icon(
                imageVector = ImageVector.vectorResource(R.drawable.ic_arrow_down),
                contentDescription = "",
                modifier = Modifier.padding(end = 8.dp), // Padding to separate the icon from the text
                tint = Color.Black,
            )
        }
    }

}
