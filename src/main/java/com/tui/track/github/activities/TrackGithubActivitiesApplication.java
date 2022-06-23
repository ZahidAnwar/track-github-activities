package com.tui.track.github.activities;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import javax.annotation.PostConstruct;
import java.util.TimeZone;

@Slf4j
@SpringBootApplication
public class TrackGithubActivitiesApplication {

	public static void main(String[] args) {

		log.info("Starting track-github-activities app...");
		SpringApplication.run(TrackGithubActivitiesApplication.class, args);
	}

	@PostConstruct
	public void init() {
		log.info("Setting Spring Boot SetTimeZone UTC for track-github-activities app...");
		TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
	}

}
