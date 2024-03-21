package com.pedra.conexa.views.users

import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
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
class UsersViewModelTest{

    @MockK
    private lateinit var usersUseCase: UsersUseCase

    private lateinit var usersViewModel: UsersViewModel

    @get: Rule
    var rule: InstantTaskExecutorRule = InstantTaskExecutorRule()


    @Before
    fun onBefore() {
        MockKAnnotations.init(this)

        val mContextMock = mockk<Context>(relaxed = true)
        CoreModule.init(mContextMock)

        usersViewModel = UsersViewModel(usersUseCase)
        Dispatchers.setMain(Dispatchers.Unconfined)
    }

    @After
    fun onAfter(){
        Dispatchers.resetMain()
    }

    @Test
    fun getUsersList_Success_ListUserUI() = runTest {
        //GIVEN
        val list = getUserUIList()
        coEvery { UtilConnection.getInstance().checkInternetConnection() } returns true
        coEvery { usersUseCase.getAllUsers() } returns list

        //WHEN
        usersViewModel.getUsersList()

        //THEN
        assert((usersViewModel.usersListLiveData.value as ResourceState.Success<List<UserUI>>).data.first().id == list.first().id  )
    }

    @Test
    fun getUsersList_Failure_UsersNotFound() = runTest {
        //GIVEN
        coEvery { usersUseCase.getAllUsers() } returns emptyList()
        coEvery { UtilConnection.getInstance().checkInternetConnection() } returns true

        //WHEN
        usersViewModel.getUsersList()

        //THEN
        assert((usersViewModel.usersListLiveData.value as ResourceState.Failure).message == ConstantsNetwork.USERS_NOT_FOUND  )
    }

    @Test
    fun getUsersList_Failure_NotConnection() = runTest {
        //GIVEN
        coEvery { UtilConnection.getInstance().checkInternetConnection() } returns false

        //WHEN
        usersViewModel.getUsersList()

        //THEN
        assert((usersViewModel.usersListLiveData.value as ResourceState.Failure).message == ConstantsNetwork.NOT_CONNECTION  )
    }

    @Test
    fun getUsersList_Failure_UsersFailed() = runTest {
        //GIVEN
        coEvery { usersUseCase.getAllUsers() } coAnswers { throw Exception() }
        coEvery { UtilConnection.getInstance().checkInternetConnection() } returns true

        //WHEN
        usersViewModel.getUsersList()

        //THEN
        assert((usersViewModel.usersListLiveData.value as ResourceState.Failure).message == ConstantsNetwork.USERS_FAILED  )
    }

    /**
     *
     *  End of Tests
     *
     * */

    private fun getUserUIList():List<UserUI>{
        return listOf(getUserUI())
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