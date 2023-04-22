package com.maibaduoduo.test;

import com.google.common.collect.Lists;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.function.ThrowingConsumer;
import sun.tools.tree.CastExpression;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

public class TestLambda {

	/**
	 * 自定义比较规则
	 */
	List<Integer> lists = null;
	List<String> lists1 = null;
	@Before
	public void createList(){
		lists =Lists.newArrayList(1,2,4,6,7,8,9);
		lists1 =Lists.newArrayList("1","2","3");
	}
	@Test
	public  void comparator() {
		List<Contact> contacts = new ArrayList<Contact>() {{
			add(new Contact().setFirstName("Foo").setLastName("Jack"));
			add(new Contact().setFirstName("Bar").setLastName("Ma"));
			add(new Contact().setFirstName("Olala").setLastName("Awesome"));
		}};
		Comparator<Contact> byFirstName = new Comparator<Contact>() {
			@Override
			public int compare(Contact o1, Contact o2) {
				return Character.compare(o1.getFirstName().charAt(0), o2.getFirstName().charAt(0));
			}
		};
		//--- Using Lambda form ---//
		Comparator<Contact> byFirstNameLambdaForm = (o1, o2) ->
				Character.compare(o1.getFirstName().charAt(0), o2.getFirstName().charAt(0));
		Function<Contact, Character> keyExtractor = o -> o.getFirstName().charAt(0);
		Comparator<Character> keyComparator = (c1, c2) ->
				Character.compare(c1, c2);
		Comparator<Contact> byFirstNameAdvanced = (o1, o2) ->
				keyComparator.compare(keyExtractor.apply(o1), keyExtractor.apply(o2));
		Comparator<Contact> compareByFirstName = Comparator.comparing(keyExtractor);
		Comparator<Contact> compareByNameLength = Comparator.comparing(p -> (p.getFirstName() + p.getLastName()).length());

		contacts.stream()
				.sorted(byFirstNameLambdaForm)
				.forEach(c -> System.out.println(c.getFirstName() + " " + c.getLastName()));
	}
	@Test
	public void lambdaException(){
		try{
			lists.stream().forEach(i->System.out.print(i/0));
		}catch(ArithmeticException e){
			System.out.println("by zero");
		}
	}

	@Test
	public void lambdaWrapperException(){
		lists.stream().forEach(lambaaWrapper(integer -> System.out.println(integer/0)));
	}

	@Test
	public void lambdaWrapperException2(){
		lists1.stream().forEach(consumerWrapper2(string -> throwException(string)));
	}

	public Consumer<Integer> lambaaWrapper(Consumer<Integer> consumer){
       return  (i)->{
            	try{
					consumer.accept(i);
				}catch(ArithmeticException e){
            		System.out.println(e.getMessage());
				}
       };
	}
   public static void throwException(Object obj) throws Exception{}

	static <T> Consumer<T> consumerWrapper2(ThrowingConsumer<T> tExceptionThrowingConsumer){
             return i->{
					 try {
						 tExceptionThrowingConsumer.accept(i);
					 } catch (Throwable throwable) {
						 throwable.printStackTrace();
					 }
			 };
	}

	public <T, E extends Exception> Consumer<T> lambdaWraper3(Consumer<T> consumer, Class<E> clazz) {
		return i -> {
              try{
              	consumer.accept(i);
			  }catch(Exception ex){
                try{
					E e = clazz.cast(ex);
					System.out.println(e.getMessage());
				}catch(ClassCastException e){
                   e.printStackTrace();
				}
			  }
		};
	}
}


class Contact{
	private String firstName;
	private String lastName;

	public String getFirstName() {
		return firstName;
	}

	public Contact setFirstName(String firstName) {
		this.firstName = firstName;
		return  this;
	}

	public String getLastName() {
		return lastName;
	}

	public Contact setLastName(String lastName) {
		this.lastName = lastName;
		return  this;
	}
}