//package com.kazemieh.designsystem.sample
//
//import android.annotation.SuppressLint
//import android.transition.AutoTransition
//import android.transition.TransitionManager
//import android.util.Log
//import android.view.View
//import android.widget.AdapterView
//import android.widget.ArrayAdapter
//import com.kazemieh.designsystem.libdesign.buttons.base.CornerRadius
//import com.kazemieh.designsystem.libdesign.buttons.normal.small.ConfigurationSmallButton
//import com.kazemieh.designsystem.libdesign.buttons.normal.small.StateSmallButton
//import com.kazemieh.designsystem.libdesign.buttons.normal.small.StyleSmallButton
//import com.kazemieh.designsystem.sample.databinding.ActivityMainBinding
//import kotlinx.coroutines.DelicateCoroutinesApi
//
//
//class IconSmallButtonSample(private val binding: ActivityMainBinding) {
//
//    companion object {
//        val TAG = "IconSmallButtonSample"
//    }
//
//    fun iconSmallButtonConfig() {
//
//        // show
//        showView()
//
//        // corner
//        cornerRadiusConfig()
//
//
//        //configuration
//        configurationConfig()
//
//
//        //spinnerStyle
//        spinnerStyleConfig()
//
//
//        //spinnerState
//        spinnerStateConfig()
//
//
//        //leadingIcon
//        leadingIconConfig()
//
//        binding.incIconSmallButtonSample.iconSmallButton.setOnClickListener {
//            Log.d(TAG, "smallButtonConfig: clicked")
//        }
//
//
//    }
//
//    @OptIn(DelicateCoroutinesApi::class)
//    @SuppressLint("UseCompatLoadingForDrawables")
//    private fun showView() {
//
//        binding.incIconSmallButtonSample.tvIconSmallButton.setOnClickListener {
//            if (binding.incIconSmallButtonSample.llIconSmallButton.visibility == View.VISIBLE) {
//                TransitionManager.beginDelayedTransition(
//                    binding.incIconSmallButtonSample.parentLinearLayoutIconSmallButton,
//                    AutoTransition()
//                )
//                binding.incIconSmallButtonSample.llIconSmallButton.visibility = View.GONE
//                binding.incIconSmallButtonSample.tvIconSmallButton.setCompoundDrawablesWithIntrinsicBounds(
//                    0,
//                    0,
//                    R.drawable.round_expand_more_24,
//                    0
//                )
//
//            } else {
//                TransitionManager.beginDelayedTransition(
//                    binding.incIconSmallButtonSample.parentLinearLayoutIconSmallButton,
//                    AutoTransition()
//                )
//                binding.incIconSmallButtonSample.llIconSmallButton.visibility = View.VISIBLE
//                binding.incIconSmallButtonSample.tvIconSmallButton.setCompoundDrawablesWithIntrinsicBounds(
//                    0,
//                    0,
//                    R.drawable.round_expand_less_24,
//                    0
//                )
//
//            }
//        }
//
//    }
//
//
//    private fun cornerRadiusConfig() {
//        fun isCheckedSwitch(isCheck: Boolean) {
//            if (isCheck) binding.incIconSmallButtonSample.iconSmallButton.cornerRadius =
//                CornerRadius.ROUND_100
//            else binding.incIconSmallButtonSample.iconSmallButton.cornerRadius = CornerRadius.ROUND_8
//            // binding.incIconSmallButtonSample.smallButton.cornerRadius = 25 // number
//        }
//        isCheckedSwitch(binding.incIconSmallButtonSample.switchRoundnessIconSmallButton.isChecked)
//        binding.incIconSmallButtonSample.switchRoundnessIconSmallButton.setOnCheckedChangeListener { buttonView, isChecked ->
//            isCheckedSwitch(isChecked)
//        }
//    }
//
//
//    private fun configurationConfig() {
//        val configurationList = arrayOf("Primary", "Error")
//        val configurationAdapter =
//            ArrayAdapter(
//                binding.incIconSmallButtonSample.root.context,
//                android.R.layout.simple_spinner_item,
//                configurationList
//            )
//        configurationAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
//        binding.incIconSmallButtonSample.spinnerConfigurationIconSmallButton.adapter = configurationAdapter
//        binding.incIconSmallButtonSample.spinnerConfigurationIconSmallButton.onItemSelectedListener =
//            object :
//                AdapterView.OnItemSelectedListener {
//                override fun onItemSelected(
//                    parent: AdapterView<*>?,
//                    view: View?,
//                    position: Int,
//                    id: Long
//                ) {
//                    if (position == 0) {
//                        binding.incIconSmallButtonSample.iconSmallButton.configuration =
//                            ConfigurationSmallButton.PRIMARY
//                    } else {
//                        binding.incIconSmallButtonSample.iconSmallButton.configuration =
//                            ConfigurationSmallButton.ERROR
//                    }
//
//                }
//
//                override fun onNothingSelected(parent: AdapterView<*>?) {
//                }
//
//            }
//    }
//
//    private fun spinnerStyleConfig() {
//        val styleList = arrayOf("FIELD", "OUTLINE", "TEXT", "ELEVATED", "TONAL")
//        val styleAdapter =
//            ArrayAdapter(
//                binding.incIconSmallButtonSample.root.context,
//                android.R.layout.simple_spinner_item,
//                styleList
//            )
//        styleAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
//        binding.incIconSmallButtonSample.spinnerStyleIconSmallButton.adapter = styleAdapter
//        binding.incIconSmallButtonSample.spinnerStyleIconSmallButton.onItemSelectedListener =
//            object : AdapterView.OnItemSelectedListener {
//                override fun onItemSelected(
//                    parent: AdapterView<*>?,
//                    view: View?,
//                    position: Int,
//                    id: Long
//                ) {
//                    when (position) {
//                        0 -> {
//                            binding.incIconSmallButtonSample.iconSmallButton.style = StyleSmallButton.FIELD
//                        }
//
//                        1 -> {
//                            binding.incIconSmallButtonSample.iconSmallButton.style =
//                                StyleSmallButton.OUTLINE
//                        }
//
//                        2 -> {
//                            binding.incIconSmallButtonSample.iconSmallButton.style =
//                                StyleSmallButton.STANDARD
//                        }
//
//                        3 -> {
//                            binding.incIconSmallButtonSample.iconSmallButton.style =
//                                StyleSmallButton.ELEVATED
//                        }
//
//                        4 -> {
//                            binding.incIconSmallButtonSample.iconSmallButton.style = StyleSmallButton.TONAL
//                        }
//                    }
//
//                }
//
//                override fun onNothingSelected(parent: AdapterView<*>?) {
//                }
//
//            }
//    }
//
//    private fun spinnerStateConfig() {
//        val styleList = arrayOf("ENABLE", "DISABLE", "LOADING")
//        val styleAdapter =
//            ArrayAdapter(
//                binding.incIconSmallButtonSample.root.context,
//                android.R.layout.simple_spinner_item,
//                styleList
//            )
//        styleAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
//        binding.incIconSmallButtonSample.spinnerStateIconSmallButton.adapter = styleAdapter
//        binding.incIconSmallButtonSample.spinnerStateIconSmallButton.onItemSelectedListener =
//            object : AdapterView.OnItemSelectedListener {
//                override fun onItemSelected(
//                    parent: AdapterView<*>?,
//                    view: View?,
//                    position: Int,
//                    id: Long
//                ) {
//                    when (position) {
//                        0 -> {
//                            binding.incIconSmallButtonSample.iconSmallButton.state = StateSmallButton.ENABLE
//                        }
//
//                        1 -> {
//                            binding.incIconSmallButtonSample.iconSmallButton.state =
//                                StateSmallButton.DISABLE
//                        }
//
//                        2 -> {
//                            binding.incIconSmallButtonSample.iconSmallButton.state =
//                                StateSmallButton.LOADING
//                        }
//                    }
//
//                }
//
//                override fun onNothingSelected(parent: AdapterView<*>?) {
//                }
//
//            }
//    }
//
//
//    private fun leadingIconConfig() {
//        @SuppressLint("UseCompatLoadingForDrawables")
//        fun isCheckedSwitch(isCheck: Boolean) {
//            if (isCheck) binding.incIconSmallButtonSample.iconSmallButton.leadingIcon =
//                binding.incIconSmallButtonSample.root.context.getDrawable(R.drawable.icon)
//            else binding.incIconSmallButtonSample.iconSmallButton.leadingIcon = null
//        }
//        isCheckedSwitch(binding.incIconSmallButtonSample.switchShowLeadingIconIconSmallButton.isChecked)
//        binding.incIconSmallButtonSample.switchShowLeadingIconIconSmallButton.setOnCheckedChangeListener { buttonView, isChecked ->
//            isCheckedSwitch(isChecked)
//        }
//    }
//
//}