package fastcampus.aop.part1.chpater3

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import fastcampus.aop.part1.chpater3.databinding.ActivityInputBinding

class InputActivity : AppCompatActivity() {
    private lateinit var binding: ActivityInputBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityInputBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.completeButton.setOnClickListener {
            Intent(this, MainActivity::class.java).apply {
                putExtra("name", binding.editTextName.text.toString())
                startActivity(this)
            }
        }
    }
}