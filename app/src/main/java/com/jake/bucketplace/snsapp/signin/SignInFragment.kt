package com.jake.bucketplace.snsapp.signin

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.lifecycle.observe
import androidx.navigation.fragment.findNavController
import com.jake.bucketplace.snsapp.R
import com.jake.bucketplace.snsapp.SnsApplication
import com.jake.bucketplace.snsapp.databinding.FragmentSignInBinding
import javax.inject.Inject

class SignInFragment : Fragment() {

    private lateinit var binding: FragmentSignInBinding
    @Inject
    lateinit var viewModel: SignInViewModel
    private lateinit var manager: InputMethodManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (activity?.application as SnsApplication).appComponent.signInComponent().create().inject(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        this.binding = FragmentSignInBinding.inflate(inflater, container, false)
        binding.apply {
            lifecycleOwner = this@SignInFragment
            fragment = this@SignInFragment
            viewModel = this@SignInFragment.viewModel
        }

        this.manager = context?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager

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

    override fun onDestroy() {
        binding?.unbind()
        super.onDestroy()
    }

    fun singIn() {
        binding.apply {
            val nickName = signInNickName.text.toString()
            val password = signInPassword.text.toString()
            viewModel?.signIn(nickName, password)
            manager?.hideSoftInputFromWindow(root.windowToken, InputMethodManager.HIDE_NOT_ALWAYS)
        }
    }

}