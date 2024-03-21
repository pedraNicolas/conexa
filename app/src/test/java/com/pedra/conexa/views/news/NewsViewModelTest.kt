package com.pedra.conexa.views.news

import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.pedra.conexamodel.NewsUI
import com.pedra.conexamodel.UserUI
import com.pedra.conexarepositories.usecase.NewsUseCase
import com.pedra.conexarepositories.usecase.UsersUseCase
import com.pedra.core.ConstantsNetwork
import com.pedra.core.CoreModule
import com.pedra.core.utils.ResourceState
import com.pedra.core.utils.UtilConnection
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class NewsViewModelTest {

    private val USER_ID = "1"

    @MockK
    private lateinit var usersUseCase: UsersUseCase

    @MockK
    private lateinit var newsUseCase: NewsUseCase

    private lateinit var newsViewModel: NewsViewModel

    @get: Rule
    var rule: InstantTaskExecutorRule = InstantTaskExecutorRule()


    @Before
    fun onBefore() {
        MockKAnnotations.init(this)

        val mContextMock = mockk<Context>(relaxed = true)
        CoreModule.init(mContextMock)

        newsViewModel = NewsViewModel(usersUseCase, newsUseCase)
        Dispatchers.setMain(Dispatchers.Unconfined)
    }

    @After
    fun onAfter() {
        Dispatchers.resetMain()
    }

    @Test
    fun getNewsUIList_Success_ListNewsUI() = runTest {
        //GIVEN
        val list = getNewsUIList()
        coEvery { UtilConnection.getInstance().checkInternetConnection() } returns true
        coEvery { newsUseCase.getNewsUIList() } returns list

        //WHEN
        newsViewModel.getNewsUIList()

        //THEN
        assert((newsViewModel.newsLiveData.value as ResourceState.Success<List<NewsUI>>).data.first().id == list.first().id  )
    }

    @Test
    fun getNewsUIList_Failure_NewsNotFound() = runTest {
        //GIVEN
        coEvery { newsUseCase.getNewsUIList() } returns emptyList()
        coEvery { UtilConnection.getInstance().checkInternetConnection() } returns true

        //WHEN
        newsViewModel.getNewsUIList()

        //THEN
        assert((newsViewModel.newsLiveData.value as ResourceState.Failure).message == ConstantsNetwork.NEWS_NOT_FOUND  )
    }

    @Test
    fun getNewsUIList_Failure_NotConnection() = runTest {
        //GIVEN
        coEvery { UtilConnection.getInstance().checkInternetConnection() } returns false

        //WHEN
        newsViewModel.getNewsUIList()

        //THEN
        assert((newsViewModel.newsLiveData.value as ResourceState.Failure).message == ConstantsNetwork.NOT_CONNECTION  )
    }

    @Test
    fun getNewsUIList_Failure_NewsFailed() = runTest {
        //GIVEN
        coEvery { newsUseCase.getNewsUIList() } coAnswers { throw Exception() }
        coEvery { UtilConnection.getInstance().checkInternetConnection() } returns true

        //WHEN
        newsViewModel.getNewsUIList()

        //THEN
        assert((newsViewModel.newsLiveData.value as ResourceState.Failure).message == ConstantsNetwork.NEWS_FAILED  )
    }

    @Test
    fun getUSerById_Success_UserUI() = runTest {
        //GIVEN
        val userUI = getUserUI()
        coEvery { UtilConnection.getInstance().checkInternetConnection() } returns true
        coEvery { usersUseCase.getUserById(USER_ID) } returns userUI

        //WHEN
        newsViewModel.getUSerById(USER_ID)

        //THEN
        assert((newsViewModel.userLiveData.value as ResourceState.Success<UserUI>).data.id == userUI.id  )
    }

    @Test
    fun getUSerById_Failure_UserNotFound() = runTest {
        //GIVEN
        coEvery { usersUseCase.getUserById(USER_ID) } returns null
        coEvery { UtilConnection.getInstance().checkInternetConnection() } returns true

        //WHEN
        newsViewModel.getUSerById(USER_ID)

        //THEN
        assert((newsViewModel.userLiveData.value as ResourceState.Failure).message == ConstantsNetwork.USERS_NOT_FOUND  )
    }

    @Test
    fun getUSerById_Failure_NotConnection() = runTest {
        //GIVEN
        coEvery { UtilConnection.getInstance().checkInternetConnection() } returns false

        //WHEN
        newsViewModel.getUSerById(USER_ID)

        //THEN
        assert((newsViewModel.userLiveData.value as ResourceState.Failure).message == ConstantsNetwork.NOT_CONNECTION  )
    }

    @Test
    fun getUSerById_Failure_UserFailed() = runTest {
        //GIVEN
        coEvery { usersUseCase.getUserById(USER_ID) } coAnswers { throw Exception() }
        coEvery { UtilConnection.getInstance().checkInternetConnection() } returns true

        //WHEN
        newsViewModel.getUSerById(USER_ID)

        //THEN
        assert((newsViewModel.userLiveData.value as ResourceState.Failure).message == ConstantsNetwork.USERS_FAILED  )
    }


    /**
     *
     *  End of Tests
     *
     * */
    private fun getNewsUIList(): List<NewsUI> {
        return listOf(getNewsUI())
    }

    private fun getNewsUI(): NewsUI {
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

    private fun getUserUI(): UserUI {
        return UserUI(
            1,
            "John",
            "Doe",
            "johndoe@example.com",
            "42.1234",
            "-71.2345",
            "ABC Company",
        )
    }
}