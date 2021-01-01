//package com.mljr.gps.common.config;
//
//import com.alibaba.druid.pool.DruidDataSource;
//import org.apache.ibatis.session.SqlSessionFactory;
//import org.mybatis.spring.SqlSessionFactoryBean;
//import org.mybatis.spring.SqlSessionTemplate;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Import;
//import org.springframework.context.annotation.PropertySource;
//import org.springframework.core.Ordered;
//import org.springframework.core.annotation.Order;
//import org.springframework.jdbc.core.JdbcTemplate;
//import org.springframework.jdbc.datasource.DataSourceTransactionManager;
//import org.springframework.transaction.annotation.EnableTransactionManagement;
//
//import java.sql.SQLException;
//import java.util.Properties;
//
///**
// * @author : Guo LiXiao
// * @date : 2018-1-5  14:22
// */
//@Configuration
//@EnableTransactionManagement(proxyTargetClass = true)
//@PropertySource(value = {"classpath:application-*.properties"}, encoding = "UTF-8", ignoreResourceNotFound = true)
//@Import(MybatisConfig.class)
//@Order(Ordered.LOWEST_PRECEDENCE)
//public class DataSourceConfig {
//
//    Logger logger = LoggerFactory.getLogger(DataSourceConfig.class);
//
//    private final String typeAlias = "com.mljr.gps.entity,com.mljr.gps.form";
//
//    @Value("${spring.datasource.driverClassName}")
//    String driver;
//
//    @Value("${spring.datasource.url}")
//    String url;
//
//    @Value("${spring.datasource.username}")
//    private String username;
//
//    @Value("${spring.datasource.password}")
//    private String password;
//
//    @Value("${spring.datasource.initial-size}")
//    private Integer initialSize;
//
//    @Value("${spring.datasource.max-idle}")
//    private Integer minIdle;
//
//    @Value("${spring.datasource.max-active}")
//    private Integer maxActive;
//
//    @Bean(name = "dataSource", initMethod = "init", destroyMethod = "close")
//    @Order(Ordered.HIGHEST_PRECEDENCE + 11)
//    public DruidDataSource dataSource() {
//        DruidDataSource dataSource = new DruidDataSource();
//        dataSource.setUrl(url);
//        dataSource.setUsername(username);
//        dataSource.setPassword(password);
//        try {
//            dataSource.setFilters("stat,slf4j,wall");
//        } catch (SQLException e) {
//            logger.error("datasource 初始化filters失败", e);
//        }
//        //配置初始化大小、最小、最大
//        dataSource.setInitialSize(initialSize);
//        dataSource.setMinIdle(minIdle);
//        dataSource.setMaxActive(maxActive);
//        dataSource.setMaxWait(60000);
//        dataSource.setMinEvictableIdleTimeMillis(160000);
//        dataSource.setValidationQuery("SELECT 'x'");
//        //建议配置为true，不影响性能，并且保证安全性。申请连接的时候检测，如果空闲时间大于timeBetweenEvictionRunsMillis，执行validationQuery检测连接是否有效。
//        dataSource.setTestWhileIdle(true);
//        //申请连接时执行validationQuery检测连接是否有效，做了这个配置会降低性能。
//        dataSource.setTestOnBorrow(false);
//        //归还连接时执行validationQuery检测连接是否有效，做了这个配置会降低性能。
//        dataSource.setTestOnReturn(false);
//        logger.info("MybatisConfig-- DataSource数据源");
//        return dataSource;
//    }
//
//	@Order(Ordered.HIGHEST_PRECEDENCE + 12)
//    @Bean(name = "sqlSessionFactory")
//    public SqlSessionFactory sqlSessionFactoryBean() {
//
//
//
//
//		logger.info("MybatisConfig--生成sqlSessionFactoryBean");
//        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
//        bean.setDataSource(dataSource());
//        bean.setTypeAliasesPackage(typeAlias);
//        Properties properties = new Properties();
//        properties.setProperty("dialect", "mysql");
//        bean.setConfigurationProperties(properties);
//        try {
//            return bean.getObject();
//        } catch (Exception e) {
//
//            throw new RuntimeException(e);
//        }
//    }
//
//    @Bean
//    public SqlSessionTemplate sqlSessionTemplate(SqlSessionFactory sqlSessionFactory) {
//        return new SqlSessionTemplate(sqlSessionFactory);
//    }
//
//    @Order(Ordered.HIGHEST_PRECEDENCE + 13)
//    @Bean
//    public DataSourceTransactionManager dataSourceTransactionManager() {
//        logger.info("MybatisConfig-- 事务启动~");
//        DataSourceTransactionManager transactionManager = new DataSourceTransactionManager();
//        transactionManager.setDataSource(dataSource());
//        transactionManager.setGlobalRollbackOnParticipationFailure(true);
//        return transactionManager;
//    }
//
//    @Bean(name = "jdbcTemplate")
//    @Order(Ordered.HIGHEST_PRECEDENCE + 14)
//    public JdbcTemplate jdbcTemplate() {
//        JdbcTemplate jdbcTemplate = new JdbcTemplate();
//        jdbcTemplate.setDataSource(dataSource());
//        return jdbcTemplate;
//    }
//}
