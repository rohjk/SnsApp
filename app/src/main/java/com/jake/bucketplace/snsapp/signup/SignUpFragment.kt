package com.jake.bucketplace.snsapp.signup

import android.content.Context.INPUT_METHOD_SERVICE
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.core.content.ContextCompat.getSystemService
import androidx.lifecycle.observe
import androidx.navigation.fragment.findNavController
import com.jake.bucketplace.snsapp.SnsApplication
import com.jake.bucketplace.snsapp.databinding.FragmentSignUpBinding
import javax.inject.Inject

class SignUpFragment : Fragment() {

    private lateinit var binding: FragmentSignUpBinding
    @Inject
    lateinit var viewModel: SignUpViewModel
    private lateinit var manager: InputMethodManager

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

        this.manager = context?.getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager

        subscribe()
        return binding.root
    }

    override fun onDestroy() {
        binding?.unbind()
        super.onDestroy()
    }

    fun subscribe() {
        viewModel.apply {
            isSignIn.observe(viewLifecycleOwner) { isSignUp ->
                if(isSignUp) {
                    this@SignUpFragment.findNavController().navigateUp()
                }
            }
            onError.observe(viewLifecycleOwner) { errorMessage ->
                Toast.makeText(context,errorMessage, Toast.LENGTH_SHORT).show()
            }
        }
    }

    fun singUp() {
        binding.apply {
            val nickName = signupNickName.text.toString()
            val introduction = signupIntroduction.text.toString()
            val password = signupPassword.text.toString()
            viewModel?.signUp(nickName, introduction, password)
            manager?.hideSoftInputFromWindow(root.windowToken, InputMethodManager.HIDE_NOT_ALWAYS)
        }

    }

}