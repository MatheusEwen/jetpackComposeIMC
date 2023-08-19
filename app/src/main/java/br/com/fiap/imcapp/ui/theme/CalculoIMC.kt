package br.com.fiap.imcapp
import kotlin.math.pow


fun calcularIMC(altura: Double, peso: Double) : Double {
    return peso / (altura / 100).pow(2.0)
}
data class StringPair(val first: String, val second: String)

fun determinarClassificacaoIMC(imc: Double): StringPair {
    var text = ""
    var color = ""
    if(imc < 18.5) {
        text = "Abaixo do peso"
        color = "FFF44336"
        return StringPair(text, color)
    } else if (imc >= 18.5 && imc < 25.0) {
        text = "Peso Ideal"
        color = "FF4CAF50"
        return StringPair(text, color)

    } else if (imc >= 25.0 && imc < 30.0) {
        text = "Quase acima do peso"
        color = "FFFFEB3B"
        return StringPair(text, color)

    } else if (imc >= 30.0 && imc < 35.0) {
        text = "Obesidade Grau I"
        color = "FFF44336"
        return StringPair(text, color)

    } else if (imc >= 35.0 && imc < 40.0) {
        text = "Obesidade Grau II"
        color = "FFF44336"
        return StringPair(text, color)

    } else {
        text = "Obesidade Grau III"
        color = "FFF44336"
        return StringPair(text, color)

    }
}