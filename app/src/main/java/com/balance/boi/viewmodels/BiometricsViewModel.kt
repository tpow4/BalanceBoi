package com.balance.boi.viewmodels

import android.content.Context
import androidx.biometric.BiometricPrompt
import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModel
import com.balance.boi.utils.CipherUtils.getCipher
import com.balance.boi.utils.CipherUtils.getOrCreateSecretKey
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.crypto.Cipher
import javax.inject.Inject

@HiltViewModel
class BiometricsViewModel @Inject constructor() : ViewModel() {
    private val _biometricAuthState = MutableStateFlow<BiometricAuthState>(BiometricAuthState.Idle)
    val biometricAuthState: StateFlow<BiometricAuthState> = _biometricAuthState

    fun authenticate(context: Context) {
        val promptInfo = BiometricPrompt.PromptInfo.Builder()
            .setTitle("Biometric login for my app")
            .setSubtitle("Log in using your biometric credential")
            .setNegativeButtonText("Cancel")
            .build()

        val cipher = getCipher()
        val secretKey = getOrCreateSecretKey()
        cipher.init(Cipher.ENCRYPT_MODE, secretKey)

        val biometricPrompt = BiometricPrompt(context as FragmentActivity,
            ContextCompat.getMainExecutor(context),
            object : BiometricPrompt.AuthenticationCallback() {
                override fun onAuthenticationError(errorCode: Int, errString: CharSequence) {
                    _biometricAuthState.value = BiometricAuthState.Error(errString.toString())
                }

                override fun onAuthenticationSucceeded(result: BiometricPrompt.AuthenticationResult) {
                    _biometricAuthState.value = BiometricAuthState.Success
                }

                override fun onAuthenticationFailed() {
                    _biometricAuthState.value = BiometricAuthState.Failed
                }
            })

        biometricPrompt.authenticate(promptInfo, BiometricPrompt.CryptoObject(cipher))
    }
}

sealed class BiometricAuthState {
    object Idle : BiometricAuthState()
    object Success : BiometricAuthState()
    object Failed : BiometricAuthState()
    data class Error(val message: String) : BiometricAuthState()
}
