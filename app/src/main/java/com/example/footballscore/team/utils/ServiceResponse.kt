package com.example.footballscore.team.utils

import ar.com.galicia.core.services.ServiceResult

interface ServiceResponse{

    fun<T> onSucces(succes:ServiceResult<T>)
    fun<T> onError(error:ServiceResult<T>)
}