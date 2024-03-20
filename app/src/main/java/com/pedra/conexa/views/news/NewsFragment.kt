package com.pedra.conexa.views.news

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.pedra.conexa.R
import com.pedra.conexa.databinding.FragmentNewsBinding
import com.pedra.conexa.utils.GenericRecyclerAdapter
import com.pedra.conexa.utils.ViewHolderType
import com.pedra.conexacomponents.interfaces.OnTextChangeListener
import com.pedra.conexamodel.NewsUI
import com.pedra.core.ConstantsConexa
import com.pedra.core.utils.UtilDate
import java.util.Locale

class NewsFragment : Fragment(), OnTextChangeListener {

    private lateinit var binding: FragmentNewsBinding
    private val viewModel: NewsViewModel by viewModels()
    private lateinit var newsAdapter: GenericRecyclerAdapter<NewsUI, NewsUI>
    private var newsUIList = listOf<NewsUI>()
    private val utilDate = UtilDate.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FragmentNewsBinding.inflate(layoutInflater)

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
        setUpComponents()
    }

    private fun setUpComponents() {
        with(binding) {
            searchNews.clearFocus()
            searchNews.setOnTextChangedListener(this@NewsFragment)
            tvDate.text = utilDate.dateToString(
                utilDate.getCurrentDate(),
                UtilDate.COMPLETE_DATE_PATTERN,
                Locale("es", "ES")
            )
        }
    }

    private fun setUpRecyclerView() {
        binding.rvNews.layoutManager = LinearLayoutManager(requireContext())

        newsAdapter = GenericRecyclerAdapter(
            list = newsUIList,
            viewHolderType = ViewHolderType.NewsViewHolder,
            onPrimaryClick = {
                val bundle = Bundle()
                bundle.putString(ConstantsConexa.TITLE, it.title)
                bundle.putString(ConstantsConexa.BODY, it.content)
                bundle.putString(ConstantsConexa.IMAGE, it.image)
                bundle.putInt(ConstantsConexa.USERID, it.userId)
                bundle.putString(ConstantsConexa.UPDATED_AT, it.updatedAt)
                findNavController().navigate(R.id.action_newsFragment_to_selectedNewFragment, bundle)
            })

        binding.rvNews.adapter = newsAdapter
    }

    private fun populateList(list: List<NewsUI>) {
        newsUIList = list
        newsAdapter.updateList(list)
    }

    private fun setUpViewModel() {
        viewModel.getNewsUIList()
    }

    private fun setUpObservers() {
        viewModel.newsLiveData.observe(viewLifecycleOwner) {
            populateList(it)
        }
    }

    override fun onTextChanged(query: String?) {
        if (!query.isNullOrBlank()) {
           val filteredList = newsUIList.filter { it.title.contains(query, true) || it.content.contains(query, true)}
            newsAdapter.updateList(filteredList)
        } else {
            newsAdapter.updateList(newsUIList)
        }
    }
}