Approach 2: Supporting multiple algorithms on the basis of versions in parameters.(Request parameter versioning)

CodingTask<br>
Rest-Api to list prime numbers

The technical specs of the project are:<br>
Language: JAVA 17<br>
Build tool: Maven<br>
Framework: SpringBoot<br>
Caching Mechanism: Caffeine<br>
Unit Tests: Junit and Mockito<br>
Integration Tests: MockMvc<br>

I have coded in such a way that this application supports 2 algorithms with 2 separate versions in endpoint parameters endpoint<br>
 1. /primes/{num}?version=1: using the concept that a number is a prime number if it's not divisible by any number less than or equal to its square root.<br>
 2. /primes/{num}?version=2: using the concept that a prime number, except 2 and 3 , can be represented as either 6k-1 or 6k+1 , where k>=1. I have used this logic in conjunction with concept of first algorithm. Hence, making it almost 3 times faster than the previous one.<br>
 3. /primes/{num} : uses second algorithm by default
The time complexity of both algorithms is O(sqrt(n)) where n is the input. <br>

I have tried to implement concurrency by using ParallelStream() which makes use of Fork Join Pool internally.<br>

The output of the rest endpoints can be accessed in the format of xml or json based on your choice. I have added this dependency to support xml return type: jackson-dataformat-xml<br>

I have deployed the application to EC2 instance and also configured it to run in background: You can access the endpoints from following links:<br>

http://ec2-13-56-137-243.us-west-1.compute.amazonaws.com:8081/primes/20?version=1 or http://13.56.137.243:8081/primes/3?version=1
http://ec2-13-56-137-243.us-west-1.compute.amazonaws.com:8081/primes/20?version=2 OR http://13.56.137.243:8081/primes/3?version=2
http://ec2-13-56-137-243.us-west-1.compute.amazonaws.com:8081/primes/20 OR http://13.56.137.243:8081/primes/3

Alternatively, you can use above links to access the endpoints through postman as well.

