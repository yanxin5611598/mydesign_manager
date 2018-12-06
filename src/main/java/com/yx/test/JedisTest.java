package com.yx.test;

import com.yx.mydesign.utils.JedisPoolUtils;

import redis.clients.jedis.Jedis;

public class JedisTest {
	public static void main(String[] args) {
		Jedis jedis = JedisPoolUtils.getJedis();
        jedis.srem("onlineDevice", "D0001");
        jedis.del("D0001");
		/*jedis.del("onlineDevice");
		jedis.lpush("test", "111");
		jedis.lpush("test", "222");
		jedis.lpush("test", "333");
		System.out.println(jedis.lrange("test",0,100));
		jedis.lrem("test", 10, "111");
		System.out.println(jedis.lrange("test",0,3));
		System.out.println("------------------分割线---------------");
		if(jedis.exists("set")){
			jedis.del("set");
		}
		jedis.sadd("set", "111");
		jedis.sadd("set", "211");
		jedis.sadd("set", "311");
		jedis.sadd("set", "311");
		System.out.println(jedis.smembers("set"));
		System.out.println(jedis.sismember("set", "111"));
		System.out.println(jedis.srem("set","111"));
		System.out.println(jedis.sismember("set", "111"));
		System.out.println(jedis.smembers("set"));*/
		/*for(String str:jedis.get("test")){
			System.out.println(str);
		}*/
	}
}
