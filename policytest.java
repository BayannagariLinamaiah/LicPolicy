package com.nttdata;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;


class Policytest {

	@Test
	void test() {
		//checking the opertion.
		int check=Policy.Insert();
		assertEquals(check, 1);
	}
}
