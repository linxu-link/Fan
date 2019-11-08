package com.link.component_shopping.data.entity

data class GoodsEntity(var img:String, var id:String, var price:String, var volume:String,var isHave:Boolean,
                       var isDirect:Boolean,var swiperImgs:Array<String>,var detailImgs:Array<String>,var name:String) {


    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as GoodsEntity

        if (img != other.img) return false
        if (id != other.id) return false
        if (price != other.price) return false
        if (volume != other.volume) return false
        if (isHave != other.isHave) return false
        if (isDirect != other.isDirect) return false
        if (!swiperImgs.contentEquals(other.swiperImgs)) return false
        if (!detailImgs.contentEquals(other.detailImgs)) return false
        if (name != other.name) return false

        return true
    }

    override fun hashCode(): Int {
        var result = img.hashCode()
        result = 31 * result + id.hashCode()
        result = 31 * result + price.hashCode()
        result = 31 * result + volume.hashCode()
        result = 31 * result + isHave.hashCode()
        result = 31 * result + isDirect.hashCode()
        result = 31 * result + swiperImgs.contentHashCode()
        result = 31 * result + detailImgs.contentHashCode()
        result = 31 * result + name.hashCode()
        return result
    }
}