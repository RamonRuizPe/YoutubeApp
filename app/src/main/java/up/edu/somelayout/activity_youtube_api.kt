package up.edu.somelayout

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import com.google.android.youtube.player.YouTubeBaseActivity
import com.google.android.youtube.player.YouTubeInitializationResult
import com.google.android.youtube.player.YouTubePlayer
import com.google.android.youtube.player.YouTubePlayerView

//https://www.youtube.com/watch?v=hV8H3TNkQFk
const val YOUTUBE_VIDEO_ID_KEY = "hV8H3TNkQFk"
//https://www.youtube.com/watch?v=j3Idam5UjDY&list=PL-fsxXDXMkpwHYuwiyh7rY5_usD4jY8n0

const val PLAYLIST_ID_KEY = "PL-fsxXDXMkpwHYuwiyh7rY5_usD4jY8n0"

class activity_youtube_api : YouTubeBaseActivity(), YouTubePlayer.OnInitializedListener {
    val TAG = "YoutubePlayerActivity"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)
        //val layout = findViewById<ConstraintLayout>(R.id.activity_youtube) //Esto es lo mismo que la línea posterior
        val layout = layoutInflater.inflate(R.layout.activity_youtube_api, null) as ConstraintLayout //Al no ser una vista anidad, el root se hace null.
        setContentView(layout)

        //Button adding.
        //val button1 = Button(this)
        //button1.layoutParams = ConstraintLayout.LayoutParams(600,100)
        //button1.text = "button added"
        //layout.addView(button1)

        //Creating a widget of YoutubePlayerView
        val playerView = YouTubePlayerView(this)
        playerView.layoutParams = ConstraintLayout.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
        layout.addView(playerView)

        playerView.initialize(getString(R.string.GOOGLE_API_KEY), this)

    }

    override fun onInitializationSuccess(
        provider: YouTubePlayer.Provider?,
        player: YouTubePlayer?,
        wasRestored: Boolean
    ) {
        Toast.makeText(this,"Error starting player", Toast.LENGTH_LONG).show()

        player?.setPlaybackEventListener(playbackEventListener)
        player?.setPlayerStateChangeListener(changeStateListener)

        if(!wasRestored){
            player?.cueVideo(YOUTUBE_VIDEO_ID_KEY)
        }
        Log.d(TAG, "onInitializationSuccess")

    }

    override fun onInitializationFailure(
        provider: YouTubePlayer.Provider?,
        youTubeInitializationResult: YouTubeInitializationResult?
    ) {
        val REQUEST_CODE = 0
        if(youTubeInitializationResult?.isUserRecoverableError == true){
            youTubeInitializationResult.getErrorDialog(this, REQUEST_CODE).show()
        }else{
            Toast.makeText(this,"Error starting player", Toast.LENGTH_LONG).show()
        }
        Log.d(TAG, "onInitializationFailure")
    }

    private val playbackEventListener = object : YouTubePlayer.PlaybackEventListener{
        override fun onPlaying() {
            Toast.makeText(this@activity_youtube_api, "Playing", Toast.LENGTH_SHORT).show() //Si no se especifica en el this el objeto al que se hace referencia, creería que es al objeto de esta variable
        }

        override fun onPaused() {
            Toast.makeText(this@activity_youtube_api, "Paused", Toast.LENGTH_SHORT).show() //Si no se especifica en el this el objeto al que se hace referencia, creería que es al objeto de esta variable
        }

        override fun onStopped() {
        }

        override fun onBuffering(p0: Boolean) {

        }

        override fun onSeekTo(p0: Int) {

        }
    }

    private val changeStateListener = object : YouTubePlayer.PlayerStateChangeListener{
        override fun onLoading() {

        }

        override fun onLoaded(p0: String?) {

        }

        override fun onAdStarted() {
            Toast.makeText(this@activity_youtube_api, "Ad", Toast.LENGTH_SHORT).show() //Si no se especifica en el this el objeto al que se hace referencia, creería que es al objeto de esta variable
        }

        override fun onVideoStarted() {

        }

        override fun onVideoEnded() {
            Toast.makeText(this@activity_youtube_api, "Finished", Toast.LENGTH_SHORT).show() //Si no se especifica en el this el objeto al que se hace referencia, creería que es al objeto de esta variable
        }

        override fun onError(p0: YouTubePlayer.ErrorReason?) {

        }

    }

}