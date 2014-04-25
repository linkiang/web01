package web01;

import org.junit.Test;

import com.lq.web01.module.security.service.SecurityCheck;


public class Test01 {
	
	@Test
	public void test01() {
		SecurityCheck.check();
	}

}
