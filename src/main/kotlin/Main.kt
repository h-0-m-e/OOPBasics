import java.util.Objects

data class Post(
    val id: Int,
    val ownerId: Int,
    val fromId: Int,
    val publisherId: Int,
    val publishDate: Long,
    val friendsOnly: Boolean,
    val text: String,
    val reposts: Reposts,
    val views: Int,
    val likes: Int
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
        false,
        "Text Sample",
        reposts = Reposts(1, true),
        views = 0,
        likes = 0
    )
    WallService.add(post)
    WallService.add(
        Post(
            5,
            23,
            12,
            22,
            20212109,
            false,
            "Nothing to read here",
            reposts = Reposts(3, true),
            views = 0,
            likes = 0
        )
    )
    WallService.printAll()
    val newPost = Post(
        1,
        9923,
        9923,
        9923,
        20222610,
        false,
        "NEW Text Sample",
        reposts = Reposts(0, false),
        views = 0,
        likes = 0
    )
    WallService.update(newPost)
    WallService.printAll()
}