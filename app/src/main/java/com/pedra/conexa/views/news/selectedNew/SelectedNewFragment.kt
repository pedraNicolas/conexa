package com.pedra.conexa.views.news.selectedNew

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.pedra.conexa.R
import com.pedra.conexa.databinding.FragmentSelectedNewBinding
import com.pedra.conexa.views.news.NewsViewModel
import com.pedra.core.ConstantsConexa
import com.pedra.core.utils.ResourceState
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SelectedNewFragment : Fragment() {

    private lateinit var binding: FragmentSelectedNewBinding
    private val viewModel: NewsViewModel by viewModels()

    private var title: String = ""
    private var body: String = ""
    private var image: String = ""
    private var userId: Int = 0
    private var updatedAt: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FragmentSelectedNewBinding.inflate(layoutInflater)
        getBundleArguments()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = binding.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpComponents()
        setUpObservers()
        setUpViewModel()
    }

    private fun setUpViewModel() {
        if(userId != 0) viewModel.getUSerById(userId.toString())
    }

    private fun setUpObservers() {
        viewModel.userLiveData.observe(viewLifecycleOwner){
            with(binding){
                    when (it) {
                        is ResourceState.Loading -> {
                            pbLoading.visibility = View.VISIBLE
                            llView.visibility = View.GONE
                        }
                        is ResourceState.Success -> {
                            pbLoading.visibility = View.GONE
                            llView.visibility = View.VISIBLE
                            val user = it.data
                            val name = "${user.firstName} ${user.lastName}"
                            tvAuthor.text = name
                            tvCompany.text = user.companyName
                        }

                        is ResourceState.Failure -> {
                            pbLoading.visibility = View.GONE
                            llView.visibility = View.VISIBLE
                        }
                    }
            }
        }
    }

    private fun getBundleArguments() {
        arguments?.getString(ConstantsConexa.TITLE)?.let { title = it }
        arguments?.getString(ConstantsConexa.BODY)?.let { body = it }
        arguments?.getString(ConstantsConexa.IMAGE)?.let { image = it }
        arguments?.getInt(ConstantsConexa.USERID)?.let { userId = it }
        arguments?.getString(ConstantsConexa.UPDATED_AT)?.let { updatedAt = it }
    }

    private fun setUpComponents() {
        with(binding){
            tvTitle.text = title
            tvDate.text = updatedAt
            tvBody.text = body

            Glide.with(this@SelectedNewFragment).load(image)
                .placeholder(R.drawable.progress_animation).into(ivImageNew)

            topAppBar.setNavigationOnClickListener {
                findNavController().popBackStack()
            }
        }
    }
}