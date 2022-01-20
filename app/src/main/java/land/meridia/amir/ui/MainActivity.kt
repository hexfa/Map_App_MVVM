package land.meridia.amir.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.coroutines.InternalCoroutinesApi
import land.meridia.amir.databinding.ActivityMainBinding
import land.meridia.amir.ui.map.MapActivity

@InternalCoroutinesApi
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.button.setOnClickListener{
            startActivity( Intent(this@MainActivity, MapActivity::class.java))
        }
    }
}