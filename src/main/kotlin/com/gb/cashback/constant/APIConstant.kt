package com.gb.cashback.constant

class APIConstant {

    companion object {
        const val BASE_API = "/v1"

        const val SERVICE_CREATE_RESELLER = "/reseller"
        const val SERVICE_GET_RESELLER = "/reseller/{id}"
        const val SERVICE_GET_ALL_RESELLER = "/reseller"
        const val SERVICE_UPDATE_RESELLER = "/reseller/{id}"
        const val SERVICE_DELETE_RESELLER = "/reseller/{id}"

        const val SERVICE_POST_AUTH = "/auth"

        const val SERVICE_POST_PURCHASE = "/purchase"
        const val SERVICE_GET_ALL_PURCHASE = "/purchase"
        const val SERVICE_GET_PURCHASE = "/purchase/{id}"

        const val SERVICE_GET_CASHBACK = "/cashback"

        const val BASE_INTEGRATION = "https://mdaqk8ek5j.execute-api.us-east-1.amazonaws.com/v1/"
        const val SERVICE_GET_CASHBACK_INTEGRATION = "cashback?cpf=12312312321"

        const val ERROR_400 = "Entity already exists in the system."
        const val ERROR_400_RESELER = "Reseller already exists in the system, " +
                "check the email and document number."
        const val ERROR_400_PURCHASE = "Purchase already exists in the system, " +
                "check the code."

        const val ERROR_AUTH_400 = "Email or password is incorrect."
        const val DETAILS_ERROR_AUTH_400 = "There was an error when logging in, check your email and password."

        const val ERROR_404 = "Entity does not exist in the system."
        const val ERROR_404_RESELER = "Reseller does not exist in the system, " +
                "check your ID."

        const val ERROR_412 = "There are fields with Inconsistencies."

        const val ERROR_500 = "Internal server error."
        const val DETAILS_ERROR_500 = "An internal server error occurred, " +
                "contact your system administrator."
    }
}