package contracts.user

import org.springframework.cloud.contract.spec.Contract

return Contract.make {
    description "should return user by id"

    request {
        method GET()
        url "/api/users/1"
        headers {
            contentType(applicationJson())
        }
    }

    response {
        status OK()
        headers {
            contentType(applicationJson())
        }
        body(
            id: 1,
            name: "John Doe",
            age: 30
        )
        bodyMatchers {
            jsonPath('$.id', byRegex('[0-9]+'))
            jsonPath('$.name', byRegex('.+'))
            jsonPath('$.age', byRegex('.*@.*'))
        }
    }
}