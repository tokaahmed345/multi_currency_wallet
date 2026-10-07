package com.example.multi_currencywallet.feature.converter.data.RatesRepositoryImpl

import com.example.multi_currencywallet.core.error.Failure
import com.example.multi_currencywallet.core.model.Currency
import com.example.multi_currencywallet.core.util.CurrencyMapper
import com.example.multi_currencywallet.core.util.Either
import com.example.multi_currencywallet.feature.converter.data.datasource.RatesRemoteDataSource
import com.example.multi_currencywallet.feature.converter.domain.entity.ExchangeRate
import com.example.multi_currencywallet.feature.converter.domain.repository.RatesRepository
import kotlinx.coroutines.CancellationException
import retrofit2.HttpException
import java.io.IOException
import java.net.SocketTimeoutException
import javax.inject.Inject

class RatesRepositoryImpl @Inject constructor(
    private val remote: RatesRemoteDataSource
) : RatesRepository {

    override suspend fun getRate(base: String, target: String): Either<Failure, ExchangeRate> =
        safeCall {
            val entity = remote.getRates(base, target).toEntity(target)
            if (entity != null) Either.Right(entity) else Either.Left(Failure.EmptyData)
        }

    override suspend fun getCurrencies(): Either<Failure, List<Currency>> =
        safeCall {
            val map = remote.getCurrencies()
            if (map.isEmpty()) {
                Either.Left(Failure.EmptyData)
            } else {
                Either.Right(
                    map.map { (code, name) ->
                        Currency(code = code, name = name, flag = CurrencyMapper.flagFor(code))
                    }
                )
            }
        }

    private inline fun <T> safeCall(block: () -> Either<Failure, T>): Either<Failure, T> =
        try {
            block()
        } catch (e: CancellationException) {
            throw e
        } catch (e: SocketTimeoutException) {
            Either.Left(Failure.Timeout)
        } catch (e: IOException) {
            Either.Left(Failure.NoInternet)
        } catch (e: HttpException) {
            Either.Left(Failure.Server(e.code()))
        } catch (e: Exception) {
            Either.Left(Failure.Unknown(e.message ?: "Something went wrong"))
        }
}