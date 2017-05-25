package me.donnie.read.data.entity

/**
 * @author donnieSky
 * @created_at 2017/5/19.
 * @description
 * @version
 */
data class Question(var authorName: String?,
                    var authorJobTitle: String?,
                    var authorAvatar: String?,
                    var date: String?,
                    var text: String?,
                    var tags: List<Tag>?) {

    fun hasTag(string: String): Boolean {
        for (tag in tags!!) {
            if (tag.getText() == string) {
                return true
            }
        }
        return false
    }

    override fun equals(o: Any?): Boolean {
        if (this === o) return true
        if (o !is Question) return false

        val question = o

        if (if (authorName != null) !authorName.equals(question.authorName) else question.authorName != null)
            return false
        if (if (authorJobTitle != null) !authorJobTitle.equals(question.authorJobTitle) else question.authorJobTitle != null)
            return false
        if (if (authorAvatar != null) !authorAvatar.equals(question.authorAvatar) else question.authorAvatar != null)
            return false
        if (if (date != null) !date.equals(question.date) else question.date != null)
            return false
        if (if (text != null) !text.equals(question.text) else question.text != null)
            return false
        return if (tags != null) tags!!.equals(question.tags) else question.tags == null
    }
}