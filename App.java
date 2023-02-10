package spring_basic.com.ncu.fsb;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.ApplicationContext;
//import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.*;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

/**
 * Hello world!
 *
 */
public class App {
	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("prjxml.xml");
        PropDemo propdemo = context.getBean("propdemo",PropDemo.class);
        propdemo.printProp();
	}
}

@Component("sayhello")
class SayHello {
	SayHello(){
		System.out.println("Constructor of SayHello..");
	}
	void printHello() {
		System.out.println("Hello.....");
	}
}
@Component("propdemo")
class PropDemo {
	@Autowired
	private SayHello sh;
	PropDemo(){
		System.out.println("Property Demo created..");
	}
	SayHello getPropDemo() {
		return sh;
	}
	void setSayHello(SayHello s) {
		this.sh = s;
	}
	void printProp() {
		System.out.println("Hello Property Demo..");
		sh.printHello();
	}
}
