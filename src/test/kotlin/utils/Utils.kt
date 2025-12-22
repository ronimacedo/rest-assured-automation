package utils

class Utils {
    fun readJsonFromResources(fileName: String): String {
        return object {}.javaClass.classLoader
            .getResource(fileName)
            ?.readText()
            ?: throw IllegalArgumentException("Arquivo não encontrado: $fileName")
    }
}