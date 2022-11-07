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
            comments = emptyArray(),
            Donut(false,0),
            Geo("Moscow. Red Square.","55.75482 37.62169"),
            arrayOf(
                VideoAttachment(Video(123,1,"link for video")),
                PhotoAttachment(Photo(123,1,"link for photo")),
                AudioAttachment(Audio(123,1,"link for audio")),
                GraffitiAttachment(Graffiti(123,1,"link for graffiti")),
                StickerAttachment(Sticker(123,1,"link for sticker")))
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
            comments = emptyArray(),
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
        val updatedPost = Post(
            post.id,
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
            comments = emptyArray(),
            Donut(false,0),
            Geo("Moscow. Red Square.","55.75482 37.62169"),
            arrayOf(
                VideoAttachment(Video(123,1,"link for video")),
                PhotoAttachment(Photo(123,1,"link for photo")),
                AudioAttachment(Audio(123,1,"link for audio")),
                GraffitiAttachment(Graffiti(123,1,"link for graffiti")),
                StickerAttachment(Sticker(123,1,"link for sticker")))
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
            comments = emptyArray(),
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
        val updatedPost = Post(
            19,
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
            comments = emptyArray(),
            Donut(false,0),
            Geo("Moscow. Red Square.","55.75482 37.62169"),
            arrayOf(
                VideoAttachment(Video(123,1,"link for video")),
                PhotoAttachment(Photo(123,1,"link for photo")),
                AudioAttachment(Audio(123,1,"link for audio")),
                GraffitiAttachment(Graffiti(123,1,"link for graffiti")),
                StickerAttachment(Sticker(123,1,"link for sticker")))
        )

        val result = WallService.update(updatedPost)

        assertNotEquals(true, result)
    }

    @Test(expected = PostNotFoundException::class)
    fun createComment_ShouldThrow() {
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
            9,
            comment = Comment(
                0,
                23,
                23121999,
                "Let's walk together!",
                arrayOf(
                    PhotoAttachment(Photo(234,23,"link for photo"))
                )))
    }

}