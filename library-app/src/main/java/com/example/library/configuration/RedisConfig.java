package com.example.library.configuration;

import java.time.Duration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.RedisSerializer;

@Configuration
public class RedisConfig {
	/* Used for configuring redis based caching in this application */
	@Bean
	RedisCacheManager cacheManager(RedisConnectionFactory fatory) {
		RedisSerializer<Object> jsonSerializer = new GenericJackson2JsonRedisSerializer();
		RedisCacheConfiguration config = RedisCacheConfiguration
				.defaultCacheConfig()
				.entryTtl(Duration.ofMinutes(10))
				.disableCachingNullValues()
				.serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(jsonSerializer));
		return RedisCacheManager
				.builder(fatory)
				.cacheDefaults(config)
				.build();
	}
}
