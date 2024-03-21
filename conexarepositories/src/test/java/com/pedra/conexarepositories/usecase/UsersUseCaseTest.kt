package com.pedra.conexarepositories.usecase

import com.pedra.conexamodel.UserUI
import com.pedra.conexanetwork.dtos.users.AddressDTO
import com.pedra.conexanetwork.dtos.users.CompanyDTO
import com.pedra.conexanetwork.dtos.users.GeoDTO
import com.pedra.conexanetwork.dtos.users.UserDTO
import com.pedra.conexarepositories.interfaces.UsersRepositoryInterface
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class UsersUseCaseTest {

    private val USER_ID = 1

    @MockK
    private lateinit var usersRepository: UsersRepositoryInterface

    private lateinit var usersUseCase: UsersUseCase

    @Before
    fun onBefore() {
        MockKAnnotations.init(this)
        usersUseCase = UsersUseCase(usersRepository)
    }

    @Test
    fun getAllUsers_emptyList() = runBlocking {
        //GIVEN
        coEvery { usersRepository.getAllUsers() } returns emptyList()

        //WHEN
        usersUseCase.getAllUsers()

        //THEN
        coVerify(exactly = 1) { usersRepository.getAllUsers() }
        assert(usersUseCase.getAllUsers().isEmpty())
    }

    @Test
    fun getAllUsers_Success_UIList() = runBlocking {
        //GIVEN
        val listUI = getUserUIList()
        val listDTO = getUsersDTOList()
        coEvery { usersRepository.getAllUsers() } returns listDTO

        //WHEN
        usersUseCase.getAllUsers()

        //THEN
        coVerify(exactly = 1) { usersRepository.getAllUsers() }
        assert(usersUseCase.getAllUsers()[0].companyName == listUI[0].companyName)
    }

    @Test(expected = Exception::class)
    fun getAllUsers_Throw() = runBlocking {
        //GIVEN
        coEvery { usersRepository.getAllUsers() } coAnswers { throw Exception() }

        //WHEN
        usersUseCase.getAllUsers()

        //THEN
        coVerify(exactly = 1) { usersRepository.getAllUsers() }
    }

    @Test
    fun getUserByID_returnsNull() = runBlocking {
        //GIVEN
        coEvery { usersRepository.getUserById(USER_ID.toString()) } returns null

        //WHEN
        usersUseCase.getUserById(USER_ID.toString())

        //THEN
        coVerify(exactly = 1) { usersRepository.getUserById(USER_ID.toString()) }
        assert(usersUseCase.getUserById(USER_ID.toString()) == null)
    }

    @Test
    fun getUserByID_returnsUser() = runBlocking {
        //GIVEN
        val userDTO = getUserDTO()
        val userUI = getUserUI()
        coEvery { usersRepository.getUserById(USER_ID.toString()) } returns userDTO

        //WHEN
        usersUseCase.getUserById(USER_ID.toString())

        //THEN
        coVerify(exactly = 1) { usersRepository.getUserById(USER_ID.toString()) }
        assert(usersUseCase.getUserById(USER_ID.toString())?.id == userUI.id)
    }

    @Test(expected = Exception::class)
    fun getUserByID_Throw() = runBlocking {
        //GIVEN
        coEvery { usersRepository.getUserById(USER_ID.toString()) } coAnswers { throw Exception() }

        //WHEN
        usersUseCase.getUserById(USER_ID.toString())

        //THEN
        coVerify(exactly = 1) { usersRepository.getUserById(USER_ID.toString()) }
    }



    /**
     *
     *  End of Tests
     *
     * */

    private fun getUsersDTOList(): List<UserDTO> {
        return listOf(getUserDTO())
    }

    private fun getUserDTO(): UserDTO {
        val geoDTO = GeoDTO("42.1234", "-71.2345")
        val addressDTO = AddressDTO("Anytown", geoDTO, "123 Main Street", "Apt. 4", "12345-6789")
        val companyDTO = CompanyDTO("ABC Company")
        return UserDTO(
            addressDTO,
            "1973-01-22",
            "johndoe@example.com",
            "John",
            1,
            "Doe",
            "(555) 555-1234",
            "www.johndoe.com",
            companyDTO
        )
    }

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