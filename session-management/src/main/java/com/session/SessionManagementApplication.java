package com.session;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.session.concurrency.Consumer;
import com.session.concurrency.LogProcessor;
import com.session.concurrency.Producer;

@SpringBootApplication
public class SessionManagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(SessionManagementApplication.class, args);
		LogProcessor processor = new LogProcessor();

        // multiple producers and consumers
        new Producer(processor).start();
        new Producer(processor).start();

        new Consumer(processor).start();
        new Consumer(processor).start();
	}

}
