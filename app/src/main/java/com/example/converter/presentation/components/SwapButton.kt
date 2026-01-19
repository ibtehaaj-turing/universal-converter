package com.example.converter.presentation.components

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.SwapVert
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate

@Composable
fun SwapButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    var rotationAngle by remember { mutableFloatStateOf(0f) }
    val animatedRotation by animateFloatAsState(
        targetValue = rotationAngle,
        animationSpec = spring(
            dampingRatio = 0.6f,
            stiffness = 400f
        ),
        label = "swapButtonRotation"
    )

    IconButton(
        onClick = {
            rotationAngle += 180f
            onClick()
        },
        modifier = modifier
    ) {
        Icon(
            imageVector = Icons.Default.SwapVert,
            contentDescription = "Swap units",
            tint = MaterialTheme.colorScheme.primary,
            modifier = Modifier.rotate(animatedRotation)
        )
    }
}
