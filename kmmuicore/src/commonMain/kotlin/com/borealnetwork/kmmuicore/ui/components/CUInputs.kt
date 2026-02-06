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
        singleLine = true,
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
    onValueChange: (TextFieldValue) -> Unit,
    topLabelText: String = "",
    placeHolderText: String = "",
    enabled: Boolean = true,
    maxLength: Int = Int.MAX_VALUE,
    avoidSpecialCharacters: Boolean = true,
    singleLine: Boolean = true,
    keyboardOptions: KeyboardOptions = KeyboardOptions(
        capitalization = KeyboardCapitalization.Words,
        keyboardType = KeyboardType.Text
    ),
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    showEndIcon: Boolean = true,
    iconTint: Color = PrimaryColor,
    showTopEndIcon: Boolean = true,
    endIcon: DrawableResource = Res.drawable.ic_qrcode,
    topEndIcon: DrawableResource = Res.drawable.ic_help,
    endIconClicked: () -> Unit = {},
    topEndIconClicked: () -> Unit = {}
) {
    // 1. Memorizar colores para evitar cálculos en recomposición
    val borderColor = if (enabled) PrimaryColor else BorderDisabledColor
    val textColor = if (enabled) Black else TextDisabledColor

    Column(modifier = modifier.fillMaxWidth()) {
        // --- Fila Superior ---
        Row(
            modifier = Modifier.fillMaxWidth().padding(bottom = 4.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = topLabelText,
                fontSize = 14.sp,
                color = TextTopColor,
                fontWeight = SemiBold
            )
            if (showTopEndIcon) {
                Icon(
                    painter = painterResource(topEndIcon),
                    contentDescription = "Ayuda",
                    tint = PrimaryColor,
                    modifier = Modifier
                        .size(20.dp)
                        .clickable(onClick = topEndIconClicked)
                )
            }
        }

        // --- Campo de Texto ---
        OutlinedTextField(
            value = value,
            onValueChange = { newValue ->
                if (validateInput(
                        newValue.text,
                        maxLength,
                        avoidSpecialCharacters,
                        keyboardOptions.keyboardType
                    )
                ) {
                    onValueChange(newValue)
                }
            },
            modifier = Modifier.fillMaxWidth(),
            enabled = enabled,
            singleLine = singleLine,
            placeholder = {
                Text(text = placeHolderText, color = textColor.copy(alpha = 0.6f))
            },
            trailingIcon = {
                if (showEndIcon) {
                    IconButton(onClick = endIconClicked) {
                        Icon(
                            painter = painterResource(endIcon),
                            contentDescription = null,
                            tint = iconTint
                        )
                    }
                }
            },
            keyboardOptions = keyboardOptions,
            keyboardActions = keyboardActions,
            shape = RoundedCornerShape(4.dp),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = borderColor,
                unfocusedBorderColor = borderColor,
                disabledBorderColor = BorderDisabledColor,
                focusedContainerColor = White,
                unfocusedContainerColor = White
            )
        )
    }
}

private fun validateInput(
    text: String,
    maxLength: Int,
    avoidSpecialChars: Boolean,
    keyboardType: KeyboardType
): Boolean {
// 1. Control de longitud (Siempre primero)
    if (text.length > maxLength) return false

    // 2. Si el texto está vacío, permitirlo (para poder borrar)
    if (text.isEmpty()) return true

    val lastChar = text.last()

    // 3. FILTRO ESTRICTO PARA NÚMEROS
    // Si el teclado es de tipo Number o NumberPassword, solo aceptamos dígitos.
    if (keyboardType == KeyboardType.Number || keyboardType == KeyboardType.NumberPassword) {
        return lastChar.isDigit()
    }

    // 4. Validación para Email
    if (keyboardType == KeyboardType.Email) {
        return text.matches("[a-zA-Z0-9-.\\-_@#]*".toRegex())
    }

    // 5. Filtro de caracteres especiales (para texto normal)
    if (avoidSpecialChars) {
        return lastChar.isLetterOrDigit()
    }

    // Si avoidSpecialChars es false y no es un campo numérico, aceptamos todo
    return true
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

