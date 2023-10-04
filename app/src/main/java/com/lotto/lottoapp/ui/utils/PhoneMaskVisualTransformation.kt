import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation

class PhoneMaskVisualTransformation : VisualTransformation {
    override fun filter(text: AnnotatedString): TransformedText {
        val inputText = text.text
        val digitsOnly = inputText.replace(Regex("[^0-9]"), "")

        val formatted = buildString {
            val maxLength = 10
            val endIndex = if (digitsOnly.length > maxLength) maxLength else digitsOnly.length

            for (i in 0 until endIndex) {
                if (i == 3 || i == 6 || i == 8) {
                    append("-")
                }
                append(digitsOnly[i])
            }
        }

        val phoneNumberOffsetTranslator = object : OffsetMapping {
            override fun originalToTransformed(offset: Int): Int {
                if (offset <= 3) return offset
                if (offset <= 6) return offset + 1
                if (offset <= 9) return offset + 2
                return formatted.length
            }

            override fun transformedToOriginal(offset: Int): Int {
                if (offset <= 3) return offset
                if (offset <= 5) return offset - 1
                if (offset <= 8) return offset - 2
                return 9
            }
        }

        return TransformedText(
            AnnotatedString(formatted), phoneNumberOffsetTranslator
        )
    }
}
