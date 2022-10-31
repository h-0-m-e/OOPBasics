
interface Attachment{
    val type: String
}

data class Video(val id: Int, val ownerId: Int, val url: String)
data class VideoAttachment(val video: Video) : Attachment{
    override val type = "video"
}

data class Photo(val id: Int, val ownerId: Int, val url: String)
data class PhotoAttachment(val photo: Photo) : Attachment{
    override val type = "photo"
}

data class Audio(val id: Int, val ownerId: Int, val url: String)
data class AudioAttachment(val audio: Audio) : Attachment{
    override val type = "audio"
}

data class Graffiti(val id: Int, val ownerId: Int, val url: String)
data class GraffitiAttachment(val graffiti: Graffiti) : Attachment{
    override val type = "graffiti"
}

data class Sticker(val id: Int, val ownerId: Int, val url: String)
data class StickerAttachment(val sticker: Sticker) : Attachment{
    override val type = "sticker"
}
data class Post(
    val id: Int,
    val ownerId: Int,
    val fromId: Int,
    val publisherId: Int,
    val publishDate: Long,
    val replyPostId: Int?,
    val replyOwnerId: Int?,
    val copyright: Copyright?,
    val postType: String,
    val postSource: Any?,
    val signerId: Int?,
    val canPin: Boolean,
    val canDelete: Boolean,
    val canEdit: Boolean,
    val isPinned: Boolean,
    val markedAsAds: Boolean,
    val isFavorite: Boolean,
    val postponed: Boolean,
    val friendsOnly: Boolean,
    val text: String,
    val views: Int?,
    val likes: Int?,
    val reposts: Reposts,
    val comments: Comments?,
    val donut: Donut,
    val geo: Geo?,
    val attachments: Array<Attachment> = emptyArray()
)
data class Comments(
    val count: Int,
    val canUserComment: Boolean,
    val canGroupsComment: Boolean,
    val canUserClose: Boolean,
    val canUserOpen: Boolean
)

data class Copyright(
    val id: Int,
    val link: String,
    val name: String
)

data class Geo(
    val type: String,
    val coordinates: String
)

data class Donut(
    val isDonut: Boolean,
    val paidDuration: Int
)
data class Reposts(
    val count: Int,
    val userReposted: Boolean
)

object WallService {

    private var idCounter = 1
    private var posts = emptyArray<Post>()

    fun add(post: Post): Post {
        posts += post.copy(id = idCounter++)
        return posts.last()
    }

    fun update(updatedPost: Post): Boolean {
        for ((index, post) in posts.withIndex()) {
            if (updatedPost.id == post.id) {
                posts[index] = updatedPost.copy()
                return true
            }
        }
        return false
    }

    fun clear() {
        posts = emptyArray()
        idCounter = 1
    }

    fun printAll() {
        for (post in posts) {
            println(post)
        }
    }
}

fun main() {
    val post = Post(
        1,
        9923,
        9923,
        9923,
        20222610,
        null,
        null,
        null,
        "post",
        null,
        null,
        true,
        true,
        true,
        false,
        false,
        true,
        false,
        false,
        "Hello Moscow!",
        views = 0,
        likes = 0,
        reposts = Reposts(1, true),
        Comments(2,true,true,true,true),
        Donut(false,0),
        Geo("Moscow. Red Square.","55.75482 37.62169"),
        arrayOf(
            VideoAttachment(Video(123,1,"link for video")),
            PhotoAttachment(Photo(123,1,"link for photo")),
            AudioAttachment(Audio(123,1,"link for audio")),
            GraffitiAttachment(Graffiti(123,1,"link for graffiti")),
            StickerAttachment(Sticker(123,1,"link for sticker")))
    )
    WallService.add(post)
    WallService.add(
        Post(
            5,
            9923,
            9923,
            9923,
            20222610,
            null,
            null,
            null,
            "post",
            null,
            null,
            true,
            true,
            true,
            false,
            false,
            true,
            false,
            false,
            "Hello SPB!",
            views = 0,
            likes = 0,
            reposts = Reposts(1, true),
            Comments(2,true,true,true,true),
            Donut(false,0),
            Geo("SPB","55.75482 37.62169"),
            arrayOf(
                VideoAttachment(Video(123,1,"link for video")),
                PhotoAttachment(Photo(123,1,"link for photo")),
                AudioAttachment(Audio(123,1,"link for audio")),
                GraffitiAttachment(Graffiti(123,1,"link for graffiti")))
        )
    )
    WallService.printAll()
    val newPost = Post(
        1,
        9923,
        9923,
        9923,
        20222610,
        null,
        null,
        null,
        "post",
        null,
        null,
        true,
        true,
        true,
        false,
        false,
        true,
        false,
        false,
        "Hello Moscow, I love you!",
        views = 0,
        likes = 0,
        reposts = Reposts(1, true),
        Comments(2,true,true,true,true),
        Donut(false,0),
        Geo("Moscow. Red Square.","55.75482 37.62169"),
        arrayOf(
            VideoAttachment(Video(123,1,"link for video")),
            PhotoAttachment(Photo(123,1,"link for photo")),
            AudioAttachment(Audio(123,1,"link for audio")),
            GraffitiAttachment(Graffiti(123,1,"link for graffiti")),
            StickerAttachment(Sticker(123,1,"link for sticker")))
    )
    WallService.update(newPost)
    WallService.printAll()
}