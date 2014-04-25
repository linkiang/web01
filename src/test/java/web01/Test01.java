package web01;

import org.junit.Test;

import com.lq.web01.module.security.service.SecurityCheck;


public class Test01 {
	
	@Test
	public void test01() {
		SecurityCheck.check();
	}
	
	// 测试非静态内部类
	@Test
	public void test02() {
		ClassOuter out = new ClassOuter();
		ClassOuter.ClassInner inner  =  out.new ClassInner();
		inner.method1();
	}

}
