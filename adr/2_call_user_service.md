# Calling user service to check if the user exist for the User Id

## Status

Accepted

## Context

The request for creating a customer has a field - userId. It is a reference of a user from the potential user service.
In a monolith application, it throws UserNotFoundException(404) when there is no user for the given userId. As we break it into
microservices, we will have to keep the business requirement intact. We need to call a potential user service to check if
the user exists before creating the customer for it. If we had some gateway microservice, we could have achieved it with Saga.
Even the front end application could have managed it with Saga. However, considering the customer-service in complete isolation,
I have decided to call the user-service(/api/users) to check the existence of the user. Moreover, this call is synchronous using Feign
clients as we need the response from user-service for further processing.  

## Decision

Calling user-service(/api/users/{userId}) to check the if user exists for given userId so that the customer referring to it
can be saved.

## Consequences

It might introduce latency in the whole ecosystem. There is another scenario where user-service goes down. This will lead to Bad Gateway
at the customer-service even if it is running fine. However, this can be mitigated by extensive caching at the customer-service as
the user data, especially id, is very unlikely to change. We can also have circuit breakers like Hystrix in place. 
