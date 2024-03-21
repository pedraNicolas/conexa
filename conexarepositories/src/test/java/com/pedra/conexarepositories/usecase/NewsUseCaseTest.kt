package com.pedra.conexarepositories.usecase

import com.pedra.conexamodel.NewsUI
import com.pedra.conexanetwork.dtos.news.NewsDTO
import com.pedra.conexarepositories.interfaces.NewsRepositoryInterface
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*

import org.junit.Before
import org.junit.Test

class NewsUseCaseTest {

    @MockK
    private lateinit var newsRepository: NewsRepositoryInterface

    private lateinit var newsUseCase: NewsUseCase

    @Before
    fun onBefore() {
        MockKAnnotations.init(this)
        newsUseCase = NewsUseCase(newsRepository)
    }

    @Test
    fun getNewsUIList_emptyList() = runBlocking {

        //GIVEN
        coEvery { newsRepository.getAllNews() } returns emptyList()

        //WHEN
        newsUseCase.getNewsUIList()

        //THEN
        coVerify(exactly = 1) { newsRepository.getAllNews() }
        assert(newsUseCase.getNewsUIList().isEmpty())
    }

    @Test
    fun getNewsUIList_Success_NewsUIList() = runBlocking {

        //GIVEN
        val newsDTOList = getNewsDTOList()
        val newsUIList = getNewsUIList()
        coEvery { newsRepository.getAllNews() } returns newsDTOList

        //WHEN
        newsUseCase.getNewsUIList()

        //THEN
        coVerify(exactly = 1) { newsRepository.getAllNews() }
        assert(newsUseCase.getNewsUIList()[0].id == newsUIList[0].id)
    }

    @Test(expected = Exception::class)
    fun getNewsUIList_Throw() = runBlocking {
        //GIVEN
        coEvery { newsRepository.getAllNews() } coAnswers { throw Exception() }

        //WHEN
        newsUseCase.getNewsUIList()

        //THEN
        coVerify(exactly = 1) { newsRepository.getAllNews() }
    }

    /**
     *
     *  End of Tests
     *
     * */

    private fun getNewsDTOList(): List<NewsDTO> {
        return listOf(getNewsDTO())
    }

    private fun getNewsDTO(): NewsDTO{
        return NewsDTO(
            1,
            "https://dummyimage.com/200x200/FFFFFF/lorem-ipsum.png&text=jsonplaceholder.org",
            "https://dummyimage.com/800x430/FFFFFF/lorem-ipsum.png&text=jsonplaceholder.org",
            "lorem",
            "Lorem ipsum dolor sit amet, consectetur adipiscing elit.",
            "Ante taciti nulla sit libero orci sed nam. Sagittis suspendisse gravida ornare iaculis cras nullam varius ac ullamcorper. Nunc euismod hendrerit netus ligula aptent potenti. Aliquam volutpat nibh scelerisque at. Ipsum molestie phasellus euismod sagittis mauris, erat ut. Gravida morbi, sagittis blandit quis ipsum mi mus semper dictum amet himenaeos. Accumsan non congue praesent interdum habitasse turpis orci. Ante curabitur porttitor ullamcorper sagittis sem donec, inceptos cubilia venenatis ac. Augue fringilla sodales in ullamcorper enim curae; rutrum hac in sociis! Scelerisque integer varius et euismod aenean nulla. Quam habitasse risus nullam enim. Ultrices etiam viverra mattis aliquam? Consectetur velit vel volutpat eget curae;. Volutpat class mus elementum pulvinar! Nisi tincidunt volutpat consectetur. Primis morbi pulvinar est montes diam himenaeos duis elit est orci. Taciti sociis aptent venenatis dui malesuada dui justo faucibus primis consequat volutpat. Rhoncus ante purus eros nibh, id et hendrerit pellentesque scelerisque vehicula sollicitudin quam. Hac class vitae natoque tortor dolor dui praesent suspendisse. Vehicula euismod tincidunt odio platea aenean habitasse neque ad proin. Bibendum phasellus enim fames risus eget felis et sem fringilla etiam. Integer.",
            "04/02/2023 13:25:21",
            "14/03/2023 17:22:20",
            1
        )
    }

    private fun getNewsUIList(): List<NewsUI> {
        return listOf(getNewsUI())
    }

    private fun getNewsUI(): NewsUI{
        return NewsUI(
            1,
            "https://dummyimage.com/200x200/FFFFFF/lorem-ipsum.png&text=jsonplaceholder.org",
            "https://dummyimage.com/800x430/FFFFFF/lorem-ipsum.png&text=jsonplaceholder.org",
            "lorem",
            "Lorem ipsum dolor sit amet, consectetur adipiscing elit.",
            "Ante taciti nulla sit libero orci sed nam. Sagittis suspendisse gravida ornare iaculis cras nullam varius ac ullamcorper. Nunc euismod hendrerit netus ligula aptent potenti. Aliquam volutpat nibh scelerisque at. Ipsum molestie phasellus euismod sagittis mauris, erat ut. Gravida morbi, sagittis blandit quis ipsum mi mus semper dictum amet himenaeos. Accumsan non congue praesent interdum habitasse turpis orci. Ante curabitur porttitor ullamcorper sagittis sem donec, inceptos cubilia venenatis ac. Augue fringilla sodales in ullamcorper enim curae; rutrum hac in sociis! Scelerisque integer varius et euismod aenean nulla. Quam habitasse risus nullam enim. Ultrices etiam viverra mattis aliquam? Consectetur velit vel volutpat eget curae;. Volutpat class mus elementum pulvinar! Nisi tincidunt volutpat consectetur. Primis morbi pulvinar est montes diam himenaeos duis elit est orci. Taciti sociis aptent venenatis dui malesuada dui justo faucibus primis consequat volutpat. Rhoncus ante purus eros nibh, id et hendrerit pellentesque scelerisque vehicula sollicitudin quam. Hac class vitae natoque tortor dolor dui praesent suspendisse. Vehicula euismod tincidunt odio platea aenean habitasse neque ad proin. Bibendum phasellus enim fames risus eget felis et sem fringilla etiam. Integer.",
            "04/02/2023 13:25:21",
            "14/03/2023 17:22:20",
            1
        )
    }
}