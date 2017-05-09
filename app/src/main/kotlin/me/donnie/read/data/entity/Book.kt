package me.donnie.read.data.entity

import java.util.*

/**
 * @author donnieSky
 * @created_at 2017/5/9.
 * @description
 * @version
 */
data class Book(var _id: String,
                var title: String,
                var author: String,
                var shortIntro: String,
                var cover: String,
                var hasCp: Boolean,
                var latelyFollower: Int,
                var retentionRatio: Double,
                var updated: Date,
                var chaptersCount: Int,
                var lastChapter: String)