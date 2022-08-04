package com.tribore.movieconstanta.domain.usecase

import com.tribore.movieconstanta.domain.models.Actor
import com.tribore.movieconstanta.domain.models.Movie
import junit.framework.Assert.assertEquals
import com.tribore.movieconstanta.domain.repository.MovieRepository
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever


class GetMoviesUseCaseTest {

    private val testRepository = mock<MovieRepository>()

    @Test
    fun `should return the data as in repository`() = runBlocking {

        val testData = listOf(
            Movie(
                actors = listOf(Actor("Сергей Витальевич Безруков"), Actor("Константин Юрьевич Хабенский")),
                directorName = "Алексей Октябринович Балабанов",
                releaseYear = 2000,
                title = "От чего так в роси"
            ),
            Movie(
                actors = listOf(Actor("Дани́ла Вале́рьевич Козло́вский")),
                directorName = "Тимур Нуруахитович Бекмамбетов",
                releaseYear = 2002,
                title = "Бабки Бабки"
            )
        )

        whenever(testRepository.getMovies()).thenReturn(testData)

        val useCase = GetMoviesUseCase(repository = testRepository)
        val actual = useCase.invoke()
        val expected = listOf(
            Movie(
                actors = listOf(Actor("Сергей Витальевич Безруков"), Actor("Константин Юрьевич Хабенский")),
                directorName = "Алексей Октябринович Балабанов",
                releaseYear = 2000,
                title = "От чего так в роси"
            ),
            Movie(
                actors = listOf(Actor("Дани́ла Вале́рьевич Козло́вский")),
                directorName = "Тимур Нуруахитович Бекмамбетов",
                releaseYear = 2002,
                title = "Бабки Бабки"
            )
        )

        assertEquals(expected, actual)
    }

}

