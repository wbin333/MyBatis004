import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Ignore;
import org.junit.Test;
import wang.mapper.EmpMapper;
import wang.pojo.Emp;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;

/**
 * @ClassName: PageTest
 * @Author: WangBin
 * @Date: 2022/9/21 13:04
 * @Description: 测试分页功能
 **/
public class PageTest {

    /**
     *     分页
     * index：当前页的起始索引
     * pagesize：每页显示的条数
     * pagenum:当前页的页码
     * index=(pagenum-1)*pagesize
     */
    @Test
    public void testPageHelper() throws IOException {
        InputStream is = Resources.getResourceAsStream("mybatis-config.xml");
        SqlSessionFactoryBuilder sqlSessionFactoryBuilder = new SqlSessionFactoryBuilder();
        SqlSessionFactory sqlSessionFactory = sqlSessionFactoryBuilder.build(is);
        SqlSession sqlSession = sqlSessionFactory.openSession(true);
        EmpMapper mapper = sqlSession.getMapper(EmpMapper.class);
        //访问第几页，每页几条数据
        PageHelper.startPage(1,2);
        List<Emp> emps = mapper.selectByExample(null);
        emps.forEach(System.out::println);  // 等同于  emps.forEach(emp -> System.out.println(emp));
    }

    @Test
    public void testall() throws IOException {
        InputStream is = Resources.getResourceAsStream("mybatis-config.xml");
        SqlSessionFactoryBuilder sqlSessionFactoryBuilder = new SqlSessionFactoryBuilder();
        SqlSessionFactory sqlSessionFactory = sqlSessionFactoryBuilder.build(is);
        SqlSession sqlSession = sqlSessionFactory.openSession(true);
        EmpMapper mapper = sqlSession.getMapper(EmpMapper.class);
//        //访问第几页，每页几条数据
        PageHelper.startPage(1,2);
        List<Emp> emps = mapper.selectByExample(null);
        // 输出页面导航信息、当前页是第几页
        PageInfo<Emp> page = new PageInfo<>(emps,2);
//        emps.forEach(System.out::println);  // 等同于  emps.forEach(emp -> System.out.println(emp));
        System.out.println(page);
    }

    @Test
    public void testGit(){
        System.out.println("hello,git");
    }

}
