🚀 Spring Boot Redis Cache Example

This project demonstrates how to implement Redis caching in a Spring Boot application using Spring Cache Abstraction.
It clearly shows how data is fetched from the database on the first request and from Redis cache on subsequent requests.

🛠️ Tech Stack

Java 17+

Spring Boot

Spring Data JPA

Spring Cache

Redis

MySQL / H2 (any relational DB)

Maven

📌 Features

REST APIs for User management

Redis cache integration using @Cacheable, @CachePut, @CacheEvict

Clear logging to verify Cache HIT vs Cache MISS

Improved performance by avoiding repeated DB calls

⚙️ Redis Cache Configuration

Redis is configured using Spring Cache abstraction.

@Cacheable(value = "Users", key = "#id")
public UserDTO getUserById(UUID id) {
    log.info("Fetching user from DATABASE");
    return userMapper.entityToDto(userRepository.findById(id).orElseThrow());
}

🔍 Cache Verification Using Logs

Spring Cache does not return logs by default, so TRACE logging is enabled to verify cache behavior.

Logging Configuration
logging.level.org.springframework.cache=TRACE
logging.level.org.springframework.data.redis=DEBUG

🧪 Cache Behavior Demonstration
🔴 First API Call — Cache MISS (Database Hit)

When the API is called for the first time:

Computed cache key '02b78f79-ff1e-417d-aa53-aef277b3f506'
No cache entry for key ... in cache(s) [Users]
Fetching user from DATABASE
select * from user u1_0 where u1_0.id=?
Creating cache entry for key ... in cache(s) [Users]


✅ Explanation:

Cache key is generated

No entry found in Redis → Cache Miss

Data fetched from Database

Result stored in Redis cache

🟢 Second API Call — Cache HIT (Redis)

When the same API is called again with the same ID:

Computed cache key '02b78f79-ff1e-417d-aa53-aef277b3f506'
Cache entry for key ... found in cache(s) [Users]


✅ Explanation:

Same cache key is reused

Entry found in Redis → Cache Hit

No database query executed

📈 Performance Benefit
Request	Data Source
1st Call	Database
2nd+ Call	Redis Cache

This significantly reduces:

Database load

Response time

Overall system latency

🧹 Cache Update & Eviction

@CachePut → Updates cache when data is modified

@CacheEvict → Removes cache when data is deleted

@CacheEvict(value = "Users", key = "#id")
public void deleteUserById(UUID id) {
    userRepository.deleteById(id);
}

▶️ How to Run the Application

Start Redis server

redis-server


Run Spring Boot application

mvn spring-boot:run


Call APIs using Postman or browser

🧠 Key Takeaway

The first request results in a cache miss and fetches data from the database.
Subsequent requests result in cache hits and data is served directly from Redis, avoiding database calls.

📷 Screenshot Proof

<img width="1380" height="415" alt="image" src="https://github.com/user-attachments/assets/1f3a0ba3-36eb-4c98-a3ed-a4faea8a6a0b" />
