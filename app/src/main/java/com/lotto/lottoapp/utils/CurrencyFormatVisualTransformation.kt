package com.lotto.lottoapp.utils

import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation
import java.text.DecimalFormat
import java.text.NumberFormat
import java.util.Locale

class CurrencyFormatVisualTransformation : VisualTransformation {
    override fun filter(text: AnnotatedString): TransformedText {
        val inputText = text.text
        val value = inputText.toDoubleOrNull() ?: 0.0

        val formatted = formatAsCurrency(value)

        val currencyOffsetTranslator = object : OffsetMapping {
            override fun originalToTransformed(offset: Int): Int {
                return offset
            }

            override fun transformedToOriginal(offset: Int): Int {
                return offset
            }
        }

        return TransformedText(
            AnnotatedString(formatted), currencyOffsetTranslator
        )
    }

    private fun formatAsCurrency(value: Double): String {
        val numberFormat = NumberFormat.getNumberInstance(Locale.getDefault())
        if (numberFormat is DecimalFormat) {
            numberFormat.isParseBigDecimal = true
            numberFormat.minimumFractionDigits = 0
        }
        return numberFormat.format(value)
    }
}
