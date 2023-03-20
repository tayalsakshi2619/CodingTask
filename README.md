CodingTask<br>
Rest-Api to list prime numbers

The technical specs of the project are:<br>
Language: JAVA 17<br>
Build tool: Maven<br>
Framework: SpringBoot<br>
Caching Mechanism: Caffeine<br>
Unit Tests: Junit and Mockito<br>
Integration Tests: MockMvc<br>

I have coded in such a way that this application supports 2 algorithms in 1 endpoint such that if number is less than 10000, it uses first algorithm otherwise second algorithm<br>
 1. First algorithm : using the concept that a number is a prime number if it's not divisible by any number less than or equal to its square root.<br>
 2. Second algorithm : using the concept that a prime number, except 2 and 3 , can be represented as either 6k-1 or 6k+1 , where k>=1. I have used this logic in conjunction with concept of first algorithm. Hence, making it almost 3 times faster than the previous one.<br>

The time complexity of both algorithms is O(sqrt(n)) where n is the input. <br>

I have tried to implement concurrency by using ParallelStream() which makes use of Fork Join Pool internally.<br>

The output of the rest endpoints can be accessed in the format of xml or json based on your choice. I have added this dependency to support xml return type: jackson-dataformat-xml<br>

I have deployed the application to a Linux EC2 instance and configured it to run as a backend service. You can use this link to access the endpoint:<br>


Note: Above links aren't operating with https protocol and will throw site not secure error.<br>

