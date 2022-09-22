import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;
import wang.mapper.EmpMapper;
import wang.pojo.Emp;
import wang.pojo.EmpExample;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * @ClassName: ZunXiangTest
 * @Author: WangBin
 * @Date: 2022/9/21 11:29
 * @Description: mybatis3逆向生成尊享版测试
 **/
public class ZunXiangTest {


    /**
     * - `selectByExample`：按条件查询，需要传入一个example对象或者null；如果传入一个null，则表示没有条件，也就是查询所有数据
     * - `example.createCriteria().xxx`：创建条件对象，通过andXXX方法为SQL添加查询添加，每个条件之间是and关系
     * - `example.or().xxx`：将之前添加的条件通过or拼接其他条件
     *
     * - `updateByPrimaryKey`：通过主键进行数据修改，如果某一个值为null，也会将对应的字段改为null
     * - `updateByPrimaryKeySelective()`：有条件的修改  通过主键进行选择性数据修改，如果某个值为null，则不修改这个字段
     *  -
     */
    @Test
    public void testZunXiang(){
        try {
            InputStream is = Resources.getResourceAsStream("mybatis-config.xml");
            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(is);
            SqlSession sqlSession = sqlSessionFactory.openSession(true);
            EmpMapper mapper = sqlSession.getMapper(EmpMapper.class);
            // 没有条件、查询所有
//            List<Emp> emps = mapper.selectByExample(null);
//            emps.forEach(emp -> System.out.println(emp));
            // 根据条件查询
            EmpExample example = new EmpExample();
            example.createCriteria().andEmpNameEqualTo("TOM").andAgeBetween(10,30);
            // 可以加上或者条件
            example.or().andDidIsNotNull();
            List<Emp> emps = mapper.selectByExample(example);
            emps.forEach(emp -> System.out.println(emp));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



}
