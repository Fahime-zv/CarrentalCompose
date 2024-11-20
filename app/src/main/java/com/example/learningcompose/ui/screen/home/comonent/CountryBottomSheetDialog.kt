package com.example.learningcompose.ui.screen.home.comonent

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.learningcompose.onStringCLickListener
import com.example.learningcompose.ui.components.GearButton


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ShowCountryDialog(onDismissListener:()->Unit,onCountryCLickListener: onStringCLickListener){
    val sheetState= rememberModalBottomSheetState()
        ModalBottomSheet(

            onDismissRequest = {
                onDismissListener.invoke()
            },
            sheetState = sheetState
        ) {
            BottomSheetContent(onCloseClick = {
                onDismissListener.invoke()

            },onCountryCLickListener=onCountryCLickListener)
    }
}

@Composable
fun BottomSheetContent(onCloseClick: () -> Unit,onCountryCLickListener: onStringCLickListener) {
    var selectedCountry: String? by remember { mutableStateOf(null) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 16.dp, top = 0.dp, end = 16.dp, bottom = 16.dp)
    ) {
        // Title and Close Icon Row
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = "Select your country",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
            IconButton(onClick = onCloseClick) {
                Icon(
                    imageVector = Icons.Default.Close,
                    contentDescription = "Close"
                )
            }
        }

        CountryList(onCountryClickListener = {countryName->
            selectedCountry=countryName
        })

        GearButton("Continue", onCLickListener = {
            selectedCountry?.let { onCountryCLickListener.invoke(it) }
        })

        Spacer(modifier = Modifier.height(16.dp))

    }
}

@Composable
fun CountryList(onCountryClickListener: onStringCLickListener) {
    var selectedIndex by remember { mutableIntStateOf(-1) }

    val items = listOf(
        ListItem("Kuwait", Icons.Default.Person),
        ListItem("Saudi Arabia", Icons.Default.Person),
        ListItem("Bahrain", Icons.Default.Person),
        ListItem("UAE", Icons.Default.Person),
        ListItem("Oman", Icons.Default.Person),
        ListItem("Qatar", Icons.Default.Person)
    )

    LazyColumn {
        itemsIndexed(items) { index, item ->
            SelectableListRow(
                item = item,
                isSelected = selectedIndex == index,
                onClick = {
                    selectedIndex = index
                    onCountryClickListener.invoke(items[index].title)
                }
            )
        }
    }
}


@Composable
fun SelectableListRow(
    item: ListItem,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable(onClick = onClick),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Image/Icon for the item
        Image(
            imageVector = item.icon,
            contentDescription = "Item Image",
            modifier = Modifier
                .size(40.dp)
                .padding(8.dp)
        )

        // Title for the item
        Text(
            text = item.title,
            fontSize = 18.sp,
            modifier = Modifier.weight(1f)
        )

        // RadioButton for selection
        RadioButton(
            selected = isSelected,
            onClick = onClick
        )
    }
}

data class ListItem(
    val title: String,
    val icon: ImageVector
)
