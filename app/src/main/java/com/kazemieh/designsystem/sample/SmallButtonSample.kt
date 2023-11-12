package com.kazemieh.designsystem.sample

import android.annotation.SuppressLint
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import com.kazemieh.designsystem.libdesign.buttons.base.CornerRadius
import com.kazemieh.designsystem.libdesign.buttons.button.small.ConfigurationSmallButton
import com.kazemieh.designsystem.libdesign.buttons.button.small.StateSmallButton
import com.kazemieh.designsystem.libdesign.buttons.button.small.StyleSmallButton
import com.kazemieh.designsystem.sample.databinding.ActivityMainBinding


class SmallButtonSample(private val binding: ActivityMainBinding) {

    companion object {
        val TAG = "SmallButtonSample"
    }
    fun smallButtonConfig() {

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

        binding.incSmallButtonSample.smallButton.setOnClickListener {
            Log.d(TAG, "smallButtonConfig: clicked")
        }


    }


    private fun cornerRadiusConfig() {
        fun isCheckedSwitch(isCheck: Boolean) {
            if (isCheck) binding.incSmallButtonSample.smallButton.cornerRadius = CornerRadius.ROUND_100
            else binding.incSmallButtonSample.smallButton.cornerRadius = CornerRadius.ROUND_8
        }
        isCheckedSwitch(binding.incSmallButtonSample.switchRoundnessSmallButton.isChecked)
        binding.incSmallButtonSample.switchRoundnessSmallButton.setOnCheckedChangeListener { buttonView, isChecked ->
            isCheckedSwitch(isChecked)
        }
    }


    private fun configurationConfig() {
        val configurationList = arrayOf("Primary", "Error")
        val configurationAdapter =
            ArrayAdapter(binding.incSmallButtonSample.root.context, android.R.layout.simple_spinner_item, configurationList)
        configurationAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.incSmallButtonSample.spinnerConfigurationSmallButton.adapter = configurationAdapter
        binding.incSmallButtonSample.spinnerConfigurationSmallButton.onItemSelectedListener = object :
            AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                if (position == 0) {
                    binding.incSmallButtonSample.smallButton.configuration = ConfigurationSmallButton.PRIMARY
                } else {
                    binding.incSmallButtonSample.smallButton.configuration = ConfigurationSmallButton.ERROR
                }

            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
            }

        }
    }

    private fun spinnerStyleConfig() {
        val styleList = arrayOf("FIELD", "OUTLINE", "TEXT", "ELEVATED", "TONAL")
        val styleAdapter =
            ArrayAdapter(binding.incSmallButtonSample.root.context, android.R.layout.simple_spinner_item, styleList)
        styleAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.incSmallButtonSample.spinnerStyleSmallButton.adapter = styleAdapter
        binding.incSmallButtonSample.spinnerStyleSmallButton.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    when (position) {
                        0 -> {
                            binding.incSmallButtonSample.smallButton.style = StyleSmallButton.FIELD
                        }

                        1 -> {
                            binding.incSmallButtonSample.smallButton.style = StyleSmallButton.OUTLINE
                        }

                        2 -> {
                            binding.incSmallButtonSample.smallButton.style = StyleSmallButton.STANDARD
                        }

                        3 -> {
                            binding.incSmallButtonSample.smallButton.style = StyleSmallButton.ELEVATED
                        }

                        4 -> {
                            binding.incSmallButtonSample.smallButton.style = StyleSmallButton.TONAL
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
            ArrayAdapter(binding.incSmallButtonSample.root.context, android.R.layout.simple_spinner_item, styleList)
        styleAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.incSmallButtonSample.spinnerStateSmallButton.adapter = styleAdapter
        binding.incSmallButtonSample.spinnerStateSmallButton.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    when (position) {
                        0 -> {
                            binding.incSmallButtonSample.smallButton.state = StateSmallButton.ENABLE
                        }

                        1 -> {
                            binding.incSmallButtonSample.smallButton.state = StateSmallButton.DISABLE
                        }

                        2 -> {
                            binding.incSmallButtonSample.smallButton.state = StateSmallButton.LOADING
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
            if (isCheck) binding.incSmallButtonSample.smallButton.leadingIcon = binding.incSmallButtonSample.root.context.getDrawable(
                R.drawable.icon)
            else binding.incSmallButtonSample.smallButton.leadingIcon = null
        }
        isCheckedSwitch(binding.incSmallButtonSample.switchShowLeadingIconSmallButton.isChecked)
        binding.incSmallButtonSample.switchShowLeadingIconSmallButton.setOnCheckedChangeListener { buttonView, isChecked ->
            isCheckedSwitch(isChecked)
        }
    }

    private fun trailingIconConfig() {
        @SuppressLint("UseCompatLoadingForDrawables")
        fun isCheckedSwitch(isCheck: Boolean) {
            if (isCheck) binding.incSmallButtonSample.smallButton.trailingIcon = binding.incSmallButtonSample.root.context.getDrawable(
                R.drawable.icon)
            else binding.incSmallButtonSample.smallButton.trailingIcon = null
        }
        isCheckedSwitch(binding.incSmallButtonSample.switchShowTrailingIconSmallButton.isChecked)
        binding.incSmallButtonSample.switchShowTrailingIconSmallButton.setOnCheckedChangeListener { buttonView, isChecked ->
            isCheckedSwitch(isChecked)
        }
    }

}