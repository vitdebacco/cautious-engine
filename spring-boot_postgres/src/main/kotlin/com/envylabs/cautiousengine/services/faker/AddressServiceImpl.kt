package com.envylabs.cautiousengine.services.faker

import com.envylabs.cautiousengine.models.Address
import com.envylabs.cautiousengine.services.AddressService
import com.github.javafaker.Faker
import org.springframework.stereotype.Service
import kotlin.random.Random
import kotlin.random.nextInt

@Service
class AddressServiceImpl : AddressService {

    companion object {
        private const val MIN_RESULTS = 1
    }

    private val faker = Faker()

    override fun many(limit: Int): List<Address> {
        val result = mutableListOf<Address>()
        val numResults = Random.nextInt(MIN_RESULTS..limit)
        val resultsRange = MIN_RESULTS..numResults

        for (i in resultsRange) {
            result.add(generateAddress())
        }

        return result
    }

    override fun one(): Address {
        return generateAddress()
    }

    private fun generateAddress(): Address {
        val state = faker.address().stateAbbr()

        return Address(
            street = faker.address().streetAddress(),
            city = faker.address().city(),
            state = state,
            zipCode = faker.address().zipCodeByState(state)
        )
    }
}
