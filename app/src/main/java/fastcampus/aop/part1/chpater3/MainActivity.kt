package fastcampus.aop.part1.chpater3

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
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

        binding.resetTextView.setOnClickListener {
            deleteData()
            getData()
        }
    }

    override fun onResume() {
        super.onResume()
        getData()
    }

    private fun getData() {
        with(getSharedPreferences(USER_INFORMATION, Context.MODE_PRIVATE)) {
            binding.nameValueTextView.text = getString(NAME, "")
            binding.bloodTypeValueTextView.text = getString(BLOOD_TYPE, "")
            binding.emergencyContactValueTextView.text = getString(EMERGENCY_CONTACT_NUMBER, "")
            getString(WARNING, "").orEmpty().let { warningText ->
                if (warningText.isNotEmpty()) binding.warningValueTextView.text = warningText
            }
        }
    }

    private fun deleteData() {
        with(getSharedPreferences(USER_INFORMATION, Context.MODE_PRIVATE).edit()) {
            clear()
            apply()
        }
        Toast.makeText(this, "삭제를 완료했습니다.", Toast.LENGTH_SHORT).show()
    }
}