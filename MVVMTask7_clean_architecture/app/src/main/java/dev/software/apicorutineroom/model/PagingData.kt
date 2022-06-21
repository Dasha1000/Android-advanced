package dev.software.apicorutineroom.model

sealed class PagingData<out T> {
    class Content<T>(val data: T) : PagingData<T>()
    object Loading : PagingData<Nothing>()
}