package se.fidde.cartoll.war.util;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.fail;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import se.fidde.cartoll.war.util.keys.IndexKeys;
import se.fidde.cartoll.war.util.localization.CartollMessages;

public class CartollMessagesTest {

	private CartollMessages messages;

	@Before
	public void setUp() throws Exception {
		messages = new CartollMessages();
	}

	@After
	public void tearDown() throws Exception {
		messages = null;
	}

	@Test
	public void testGetCartollMessages() {
		assertNotNull("can get messages", messages);
	}

	@Test
	public void testGetMessage() throws Exception {
		try {
			String message = messages.getMessage(IndexKeys.ADD_PASSING_TEXT.toString());
			assertNotNull("message not null", message);

		} catch (Exception e) {
			fail();
		}
	}

	@Test
	public void testGetMessage_fail() throws Exception {
		try {
			String message = messages.getMessage("fail");
			assertNull(message);

		} catch (Exception e) {
			fail();
		}
	}
}
