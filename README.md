CodingTask
Rest-Api to list prime numbers

The technical specs of the project are:
Language: JAVA 17
Framework: SpringBoot
Container: Tomcat
Caching Mechanism: Caffeine
Unit Tests: Junit and Mockito
Integration Tests: MockMvc

I have coded in such a way that this application supports 2 algorithms on 2 different endpoints:
 1. /primes/v1/{num} : using the concept that a number is a prime number if it's not divisible by any number less than or equal to its square root.
 2. /primes/v2/{num} : using the concept that a prime number, except 2 and 3 , can be represented as either 6k-1 or 6k+1 , where k>=1. I have used this logic in conjunction with concept of first algorithm. Hence, making it almost 3 times faster than the previous one.

The time complexity of both algorithms is O(sqrt(n)) where n is the input. 

I have tried to implement concurrency by using ParallelStream() which makes use of Fork Join Pool internally.

The output of the rest endpoints can be accessed in the format of xml or json based on your choice. I have added this dependency to support xml return type: jackson-dataformat-xml

I have deployed the application to a Linux EC2 instance and configured it to run as a backend service. You can use this link to access the endpoint:

