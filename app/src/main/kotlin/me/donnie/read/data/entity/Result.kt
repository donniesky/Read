package me.donnie.read.data.entity

/**
 * @author donnieSky
 * @created_at 2017/5/9.
 * @description
 * @version
 */
data class Result<T>(var books: List<T>,
                     var ok: Boolean)