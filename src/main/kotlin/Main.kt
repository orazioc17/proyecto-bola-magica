import kotlin.concurrent.thread
import kotlin.concurrent.timer

// Declaramos los valores de nuestras respuestas
const val RESPUESTA_AFIRMATIVA = "✅"
const val RESPUESTA_NEGATIVA = "❌"
const val RESPUESTA_DUDOSA = "?"

//Unimos las respuestas con los valores
val respuestas = mapOf(
    "Sí" to RESPUESTA_AFIRMATIVA,
    "Es cierto" to RESPUESTA_AFIRMATIVA,
    "Totalmente" to RESPUESTA_AFIRMATIVA,
    "Sin duda" to RESPUESTA_AFIRMATIVA,
    "Pregunta en otro momento" to RESPUESTA_DUDOSA,
    "No puedo decirte en este momento" to RESPUESTA_DUDOSA,
    "Puede que si o puede que no" to RESPUESTA_DUDOSA,
    "No va a suceder" to RESPUESTA_NEGATIVA,
    "No cuentes con ello" to RESPUESTA_NEGATIVA,
    "Definitivamente no" to RESPUESTA_NEGATIVA,
    "No lo creo" to RESPUESTA_NEGATIVA,
)

fun main(args: Array<String>) {
    println()
    var condicion = true
    do {
        println()
        println()
        println(
            """
        ###### Bienvenido al programa "Bola Ocho" escrito en Kotlin, seleccione una opcion ######
        1. Realizar una pregunta
        2. Revisar todas las respuestas
        3. Salir
        """.trimIndent()
        )

        when (readLine()) {
            "1" -> realizarPregunta()
            "2" -> mostrarRespuestas()
            "3" -> {
                println("Gracias por usar el programa, vuelva luego!")
                condicion = false
            }
            else -> println("Opcion incorrecta, por favor ingrese una opcion valida")
        }
    } while (condicion)
}

fun realizarPregunta() {
    println()
    println()
    println("Ingresa tu pregunta a la Bola Magica: ")
    readLine()
    println("Pregunta recibida... tu respuesta sera otorgada en breve...")
    printPointsEverySecond()
    println(respuestas.keys.random())
}

fun printPointsEverySecond() {
    for (i in 0..5) {
        Thread.sleep(1000L)
        print(".")
    }
    Thread.sleep(2000L)
    println()
}

fun mostrarRespuestas() {
    var condicion = true
    do {
        println()
        println()
        println(
            """
        Seleccione una opcion
        1. Revisar todas las respuestas
        2. Revisar solo las respuestas afirmativas
        3. Revisar solo las respuestas dudosas
        4. Revisar solo las respuestas negativas
        """.trimIndent()
        )

        when(readLine()) {
            "1" -> condicion = mostrarRespuestasPorTipo()
            "2" -> condicion = mostrarRespuestasPorTipo(RESPUESTA_AFIRMATIVA)
            "3" -> condicion = mostrarRespuestasPorTipo(RESPUESTA_DUDOSA)
            "4" -> condicion = mostrarRespuestasPorTipo(RESPUESTA_NEGATIVA)
            else -> {
                println("Opcion no valida, intente de nuevo")
            }
        }
    } while (condicion)
}

fun mostrarRespuestasPorTipo(tipoDeRespuesta: String = "TODOS"): Boolean {
    when (tipoDeRespuesta) {
        "TODOS" -> respuestas.keys.forEach { respuesta -> println(respuesta)}
        RESPUESTA_AFIRMATIVA -> respuestas.filterValues { values -> values == RESPUESTA_AFIRMATIVA }.also {
            respuestasPositivas -> println(respuestasPositivas.keys)
        }
        RESPUESTA_DUDOSA -> respuestas.filterValues { values -> values == RESPUESTA_DUDOSA }.also {
                respuestasDudosas -> println(respuestasDudosas.keys)
        }
        RESPUESTA_NEGATIVA -> respuestas.filterValues { values -> values == RESPUESTA_NEGATIVA }.also {
                respuestasNegativas -> println(respuestasNegativas.keys)
        }
    }
    return false
}
