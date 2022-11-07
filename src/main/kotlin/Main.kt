
sealed class Attachment(val type: String)

data class Video(val id: Int, val ownerId: Int, val url: String)
data class VideoAttachment(val video: Video) : Attachment("video")

data class Photo(val id: Int, val ownerId: Int, val url: String)
data class PhotoAttachment(val photo: Photo) : Attachment("photo")

data class Audio(val id: Int, val ownerId: Int, val url: String)
data class AudioAttachment(val audio: Audio) : Attachment("audio")


data class Graffiti(val id: Int, val ownerId: Int, val url: String)
data class GraffitiAttachment(val graffiti: Graffiti) : Attachment("graffiti")

data class Sticker(val id: Int, val ownerId: Int, val url: String)
data class StickerAttachment(val sticker: Sticker) : Attachment("sticker")
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
    var comments: Array<Comment> = emptyArray(),
    val donut: Donut,
    val geo: Geo?,
    val attachments: Array<Attachment> = emptyArray()
)
data class Comment(
    val id: Int,
    val fromId: Int,
    val date: Int,
    val text: String,
    val attachments: Array<Attachment> = emptyArray()
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

class PostNotFoundException(message:String): RuntimeException(message)

object WallService {

    private var PostIdCounter = 1
    private var CommentIdCounter = 1
    private var posts = emptyArray<Post>()
    private var comments = emptyArray<Comment>()

    fun add(post: Post): Post {
        posts += post.copy(id = PostIdCounter++)
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

    fun createComment(postId: Int, comment: Comment): Comment{
        for ((index, post) in posts.withIndex()) {
            if (postId == post.id) {
                comments += comment.copy(id = CommentIdCounter)
                posts[index].comments += comment.copy(id = CommentIdCounter++)
                return comment
            }
        }
        throw PostNotFoundException("Post with id $postId not found!")
    }

    fun clear() {
        posts = emptyArray()
        comments = emptyArray()
        PostIdCounter = 1
        CommentIdCounter = 1
    }

    fun printAllPosts() {
        for (post in posts) {
            println(post)
        }
    }

    fun printAllComments() {
        for (comment in comments) {
            println(comment)
        }
    }
}

fun main() {
    WallService.add(
        Post(
            0,
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
            comments = emptyArray(),
            Donut(false,0),
            Geo("SPB","55.75482 37.62169"),
            arrayOf(
                VideoAttachment(Video(123,1,"link for video")),
                PhotoAttachment(Photo(123,1,"link for photo")),
                AudioAttachment(Audio(123,1,"link for audio")),
                GraffitiAttachment(Graffiti(123,1,"link for graffiti")))
        )
    )
    WallService.createComment(
        1,
        comment = Comment(
            0,
            23,
            23121999,
            "Let's walk together!",
            arrayOf(
                PhotoAttachment(Photo(234,23,"link for photo"))
            )))
    WallService.printAllPosts()
    WallService.printAllComments()
}