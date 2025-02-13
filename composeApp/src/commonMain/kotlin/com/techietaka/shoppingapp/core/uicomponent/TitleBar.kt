package com.techietaka.shoppingapp.core.uicomponent

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.techietaka.shoppingapp.core.extension.EmptyCallback

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TitleBar(
    toolbarTitle: String,
    modifier: Modifier = Modifier,
    navigationButtonAction: EmptyCallback? = null,
    actions: @Composable RowScope.() -> Unit = {},
) = Row(modifier = modifier.fillMaxWidth()) {
    TopAppBar(
        colors = TopAppBarDefaults.topAppBarColors(),
        navigationIcon = {
            navigationButtonAction?.let {
                IconButton(onClick = it) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "Localized description"
                    )
                }
            }
        },
        actions = actions,
        title = { Text(text = toolbarTitle) }
    )
}