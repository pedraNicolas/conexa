package com.pedra.conexa.views.users

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.pedra.conexa.R
import com.pedra.conexa.databinding.FragmentUsersBinding
import com.pedra.conexa.utils.GenericRecyclerAdapter
import com.pedra.conexa.utils.ViewHolderType
import com.pedra.conexa.views.news.NewsViewModel
import com.pedra.conexamodel.NewsUI
import com.pedra.conexamodel.UserUI
import com.pedra.core.ConstantsConexa
import com.pedra.core.utils.ResourceState
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UsersFragment : Fragment() {

    private lateinit var binding: FragmentUsersBinding
    private val viewModel: UsersViewModel by viewModels()
    private lateinit var usersAdapter: GenericRecyclerAdapter<UserUI, UserUI>
    private var userUIList = listOf<UserUI>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FragmentUsersBinding.inflate(layoutInflater)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = binding.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpRecyclerView()
        setUpObservers()
        setUpViewModel()
    }

    private fun setUpViewModel() {
        viewModel.getUsersList()
    }

    private fun setUpObservers() {
        viewModel.usersListLiveData.observe(viewLifecycleOwner){
            with(binding){
                when (it) {
                    is ResourceState.Loading -> {
                        pbLoading.visibility = View.VISIBLE
                        rvUsers.visibility = View.GONE
                    }
                    is ResourceState.Success -> {
                        pbLoading.visibility = View.GONE
                        rvUsers.visibility = View.VISIBLE
                        populateList(it.data)
                    }

                    is ResourceState.Failure -> {
                        pbLoading.visibility = View.GONE
                        rvUsers.visibility = View.VISIBLE
                    }
                }
            }

        }
    }

    private fun populateList(list: List<UserUI>) {
        userUIList = list
        usersAdapter.updateList(list)
    }

    private fun setUpRecyclerView() {
        binding.rvUsers.layoutManager = LinearLayoutManager(requireContext())

        usersAdapter = GenericRecyclerAdapter(
            list = userUIList,
            viewHolderType = ViewHolderType.UsersViewHolder,
            onPrimaryClick = {
                val bundle = Bundle()
                bundle.putString(ConstantsConexa.LAT, it.lat)
                bundle.putString(ConstantsConexa.LONG, it.lng)
                findNavController().navigate(R.id.action_usersFragment_to_selectedUserFragment, bundle)
            })

        binding.rvUsers.adapter = usersAdapter
    }
}