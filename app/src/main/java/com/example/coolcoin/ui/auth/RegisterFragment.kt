package com.example.coolcoin.ui.auth

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.coolcoin.R
import com.example.coolcoin.databinding.FragmentRegisterBinding
import com.example.coolcoin.util.extensions.text
import com.example.coolcoin.util.extensions.toast
import com.google.firebase.auth.FirebaseAuth

class RegisterFragment : Fragment(), View.OnClickListener {

    private lateinit var binding: FragmentRegisterBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRegisterBinding.inflate(inflater, container, false).also {
            it.handler = this
        }
        return binding.root
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.imageViewBack -> {
                findNavController().popBackStack()
            }
            R.id.buttonRegister -> {
                val email = binding.editTextEmail.text()
                val password = binding.editTextPassword.text()
                binding.progressBarLoading.isVisible = true

                FirebaseAuth.getInstance()
                    .createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(requireActivity()) { task ->
                        if (task.isSuccessful) {
                            toast("Cool :D you become one of our awesome users")
                            findNavController().popBackStack()
                        } else {
                            binding.progressBarLoading.isVisible = false
                            toast("Something went wrong")
                        }
                    }
            }
        }
    }
}
