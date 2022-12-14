package de.workshops.bookshelf;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.EnabledIf;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@Log4j2
class EnvironmentTest {

	@Value("${server.port:8080}")
	private int port;

	@Test
	@EnabledIf(expression = "#{environment['spring.profiles.active'] == 'prod'}", loadContext = true)
	void verifyProdPort() {
		assertEquals(8090, port);
	}
}
