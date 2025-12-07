package com.borealnetwork.kmmuicore.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.font.FontWeight.Companion.Bold
import androidx.compose.ui.text.font.FontWeight.Companion.Medium
import androidx.compose.ui.text.font.FontWeight.Companion.SemiBold
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.borealnetwork.kmmuicore.domain.base.EMPTY_STRING
import com.borealnetwork.kmmuicore.ui.theme.BorderDisabledColor
import com.borealnetwork.kmmuicore.ui.theme.CUGrayColor
import com.borealnetwork.kmmuicore.ui.theme.CUGrayLetterHint
import com.borealnetwork.kmmuicore.ui.theme.GrayLetterHint
import com.borealnetwork.kmmuicore.ui.theme.PrimaryColor
import com.borealnetwork.kmmuicore.ui.theme.TextDisabledColor
import com.borealnetwork.kmmuicore.ui.theme.TextTopColor
import com.borealnetwork.kmmuicore.ui.theme.appTypography
import com.borealnetwork.kmmuicore.ui.theme.robotoFamily
import com.borealnetwork.kmmuicore.utils.containsAlphanumericCharacters
import com.borealnetwork.kmmuicore.utils.isDigitsOnly
import io.github.baudelioandalon.kmmuicore.drawable.Res
import io.github.baudelioandalon.kmmuicore.drawable.ic_design_invisibility
import io.github.baudelioandalon.kmmuicore.drawable.ic_design_visibility
import io.github.baudelioandalon.kmmuicore.drawable.ic_help
import io.github.baudelioandalon.kmmuicore.drawable.ic_qrcode
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Preview
@Composable
fun ShowEditsPreview(){

    var token by rememberSaveable(stateSaver = TextFieldValue.Saver) {
        mutableStateOf(
            TextFieldValue(
                EMPTY_STRING
            )
        )
    }

    EditTextTopLabel(
        modifier = Modifier.fillMaxWidth()
            .padding(start = 8.dp, top = 20.dp, end = 8.dp, bottom = 20.dp),
        maxLength = 50,
        enabledCounter = true,
        singleLine = true,
        textSize = 20.sp,
        placeHolderText = "Ingresar token alfanumérico",
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        keyboardActions = KeyboardActions(
            onNext = {
                if (token.text.length >= 50) {
                }
            }),
        value = token,
        onValueChange = {
            token = it

        })
}


@Composable
fun EditText(
    modifier: Modifier = Modifier,
    placeHolderText: String = "",
    onValueChange: (String) -> Unit,
    value: String,
    isError: Boolean = false,
    errorMessage: String = "",
    keyboardOptions: KeyboardOptions = KeyboardOptions(
        capitalization = KeyboardCapitalization.Words,
        keyboardType = KeyboardType.Text
    ),
    keyboardActions: KeyboardActions = KeyboardActions { },
    trailingIcon: @Composable (() -> Unit)? = null,
    enabled: Boolean = true,
    enabledCounter: Boolean = false,
    maxLength: Int = 0,
    singleLine: Boolean = false,
    colors: TextFieldColors = TextFieldDefaults.colors(unfocusedLabelColor = GrayLetterHint),
    isPassword: Boolean = false,
    visualTransformation: VisualTransformation = VisualTransformation.None
) {
    val interactionSource = remember { MutableInteractionSource() }
    var passwordVisibility by remember { mutableStateOf(isPassword) }
    val icon = if (passwordVisibility)
        painterResource(resource = Res.drawable.ic_design_invisibility)
    else
        painterResource(resource = Res.drawable.ic_design_visibility)

    Column(modifier = modifier) {

        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth(),
            textStyle = TextStyle(
                fontFamily = appTypography(),
                color = Black,
                fontWeight = SemiBold,
                fontSize = 15.sp
            ),
            interactionSource = interactionSource,
            value = value,
            onValueChange = {
                if (enabledCounter) {
                    if (it.length <= maxLength) {
                        onValueChange(it)
                    }
                } else {
                    onValueChange(it)
                }
            }, placeholder = {
                SemiBoldText(
                    text = placeHolderText,
                    color = GrayLetterHint,
                    fontSize = 15.sp
                )
            },
            colors = colors,
            isError = isError,
            keyboardOptions = if (isPassword) KeyboardOptions(
                keyboardType = KeyboardType.Password
            ) else keyboardOptions,
            trailingIcon = {
                if (isPassword) IconButton(onClick = {
                    passwordVisibility = !passwordVisibility
                }) {
                    Icon(
                        painter = icon,
                        contentDescription = "Visibility Icon"
                    )
                } else trailingIcon
            },
            enabled = enabled,
            singleLine = singleLine,
            keyboardActions = keyboardActions,
            visualTransformation = if (!passwordVisibility) visualTransformation
            else PasswordVisualTransformation(mask = '●')
        )

        AnimatedVisibility(visible = isError && errorMessage.isNotEmpty()) {
            Text(
                text = errorMessage,
                color = MaterialTheme.colorScheme.error,
                fontSize = 15.sp,
                fontWeight = Medium,
                fontFamily = appTypography()
            )
        }
    }
}

@Composable
fun EditTextTopLabel(
    modifier: Modifier = Modifier,
    value: TextFieldValue,
    keyboardOptions: KeyboardOptions = KeyboardOptions(
        capitalization = KeyboardCapitalization.Words,
        keyboardType = KeyboardType.Text
    ),
    keyboardActions: KeyboardActions = KeyboardActions { },
    singleLine: Boolean = true,
    topLabelText: String = EMPTY_STRING,
    placeHolderText: String = EMPTY_STRING,
    maxLength: Int = 0,
    enabled: Boolean = false,
    showEndIcon: Boolean = true,
    enabledCounter: Boolean = false,
    textSize: TextUnit = 15.sp,
    textTopSize: TextUnit = 14.sp,
    iconTint: Color = PrimaryColor,
    borderColor: Color = PrimaryColor,
    textColor: Color = Black,
    textTopColor: Color = TextTopColor,
    textDisabledColor: Color = TextDisabledColor,
    borderDisabledColor: Color = BorderDisabledColor,
    endIcon: DrawableResource = Res.drawable.ic_qrcode,
    showTopEndIcon: Boolean = true,
    topEndIcon: DrawableResource = Res.drawable.ic_help,
    fontFamilyTop: FontFamily = robotoFamily(),
    fontWeightTop: FontWeight = SemiBold,
    fontFamilyPlaceholder: FontFamily = robotoFamily(),
    fontWeightPlaceholder: FontWeight = Bold,
    endIconClicked: () -> Unit = {},
    topEndIconClicked: () -> Unit = {},
    onValueChange: (TextFieldValue) -> Unit
) {
    val interactionSource = remember { MutableInteractionSource() }

    val backgroundBorderColor = if (enabled) borderColor else borderDisabledColor
    val textFieldColor = if (enabled) textColor else textDisabledColor

    Column(
        modifier = modifier.fillMaxWidth()
    ) {
        // 1. Fila Superior: Etiqueta "Token" e Icono de Ayuda (?)
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 4.dp, top = 40.dp), // Espacio entre etiqueta y caja
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            Text(
                text = topLabelText,
                fontSize = textTopSize,
                color = textTopColor,
                fontFamily = fontFamilyTop,
                fontWeight = fontWeightTop
            )
            if (showTopEndIcon) {
                Icon(
                    painter = painterResource(topEndIcon),
                    contentDescription = "Ayuda",
                    tint = iconTint,
                    modifier = Modifier
                        .size(20.dp)
                        .clickable { topEndIconClicked() }
                )
            }

        }

        // 2. Campo de Texto (OutlinedTextField)
        OutlinedTextField(
            value = value,
            onValueChange = {
                if (enabledCounter) {
                    if (it.text.length <= maxLength) {
                        onValueChange(it)
                    }
                } else {
                    onValueChange(it)
                }
            },
            interactionSource = interactionSource,
            modifier = Modifier.fillMaxWidth(),
            placeholder = {
                Text(
                    text = placeHolderText,
                    fontSize = textSize,
                    color = textFieldColor,
                    fontFamily = fontFamilyPlaceholder,
                    fontWeight = fontWeightPlaceholder
                )
            },
            keyboardOptions = keyboardOptions,
            trailingIcon = {
                if (showEndIcon){
                    IconButton(onClick = endIconClicked) {
                        Icon(
                            painter = painterResource(endIcon),
                            contentDescription = "Escanear QR",
                            tint = iconTint
                        )
                    }
                }
            },
            enabled = enabled,
            singleLine = singleLine,
            keyboardActions = keyboardActions,
            shape = RoundedCornerShape(2.dp),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = backgroundBorderColor,
                unfocusedBorderColor = backgroundBorderColor,
                cursorColor = backgroundBorderColor,
                focusedContainerColor = White,
                unfocusedContainerColor = White
            )
        )
    }
}

@Composable
fun EditTextBottomLabel(
    modifier: Modifier = Modifier,
    innerModifier: Modifier = Modifier,
    placeHolderText: String = "",
    onValueChange: (TextFieldValue) -> Unit,
    isError: Boolean = false,
    value: TextFieldValue,

    avoidSpecialCharacters: Boolean = true,
    errorMessage: String = "",
    onlyNumbers: Boolean = false,
    keyboardOptions: KeyboardOptions = KeyboardOptions(
        capitalization = KeyboardCapitalization.Words,
        keyboardType = KeyboardType.Text
    ),
    keyboardActions: KeyboardActions = KeyboardActions { },
    trailingIcon: @Composable (() -> Unit)? = null,
    enabled: Boolean = true,
    fontFamily: FontFamily = appTypography(),
    textSize: TextUnit = 15.sp,
    bottomLabelText: String = "",
    label: @Composable (() -> Unit)? = null,
    enabledHelper: Boolean = true,
    enabledCounter: Boolean = false,
    maxLength: Int = 0,
    singleLine: Boolean = false,
    colors: TextFieldColors = TextFieldDefaults.colors(),
    isPassword: Boolean = false,
    visualTransformation: VisualTransformation = VisualTransformation.None
) {
    val interactionSource = remember { MutableInteractionSource() }
    var passwordVisibility by remember { mutableStateOf(isPassword) }
    val icon = if (passwordVisibility)
        painterResource(resource = Res.drawable.ic_design_invisibility)
    else
        painterResource(resource = Res.drawable.ic_design_visibility)

    Column(modifier = modifier) {

        OutlinedTextField(
            modifier = innerModifier
                .fillMaxWidth(),
            textStyle = TextStyle(
                fontFamily = fontFamily,
                color = Black,
                fontWeight = SemiBold,
                fontSize = textSize
            ),
            label = label,
            interactionSource = interactionSource,
            value = value,
            onValueChange = {
                if (it.text.isEmpty()) {
                    onValueChange(it)
                } else if (!avoidSpecialCharacters && !it.text.last().toString()
                        .containsAlphanumericCharacters() || it.text.isNotEmpty() && keyboardOptions == KeyboardOptions(
                        keyboardType = KeyboardType.Email
                    ) && !it.text.matches("[a-zA-Z-0-9-.\\-_@#]+".toRegex()) || !it.text.last()
                        .isLetterOrDigit() && !it.text.matches("[a-zA-Z-0-9-.\\-_@#]+".toRegex())
                ) {

                } else {
                    if (enabledCounter) {
                        if (onlyNumbers && it.text.matches(".*[-.].*".toRegex())) return@OutlinedTextField
                        if (it.text.last().toString().isDigitsOnly() && it.text.length <= maxLength
                            || it.text.isEmpty()
                            || it.text.length <= maxLength && !onlyNumbers
                            || it.text.length <= maxLength && onlyNumbers && it.text.replace(
                                " ",
                                ""
                            ).last().toString().isDigitsOnly()
                        ) {
                            onValueChange(
                                TextFieldValue(
                                    text = it.text,
                                    selection = it.selection
                                )
                            )
                        } else if (it.text.length <= maxLength && it.text.replace(" ", "").last()
                                .toString() < value.text.replace(" ", "").last().toString()
                        ) {
                            onValueChange(
                                TextFieldValue(
                                    text = it.text,
                                    selection = it.selection
                                )
                            )

                        }
                    } else {
                        if (!onlyNumbers || onlyNumbers && it.text.last().toString()
                                .isDigitsOnly()
                        ) {
                            onValueChange(
                                TextFieldValue(
                                    text = it.text,
                                    selection = it.selection
                                )
                            )
                        }
                    }
                }
            }, placeholder = {
                SemiBoldText(
                    text = placeHolderText,
                    color = CUGrayLetterHint,
                    fontSize = 15.sp
                )
            },
            colors = colors,
            isError = isError,
            keyboardOptions = if (isPassword) KeyboardOptions(
                keyboardType = KeyboardType.Password
            ) else keyboardOptions,
            trailingIcon = {
                if (isPassword) IconButton(onClick = {
                    passwordVisibility = !passwordVisibility
                }) {
                    Icon(
                        painter = icon,
                        contentDescription = "Visibility Icon"
                    )
                } else trailingIcon?.invoke()
            },
            enabled = enabled,
            shape = RoundedCornerShape(12.dp),
            singleLine = singleLine,
            keyboardActions = keyboardActions,
            visualTransformation = if (!passwordVisibility) visualTransformation
            else PasswordVisualTransformation(mask = '●')
        )

        if (enabledHelper) {
            RegularText(
                modifier = Modifier
                    .align(Alignment.End)
                    .padding(top = 9.dp),
                text = bottomLabelText,
                color = CUGrayColor,
                fontSize = 15.sp,
            )
        }

        AnimatedVisibility(visible = isError && errorMessage.isNotEmpty()) {
            RegularText(
                text = errorMessage,
                color = MaterialTheme.colorScheme.error,
                fontSize = 15.sp,
            )
        }

    }
}

