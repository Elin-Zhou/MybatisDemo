DAO层简化与逆向工程



分类：Mybatis   2015-07-31 09:36:49 



Github地址：git@github.com:Elin-Zhou/MybatisDemo.git

 

DAO层简化

原本需要DAO层接口+DAO层实现

简化后的只需要一个接口，不需要实现代码（Mybatis命名为Mapper接口）
 
•实现步骤

1、在Spring配置文件中加入自动扫描Mapper接口的Bean
<!--装载sqlsession-->
<bean id="sqlSession" class="org.mybatis.spring.SqlSessionFactoryBean">
			<!-- mybatis配置文件位置 -->
       <property name="configLocation" value="classpath:config/mybatis/SqlMapConfig.xml"/>
       <!-- 数据源 -->
       <property name="dataSource" ref="dataSource"/>
</bean>

<!--自动扫描mapper接口，并注入sqlsession-->
<bean class="org.mybatis.spring.mapper.MapperScannerConfigurer">
	   <!-- mapper接口所在位置 -->	
       <property name="basePackage" value="com.elin4it.mybatisDemo.mapper"/>
       <!-- 注意此处为sqlSessionFactoryBeanName而不是sqlSessionFactoryBean -->
       <property name="sqlSessionFactoryBeanName" value="sqlSession"/>
</bean>

2、修改xml文件中的namespace为该XML文件的Mapper接口的全限定名，如
<mapper namespace="com.yumei.settlecore.common.dal.dataobject">

3、添加Mapper接口，其全限定名要与对应的xml文件的namespace相同。

其中的方法签名有如下要求：
•方法名与对应的xml中的标签的id相同（Mybatsi称其为statement）
•方法如果有返回值，需要同xml标签中的resultType/resultMap类型相同，如果没有则为void
•方法如果有形参，需要同xml标签中的parameterType类型相同

2、在Service层自动注入
@Autowired
private BankBranchCustomMapper bankBranchCustomMapper;

 

3、直接调用接口方法
public int queryBankBranchByBankCodeSum(String bankCode,
		String provinceCode, String cityCode, String bankBranch) {
	Map map = new HashMap();
	map.put("bankCode", bankCode);
	map.put("provinceCode", provinceCode);
	map.put("cityCode", cityCode);
	map.put("bankBranch", bankBranch);
	
	int count = bankBranchCustomMapper
			.QUERY_BANK_BRANCH_BY_BANK_CODE_SUM(map);
	
	return count;
}

 

 

逆向工程（Generator）

自动生成指定表的POJO类、单表CRUD XML文件以及对应的Mapper接口
•实现步骤

1、在pom.xml中添加mybatis generator插件
<plugins>
    <!--mybatis 逆向工程插件-->
    <plugin>
        <groupId>org.mybatis.generator</groupId>
        <artifactId>mybatis-generator-maven-plugin</artifactId>
        <version>1.3.2</version>
        <configuration>
            <verbose>true</verbose>
            <overwrite>true</overwrite>
        </configuration>
    </plugin>
</plugins>

2、在classpath（默认）下添加generatorConfig.xml文件，其内容为 
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE generatorConfiguration
        PUBLIC "-//mybatis.org//DTD MyBatis Generator Configuration 1.0//EN"
        "http://mybatis.org/dtd/mybatis-generator-config_1_0.dtd">

<generatorConfiguration>
	<!-- 加载数据库信息 -->
    <properties resource="config/db.properties"/>
    <!-- 指定jdbc驱动 -->
    <classPathEntry location="F:\jar\mysql\mysql-connector-java-5.1.7-bin.jar"/>

    <context id="testTables" targetRuntime="MyBatis3" >
        <commentGenerator>
            <!-- 是否去除自动生成的注释 true：是 ： false:否 -->
            <property name="suppressAllComments" value="true" />
        </commentGenerator>
        <!--数据库连接的信息：驱动类、连接地址、用户名、密码 -->
        <jdbcConnection driverClass="${jdbc.driver}"
                        connectionURL="${jdbc.url}"
                        userId="${jdbc.username}"
                        password="${jdbc.password}">
        </jdbcConnection>

        <!-- 默认false，把JDBC DECIMAL 和 NUMERIC 类型解析为 Integer，为 true时把JDBC DECIMAL 和
            NUMERIC 类型解析为java.math.BigDecimal -->
        <javaTypeResolver>
            <property name="forceBigDecimals" value="false" />
        </javaTypeResolver>

        <!-- targetProject:生成PO类的位置 -->
        <javaModelGenerator targetPackage="com.elin4it.mybatisDemo.pojo"
                            targetProject="src\main\java">
            <!-- enableSubPackages:是否让schema作为包的后缀 -->
            <property name="enableSubPackages" value="false" />
            <!-- 从数据库返回的值被清理前后的空格 -->
            <property name="trimStrings" value="true" />
        </javaModelGenerator>
        <!-- targetProject:mapper映射文件生成的位置 -->
        <sqlMapGenerator targetPackage="com.elin4it.mybatisDemo.mapper"
                         targetProject="src\main\resources">
            <!-- enableSubPackages:是否让schema作为包的后缀 -->
            <property name="enableSubPackages" value="false" />
        </sqlMapGenerator>
        <!-- targetPackage：mapper接口生成的位置 -->
        <javaClientGenerator type="XMLMAPPER"
                             targetPackage="com.elin4it.mybatisDemo.mapper"
                             targetProject="src\main\java">
            <!-- enableSubPackages:是否让schema作为包的后缀 -->
            <property name="enableSubPackages" value="false" />
        </javaClientGenerator>
        <!-- 指定数据库表 -->
        <table tableName="bank"></table>
        <table tableName="bank_branch"></table>

        <!-- 有些表的字段需要指定java类型 -->
         <!-- <table schema="" tableName="">
            <columnOverride column="" javaType="" />
        </table>  -->
    </context>
</generatorConfiguration>


 

其中需要修改jdbc驱动位置、生成PO类的位置、mapper映射文件生成的位置 、mapper接口生成的位置 、指定数据库表

3、执行maven，其Goals为 mybatis-generator:generate

在指定位置会生成对应的POJO、Mapper xml、Mapper interface

 

 

查询条件

例如BankBranch，其会生成一个BankBranch类和BankBranchExample类，前者是通过字段映射生成的，后者是一个查询类，其中的一个内部类Criteria可以用来添加检索条件

使用方法大致为
// 设置查询条件
 BankBranchExample bankBranchExample = new BankBranchExample();
 BankBranchExample.Criteria criteria = bankBranchExample
 .createCriteria();

 criteria.andBankCodeEqualTo(bankCode);
 criteria.andProvinceCodeEqualTo(provinceCode);
 criteria.andCityCodeEqualTo(cityCode);
 criteria.andNameEqualTo(bankBranch);

 // 执行查询操作
 int count = bankBranchMapper.countByExample(bankBranchExample);
 return count;
