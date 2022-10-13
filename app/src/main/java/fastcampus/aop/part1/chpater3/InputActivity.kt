package fastcampus.aop.part1.chpater3

import android.content.Context
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import fastcampus.aop.part1.chpater3.databinding.ActivityInputBinding

class InputActivity : AppCompatActivity() {
    private lateinit var binding: ActivityInputBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityInputBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.bloodTypeSpinner.adapter = ArrayAdapter.createFromResource(
            this,
            R.array.blood_type_alphabet_array,
            android.R.layout.simple_list_item_1
        )

        binding.checkBox.setOnCheckedChangeListener { _, isChecked ->
            binding.warningEditText.isVisible = isChecked
        }

        binding.completeTextView.setOnClickListener {
            saveData()
            finish()
        }
    }

    private fun getBloodType(): String {
        val bloodTypeSign =
            if (binding.radioGroup.checkedRadioButtonId == R.id.plusRadioButton) "+" else "-"
        val bloodTypeAlphabet = binding.bloodTypeSpinner.selectedItem

        return "$bloodTypeSign$bloodTypeAlphabet"
    }

    private fun getWarning() =
        if (binding.checkBox.isChecked) "${binding.warningEditText.text}" else ""

    private fun saveData() {
        with(getSharedPreferences(USER_INFORMATION, Context.MODE_PRIVATE).edit()) {
            putString(NAME, "${binding.nameEditText.text}")
            putString(BLOOD_TYPE, getBloodType())
            putString(EMERGENCY_CONTACT_NUMBER, "${binding.phoneEditText.text}")
            putString(WARNING, getWarning())
            apply()
        }
        Toast.makeText(this, "저장을 완료했습니다.", Toast.LENGTH_SHORT).show()
    }
}