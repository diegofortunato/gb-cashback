package com.gb.cashback.constant

class APIConstant {

    companion object {
        const val BASE_API = "/v1"

        const val SERVICE_CREATE_RESELLER = "/reseller"
        const val SERVICE_GET_RESELLER = "/reseller/{id}"
        const val SERVICE_UPDATE_RESELLER = "/reseller/{id}"
        const val SERVICE_DELETE_RESELLER = "/reseller/{id}"

        const val SERVICE_POST_AUTH = "/auth"

        const val SERVICE_POST_PURCHASE = "/purchase"
        const val SERVICE_GET_PURCHASE = "/purchase"

        const val SERVICE_GET_CASHBACK = "/cashback"

        const val BASE_INTEGRATION = "https://mdaqk8ek5j.execute-api.us-east-1.amazonaws.com/v1/"
        const val SERVICE_GET_CASHBACK_INTEGRATION = "cashback?cpf=12312312321"

        const val ERROR_400 = "Revendedor já existe no sistema."
        const val DETAILS_ERROR_400 = "O revendedor já existe no sistema, " +
                "verifique o email e o número do documento."

        const val ERROR_AUTH_400 = "Erro de login"
        const val DETAILS_ERROR_AUTH_400 = "Email ou senha estao incorretos."

        const val ERROR_404 = "Revendedor não existe no sistema."
        const val DETAILS_ERROR_404 = "O revendedor não existe no sistema, " +
                "verifique o seu ID."

        const val ERROR_412 = "Existem campos com Inconsistências."

        const val ERROR_500 = "Erro interno do servidor."
        const val DETAILS_ERROR_500 = "Ocoreu um erro interno no servidor, " +
                "entre em contato com o administrador do sistema."
    }
}