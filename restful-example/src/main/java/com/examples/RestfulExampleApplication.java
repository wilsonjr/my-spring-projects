package com.examples;

import java.util.Arrays;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.JpaRepository;

import com.examples.model.Account;
import com.examples.model.Bookmark;
import com.examples.repository.AccountRepository;
import com.examples.repository.BookmarkRepository;

@SpringBootApplication
public class RestfulExampleApplication {

	public static void main(String[] args) {
		SpringApplication.run(RestfulExampleApplication.class, args);
	}
	
	
	@Bean
	CommandLineRunner init(AccountRepository accountRepository, BookmarkRepository bookmarkRepository) {
		return (evt) -> Arrays.asList(
				"jhoeller,dsyer,pwebb,ogierke,rwinch,mfisher,mpollack,jlong".split(","))
				.forEach(
						a -> {
							Account account = accountRepository.save(new Account(a,
									"password"));
							((JpaRepository<Bookmark, Long>) bookmarkRepository).save(new Bookmark(account,
									"http://bookmark.com/1/" + a, "A description"));
							((JpaRepository<Bookmark, Long>) bookmarkRepository).save(new Bookmark(account,
									"http://bookmark.com/2/" + a, "A description"));
						});
	}
}
