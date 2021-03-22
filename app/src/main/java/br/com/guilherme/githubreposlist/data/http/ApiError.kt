package br.com.guilherme.githubreposlist.data.http

class ApiError(var code: Int?, var message: String?) {

    fun generateErrorMessage() {
        message = when (code) {
            0 -> "Erro teste 1"
            1 -> "Erro teste 2"
            2 -> "Erro teste 3"
            else -> "Erro desconhecido"
        }
    }


}