### Running
This is a spring boot application built with Maven. When running any maven commands provide the following flag

`-Dopenweather.apiKey=<API_KEY>`

targets running tests will require this, although it would be ideal to not require it for tests and use a mock instead.

### Requirements not addressed, due to time constraints

* all of requirement 2
* validating that GeoCordinates are within the UK

### Areas for improvement within the tackled scope

* test coverage of controller is missing, WIP can be seen commented out.
* some extra bits part-modelled, but not implemented i.e. support for temperature other than Kelvin
* handling of nullable values, in many cases Optional.get has been called without checking
* should have more test cases within ForecastTest to check for edge cases
* integration test with a mocking framework such as wiremock
* integration test using real web service may be unreliable, was mainly there to speed up development and execute code against the real service quickly
* no logging
* IllegalArgumentException could be wrapped or replaced with a custom type