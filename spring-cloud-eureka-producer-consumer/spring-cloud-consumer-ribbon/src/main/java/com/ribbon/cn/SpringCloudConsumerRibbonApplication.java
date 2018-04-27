package com.ribbon.cn;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableDiscoveryClient
@EnableHystrix     //开启hystrix(熔断器)功能
public class SpringCloudConsumerRibbonApplication {

	@Bean
	@LoadBalanced    //@LoadBalanced注解表明这个restRemplate开启负载均衡的功能
	RestTemplate restTemplate(){
		return new RestTemplate();     //以RestTemplate的方式调用服务
	}

	public static void main(String[] args) {
		SpringApplication.run(SpringCloudConsumerRibbonApplication.class, args);
	}
}
