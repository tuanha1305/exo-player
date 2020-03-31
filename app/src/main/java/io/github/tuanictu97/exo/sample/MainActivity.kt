package io.github.tuanictu97.exo.sample

import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import io.github.tuanictu97.exo.core.SimpleExoPlayer
import io.github.tuanictu97.exo.core.source.MediaSource
import io.github.tuanictu97.exo.core.source.ProgressiveMediaSource
import io.github.tuanictu97.exo.core.upstream.DataSource
import io.github.tuanictu97.exo.core.upstream.DefaultDataSourceFactory
import io.github.tuanictu97.exo.core.util.Util
import io.github.tuanictu97.exo.ui.PlayerView
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    private lateinit var playerView: PlayerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init()
    }

    private fun init() {
        playerView = findViewById(R.id.player_view_video)
        val player = SimpleExoPlayer.Builder(this).build()
        playerView.player = player
        // Produces DataSource instances through which media data is loaded.
        // Produces DataSource instances through which media data is loaded.
        val dataSourceFactory: DataSource.Factory = DefaultDataSourceFactory(
            this,
            Util.getUserAgent(this, getString(R.string.app_name))
        )
        // This is the MediaSource representing the media to be played.
        // This is the MediaSource representing the media to be played.
        val videoSource: MediaSource = ProgressiveMediaSource.Factory(dataSourceFactory)
            .createMediaSource(Uri.parse(getString(R.string.url_video_sample)))
        // Prepare the player with the source.
        // Prepare the player with the source.
        player.prepare(videoSource)
        player.playWhenReady = true
    }
}
