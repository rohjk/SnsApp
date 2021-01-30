package com.jake.bucketplace.snsapp.signup

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.observe
import androidx.navigation.fragment.findNavController
import com.jake.bucketplace.snsapp.SnsApplication
import com.jake.bucketplace.snsapp.databinding.FragmentSignUpBinding
import javax.inject.Inject

class SignUpFragment : Fragment() {

    private lateinit var binding: FragmentSignUpBinding
    @Inject
    lateinit var viewModel: SignUpViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (activity?.application as SnsApplication).appComponent.signUpComponent().create().inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        this.binding = FragmentSignUpBinding.inflate(inflater, container, false)
        binding.apply {
            lifecycleOwner = this@SignUpFragment
            fragment = this@SignUpFragment
            viewModel = this@SignUpFragment.viewModel
        }

        subscribe()
        return binding.root
    }

    fun subscribe() {
        viewModel.isSignIn.observe(viewLifecycleOwner) { isSignUp ->
            if(isSignUp) {
                this.findNavController().navigateUp()
            }
        }
    }

    fun singUp() {
        binding.apply {
            val nickName = signupNickName.text.toString()
            val introduction = signupIntroduction.text.toString()
            val password = signupPassword.text.toString()
            viewModel?.signUp(nickName, introduction, password)
        }
    }

}