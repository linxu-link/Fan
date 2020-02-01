package com.link.fan.data.bean

const val TYPE_IMAGE_TEXT = 1
const val TYPE_VIDEO_TEXT = 2

data class CommunityEntity(
        var id: Int,
        var itemId: Long,
        var itemType: Int,
        var createTime: Long,
        var duration: Double,
        var feeds_text: String,
        var authorId: Long,
        var activityIcon: String,
        var activityText: String,
        var width: Int,
        var height: Int,
        var url: String,
        var cover: String,
        var author: UserEntity)