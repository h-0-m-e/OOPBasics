import org.junit.Test

import org.junit.Assert.*
import org.junit.Before

class WallServiceTest {

    @Before
    fun clearBeforeTest() {
        WallService.clear()
    }

    @Test
    fun add_IdChanged() {

        val post = Post(
            0,
            9923,
            9923,
            9923,
            20222610,
            false,
            "Text Sample",
            reposts = Reposts(3, true),
            views = 0,
            likes = 0
        )
        val result = WallService.add(post).id

        assertNotEquals(0, result)
    }

    @Test
    fun update_PostExist() {
        val post = Post(
            1,
            9923,
            9923,
            9923,
            20222610,
            false,
            "Text Sample",
            reposts = Reposts(3, true),
            views = 0,
            likes = 0
        )
        WallService.add(post)
        val updatedPost = Post(
            post.id,
            9923,
            9923,
            9923,
            20222810,
            false,
            "Updated Text",
            reposts = Reposts(5, true),
            views = 0,
            likes = 0
        )

        val result = WallService.update(updatedPost)

        assertEquals(true, result)
    }

    @Test
    fun update_PostDoNotExist() {
        val post = Post(
            1,
            9923,
            9923,
            9923,
            20222610,
            false,
            "Text Sample",
            reposts = Reposts(3, true),
            views = 0,
            likes = 0
        )
        WallService.add(post)
        val updatedPost = Post(
            19,
            9923,
            9923,
            9923,
            20222810,
            false,
            "Updated Text",
            reposts = Reposts(3, true),
            views = 0,
            likes = 0
        )

        val result = WallService.update(updatedPost)

        assertNotEquals(true, result)
    }

}