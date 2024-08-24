package com.kazemieh.designsystem.sample

import android.annotation.SuppressLint
import android.content.res.Configuration
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import com.kazemieh.designsystem.libdesign.ButtonType
import com.kazemieh.designsystem.libdesign.ConfigurationButton
import com.kazemieh.designsystem.libdesign.CornerRadius
import com.kazemieh.designsystem.libdesign.StateButton
import com.kazemieh.designsystem.libdesign.StyleButton
import com.kazemieh.designsystem.sample.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        darkModeConfig()

        smallButtonConfig()
    }

    private fun smallButtonConfig() {

        // type
        typeConfig()

        // corner
        cornerRadiusConfig()

        //configuration
        configurationConfig()

        //spinnerStyle
        spinnerStyleConfig()

        //spinnerState
        spinnerStateConfig()

        //leadingIcon
        leadingIconConfig()

        //trailingIcon
        trailingIconConfig()

        binding.smallButton.setOnClickListener {
            Log.d("949494", "smallButtonConfig: clicked")
        }
        binding.smallButton.text =
            binding.root.resources.getString(R.string.button_name)

    }
    private fun darkModeConfig() {
        binding.switchDarkLightMode.isChecked =
            (resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK) != Configuration.UI_MODE_NIGHT_NO
        @SuppressLint("UseCompatLoadingForDrawables")
        fun isCheckedSwitch(isCheck: Boolean) {
            if (isCheck) AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            else AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        }
        isCheckedSwitch(binding.switchDarkLightMode.isChecked)
        binding.switchDarkLightMode.setOnCheckedChangeListener { buttonView, isChecked ->
            isCheckedSwitch(isChecked)
        }
    }
    private fun typeConfig() {
        fun isCheckedSwitch(isCheck: Boolean) {
            if (isCheck) binding.smallButton.buttonType =
                ButtonType.NORMAL
            else binding.smallButton.buttonType = ButtonType.ICON
        }
        isCheckedSwitch(binding.switchTypeButton.isChecked)
        binding.switchTypeButton.setOnCheckedChangeListener { buttonView, isChecked ->
            isCheckedSwitch(isChecked)
        }
    }

    private fun cornerRadiusConfig() {
        fun isCheckedSwitch(isCheck: Boolean) {
            if (isCheck) binding.smallButton.cornerRadius =
                CornerRadius.ROUND_100
            else binding.smallButton.cornerRadius = CornerRadius.ROUND_8
            // binding.smallButton.cornerRadius = 25 // number
        }
        isCheckedSwitch(binding.switchRoundnessSmallButton.isChecked)
        binding.switchRoundnessSmallButton.setOnCheckedChangeListener { buttonView, isChecked ->
            isCheckedSwitch(isChecked)
        }
    }

    private fun configurationConfig() {
        val configurationList = arrayOf("Primary", "Error")
        val configurationAdapter =
            ArrayAdapter(
                binding.root.context,
                android.R.layout.simple_spinner_item,
                configurationList
            )
        configurationAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spinnerConfigurationSmallButton.adapter = configurationAdapter
        binding.spinnerConfigurationSmallButton.onItemSelectedListener =
            object :
                AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    if (position == 0) {
                        binding.smallButton.configuration =
                            ConfigurationButton.PRIMARY
                    } else {
                        binding.smallButton.configuration =
                            ConfigurationButton.ERROR
                    }

                }

                override fun onNothingSelected(parent: AdapterView<*>?) {
                }

            }
    }

    private fun spinnerStyleConfig() {
        val styleList = arrayOf("FIELD", "OUTLINE", "TEXT", "ELEVATED", "TONAL")
        val styleAdapter =
            ArrayAdapter(
                binding.root.context,
                android.R.layout.simple_spinner_item,
                styleList
            )
        styleAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spinnerStyleSmallButton.adapter = styleAdapter
        binding.spinnerStyleSmallButton.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    when (position) {
                        0 -> {
                            binding.smallButton.style = StyleButton.FIELD
                        }

                        1 -> {
                            binding.smallButton.style =
                                StyleButton.OUTLINE
                        }

                        2 -> {
                            binding.smallButton.style =
                                StyleButton.STANDARD
                        }

                        3 -> {
                            binding.smallButton.style =
                                StyleButton.ELEVATED
                        }

                        4 -> {
                            binding.smallButton.style = StyleButton.TONAL
                        }
                    }

                }

                override fun onNothingSelected(parent: AdapterView<*>?) {
                }

            }
    }

    private fun spinnerStateConfig() {
        val styleList = arrayOf("ENABLE", "DISABLE", "LOADING")
        val styleAdapter =
            ArrayAdapter(
                binding.root.context,
                android.R.layout.simple_spinner_item,
                styleList
            )
        styleAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spinnerStateSmallButton.adapter = styleAdapter
        binding.spinnerStateSmallButton.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    when (position) {
                        0 -> {
                            binding.smallButton.state = StateButton.ENABLE
                        }

                        1 -> {
                            binding.smallButton.state =
                                StateButton.DISABLE
                        }

                        2 -> {
                            binding.smallButton.state =
                                StateButton.LOADING
                        }
                    }

                }

                override fun onNothingSelected(parent: AdapterView<*>?) {
                }

            }
    }

    private fun leadingIconConfig() {
        @SuppressLint("UseCompatLoadingForDrawables")
        fun isCheckedSwitch(isCheck: Boolean) {
            if (isCheck) binding.smallButton.leadingIcon =
                binding.root.context.getDrawable(R.drawable.icon)
            else binding.smallButton.leadingIcon = null
        }
        isCheckedSwitch(binding.switchShowLeadingIconSmallButton.isChecked)
        binding.switchShowLeadingIconSmallButton.setOnCheckedChangeListener { buttonView, isChecked ->
            isCheckedSwitch(isChecked)
        }
    }

    private fun trailingIconConfig() {
        @SuppressLint("UseCompatLoadingForDrawables")
        fun isCheckedSwitch(isCheck: Boolean) {
            if (isCheck) binding.smallButton.trailingIcon =
                binding.root.context.getDrawable(R.drawable.icon)
            else binding.smallButton.trailingIcon = null
        }
        isCheckedSwitch(binding.switchShowTrailingIconSmallButton.isChecked)
        binding.switchShowTrailingIconSmallButton.setOnCheckedChangeListener { buttonView, isChecked ->
            isCheckedSwitch(isChecked)
        }
    }

}