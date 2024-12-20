package contracts.user

import org.springframework.cloud.contract.spec.Contract

return Contract.make {
    description "should create new user"

    request {
        method POST()
        url "/api/users"
        headers {
            contentType(applicationJson())
        }
        body(
                name: "Jane Doe",
                age: "21"
        )
        bodyMatchers {
            jsonPath('$.name', byRegex('.+'))
            jsonPath('$.age', byRegex('.*@.*'))
        }
    }

    response {
        status CREATED()
        headers {
            contentType(applicationJson())
        }
        body(
                id: 2,
                name: fromRequest().body('$.name'),
                age: fromRequest().body('$.age')
        )
        bodyMatchers {
            jsonPath('$.id', byRegex('[0-9]+'))
        }
    }
}