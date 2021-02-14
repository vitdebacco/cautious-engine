package com.envylabs.cautiousengine.services.faker

import com.envylabs.cautiousengine.models.Address
import com.envylabs.cautiousengine.services.AddressService
import com.github.javafaker.Faker
import org.springframework.stereotype.Service
import kotlin.random.Random
import kotlin.random.nextInt

@Service
class AddressServiceImpl : AddressService {

    private val faker = Faker()

    override fun many(limit: Int): List<Address> {
        var numResults = Random.nextInt(0..limit)
        val result = mutableListOf<Address>()

        while (--numResults >= 0) {
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
