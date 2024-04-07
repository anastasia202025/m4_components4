package com.example.m4_components4

import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.ProgressBar
import android.widget.RadioGroup
import android.widget.Switch
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.widget.addTextChangedListener
import com.google.android.material.checkbox.MaterialCheckBox
import com.google.android.material.switchmaterial.SwitchMaterial
import com.google.android.material.textfield.TextInputEditText
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val randomProgress = Random.nextInt(101)
        val pointsProgressBar = findViewById<ProgressBar>(R.id.pointsLinearBar)
        val pointsText = findViewById<AppCompatTextView>(R.id.actualPointsTextView)
        pointsProgressBar.progress = randomProgress
        pointsText.setText("$randomProgress / 100")

        var isNameValid = false
        var isPhoneValid = false
        var isGenderSelect = false
        var isNotificationSelect = false
        var isNotificationAuthorizedSelect = false
        var isNotificationNewProductSelect = false

        val userNameText = findViewById<TextInputEditText>(R.id.userNameText)
        userNameText.addTextChangedListener {
            val name = it.toString()
            isNameValid = name.isNotEmpty() && name.length <= 40
            checkSaveButtonStatus(
                isNameValid,
                isPhoneValid,
                isGenderSelect,
                isNotificationSelect,
                isNotificationAuthorizedSelect,
                isNotificationNewProductSelect
            )
        }

        val userPhoneText = findViewById<TextInputEditText>(R.id.userPhoneText)
        userPhoneText.addTextChangedListener {
            val phone = it.toString()
            isPhoneValid = phone.isNotEmpty() && phone.length <= 15
            checkSaveButtonStatus(isNameValid,
                isPhoneValid,
                isGenderSelect,
                isNotificationSelect,
                isNotificationAuthorizedSelect,
                isNotificationNewProductSelect)
        }

        val genderRadioGroup = findViewById<RadioGroup>(R.id.genderRadioGroup)
        genderRadioGroup.setOnCheckedChangeListener { group, checkedId ->
            isGenderSelect = checkedId != -1
            checkSaveButtonStatus(isNameValid,
                isPhoneValid,
                isGenderSelect,
                isNotificationSelect,
                isNotificationAuthorizedSelect,
                isNotificationNewProductSelect)
        }


        val notificationSwitch = findViewById<SwitchMaterial>(R.id.notificationsSwitch)
        val notificationAuthorizedCheckBox =
            findViewById<MaterialCheckBox>(R.id.notificationAuthorizationCheckBox)
        val notificationNewProductCheckBox =
            findViewById<MaterialCheckBox>(R.id.notificationNewProductsCheckBox)

        notificationAuthorizedCheckBox.isChecked = false
        notificationAuthorizedCheckBox.isEnabled = false
        notificationAuthorizedCheckBox.isClickable = false
        notificationNewProductCheckBox.isChecked = false
        notificationNewProductCheckBox.isEnabled = false
        notificationNewProductCheckBox.isClickable = false

        notificationSwitch.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
                notificationAuthorizedCheckBox.isEnabled = true
                notificationAuthorizedCheckBox.isClickable = true
                notificationNewProductCheckBox.isEnabled = true
                notificationNewProductCheckBox.isClickable = true

                isNotificationSelect = true

                checkSaveButtonStatus(
                    isNameValid,
                    isPhoneValid,
                    isGenderSelect,
                    isNotificationSelect,
                    isNotificationAuthorizedSelect,
                    isNotificationNewProductSelect
                )

            } else {
                notificationAuthorizedCheckBox.isChecked = false
                notificationAuthorizedCheckBox.isEnabled = false
                notificationAuthorizedCheckBox.isClickable = false
                notificationNewProductCheckBox.isChecked = false
                notificationNewProductCheckBox.isEnabled = false
                notificationNewProductCheckBox.isClickable = false

                isNotificationSelect = false
                checkSaveButtonStatus(
                    isNameValid,
                    isPhoneValid,
                    isGenderSelect,
                    isNotificationSelect,
                    isNotificationAuthorizedSelect,
                    isNotificationNewProductSelect
                )
            }
        }

        notificationAuthorizedCheckBox.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
                isNotificationAuthorizedSelect = true

                checkSaveButtonStatus(isNameValid,
                    isPhoneValid,
                    isGenderSelect,
                    isNotificationSelect,
                    isNotificationAuthorizedSelect,
                    isNotificationNewProductSelect)
            } else {
                isNotificationAuthorizedSelect = false

                checkSaveButtonStatus(isNameValid,
                    isPhoneValid,
                    isGenderSelect,
                    isNotificationSelect,
                    isNotificationAuthorizedSelect,
                    isNotificationNewProductSelect)
            }
        }

        notificationNewProductCheckBox.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
                isNotificationNewProductSelect = true

                checkSaveButtonStatus(isNameValid,
                    isPhoneValid,
                    isGenderSelect,
                    isNotificationSelect,
                    isNotificationAuthorizedSelect,
                    isNotificationNewProductSelect)
            } else {
                isNotificationNewProductSelect = false

                checkSaveButtonStatus(isNameValid,
                    isPhoneValid,
                    isGenderSelect,
                    isNotificationSelect,
                    isNotificationAuthorizedSelect,
                    isNotificationNewProductSelect)
            }
        }

        val saveButton = findViewById<Button>(R.id.saveButton)
        saveButton.setOnClickListener {
            Toast.makeText(this, "Save ${userNameText.text}", Toast.LENGTH_SHORT).show()
        }
    }

    private fun checkSaveButtonStatus(
        isNameValid: Boolean,
        isPhoneValid: Boolean,
        isGenderSelect: Boolean,
        isNotificationSelect: Boolean,
        isNotificationAuthorizedSelect: Boolean,
        isNotificationNewProductSelect: Boolean
    ) {
        val saveButton = findViewById<Button>(R.id.saveButton)

        saveButton.isEnabled =
            isNameValid && isPhoneValid && isGenderSelect && isNotificationSelect &&
                    (isNotificationAuthorizedSelect || isNotificationNewProductSelect)
    }
}