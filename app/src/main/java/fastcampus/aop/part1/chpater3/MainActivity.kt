package fastcampus.aop.part1.chpater3

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import fastcampus.aop.part1.chpater3.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.floatingActionButton.setOnClickListener {
            val intent = Intent(this, InputActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onResume() {
        super.onResume()
        getData()
    }

    private fun getData() {
        with(getSharedPreferences("userInformation", Context.MODE_PRIVATE)) {
            binding.nameValueTextView.text = getString("name", "")
            binding.bloodTypeValueTextView.text = getString("bloodType", "")
            binding.emergencyContactValueTextView.text = getString("phoneNumber", "")
            getString("warning", "").orEmpty().let { warningText ->
                if (warningText.isNotEmpty()) binding.warningValueTextView.text = warningText
            }
        }
    }
}