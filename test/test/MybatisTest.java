package test;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;

import mybatis_demo.Person;
import mybatis_demo.User;

import org.junit.Before;
import org.junit.Test;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class MybatisTest {
	private SqlSessionFactory sf;
	
	@Before
	public void initsf() throws IOException{
		String resoure = "sqlMapConfig.xml";
		InputStream inputStream = Resources.getResourceAsStream(resoure);
		sf = new SqlSessionFactoryBuilder()
				.build(inputStream);
	}

	@Test
	public void test1() throws IOException {

		SqlSession session = sf.openSession();
		// User u =
		// session.selectOne("mybatis_demo.User.selectUserById","0001");
		// System.out.println(u);

		/*List<User> users = session
				.selectList("mybatis_demo.User.selectAllUser");
		for (User u : users) {
			System.out.println(u);
		}*/
		
		List<HashMap> users = session.selectList("mybatis_demo.User.selectAllUserForMap");
		
		for(HashMap map: users){
			System.out.println(map);
		}
		
	}
	
	@Test
	public void test2() throws IOException {

		SqlSession session = sf.openSession();
		/*User u = new User();
		u.setId("0003");
		u.setName("lisi");
		u.setAddress("李四");
		int i = session.insert("mybatis_demo.User.insertUser", u);*/
		
		HashMap<String,String> map = new HashMap<String,String>();
		map.put("id", "0004");
		map.put("name", "wangwu");
		map.put("address", "王五");
		
		int i = session.insert("mybatis_demo.User.insertUserForMap",map); 
		session.commit();
		System.out.println(i);
	}
	
	@Test
	public void test3() throws IOException {
		SqlSession session = sf.openSession();
		/*User u = new User();
		u.setId("0003");
		u.setName("lisi3");
		u.setAddress("李四3");
		int i = session.update("mybatis_demo.User.updateUserById", u);*/
		HashMap<String,String> map = new HashMap<String,String>();
		map.put("id", "0003");
		map.put("name", "lisi4");
		map.put("address", "李四4");
		
		int i = session.update("mybatis_demo.User.updateUserByIdForMap", map);
		session.commit();
		System.out.println(i);
		
	}
	
	
	@Test
	public void test4() throws IOException {
		SqlSession session = sf.openSession();
		User u = new User();
		u.setId("0003");
		u.setName("lisi3");
		u.setAddress("李四3");
		int i = session.update("mybatis_demo.User.deleteUserById", u);
		/*HashMap<String,String> map = new HashMap<String,String>();
		map.put("id", "0004");
		
		int i = session.update("mybatis_demo.User.deleteUserByIdForMap", map);*/
		session.commit();
		System.out.println(i);
		
	}
	
	@Test
	public void test5() throws IOException {
		SqlSession session = sf.openSession();
		User u = new User();
		u.setId("0002");
		u.setName("zhangsan2");
		u.setAddress("文三路2");
		List<User> users = session.selectList("mybatis_demo.User.selectUserCondition", u);
		
		for(User user: users){
			System.out.println(user);
		}
	}
	
	@Test
	public void test6() throws IOException {

		SqlSession session = sf.openSession();
//		Person p =
//		 session.selectOne("mybatis_demo.Person.selectPersonById","0001");
//		 System.out.println(p.getPersonId());

		List<Person> Persons = session
				.selectList("mybatis_demo.Person.selectAllPerson");
		for (Person p : Persons) {
			System.out.println(p);
		}
	}
}
