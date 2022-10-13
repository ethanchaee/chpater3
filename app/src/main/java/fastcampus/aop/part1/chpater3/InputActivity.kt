package fastcampus.aop.part1.chpater3

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
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
            Log.d("bloodType", getBloodType())

            Intent(this, MainActivity::class.java).apply {
                putExtra("name", binding.nameEditText.text)
                putExtra("bloodType", getBloodType())
                putExtra("phoneNumber", binding.phoneEditText.text)
                putExtra("warning", getWarning())
                startActivity(this)
            }
        }
    }

    private fun getBloodType(): String {
        val bloodTypeSign =
            if (binding.radioGroup.checkedRadioButtonId == R.id.plusRadioButton) "+" else "-"
        val bloodTypeAlphabet = binding.bloodTypeSpinner.selectedItem

        return "$bloodTypeSign$bloodTypeAlphabet"
    }

    private fun getWarning() = if (binding.checkBox.isSelected) binding.warningEditText.text else ""

}