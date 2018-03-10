package com.example.config;

import com.example.domain.User;
import com.example.interceptor.PasswordEncoderPlugin;
import com.mchange.v2.c3p0.ComboPooledDataSource;

import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import javax.sql.DataSource;
import java.beans.PropertyVetoException;

@Configuration
public class DataConfig {

    @Bean
    public RedisConnectionFactory connectionFactory() {
        return new JedisConnectionFactory();
    }

    @Bean
    public RedisTemplate<Object, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
        RedisTemplate<Object, Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(redisConnectionFactory);
        //redisTemplate.setDefaultSerializer(new Jackson2JsonRedisSerializer(User.class));
        return redisTemplate;
    }

//    @Bean
//    @Primary
//    public DataSource jndiDataSource() {
//        JndiObjectFactoryBean jndiObjectFactoryBean =
//                new JndiObjectFactoryBean();
//        jndiObjectFactoryBean.setJndiName("java:comp/env/jdbc/test");
//        return (DataSource)jndiObjectFactoryBean.getObject();
//    }

    @Bean
    @Primary
    public DataSource h2DataSource() {
        return new EmbeddedDatabaseBuilder()
                .setType(EmbeddedDatabaseType.H2)
                .addScript("schema.sql")
                .build();
    }

    @Bean
    public DataSource c3p0dataSource()  {
        ComboPooledDataSource cpds = new ComboPooledDataSource();
        try {
            cpds.setDriverClass( "com.mysql.jdbc.Driver" );
        } catch (PropertyVetoException e) {
            e.printStackTrace();
        }
        cpds.setJdbcUrl( "jdbc:mysql://localhost:3306/test" );
        cpds.setUser("peter");
        cpds.setPassword("123456");

        cpds.setMinPoolSize(5);
        cpds.setAcquireIncrement(5);
        cpds.setMaxPoolSize(20);

        return cpds;
    }



    @Bean
    public SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean
                = new SqlSessionFactoryBean();

        sqlSessionFactoryBean.setDataSource(dataSource);

        Resource[] mappers = new ClassPathResource[]{
                new ClassPathResource("mappers/UserMapper.xml"),
        };
        sqlSessionFactoryBean.setMapperLocations(mappers);

        Class[] aliases = new Class[]{User.class,};
        sqlSessionFactoryBean.setTypeAliases(aliases);

        sqlSessionFactoryBean.setPlugins(new Interceptor[]{
                new PasswordEncoderPlugin(),
        });

        //sqlSessionFactoryBean.setCache(new MybatisRedisCache("1"));

        return sqlSessionFactoryBean.getObject();
    }

    @Bean
    public MapperScannerConfigurer mapperScannerConfigurer() {
        MapperScannerConfigurer mapperScannerConfigurer
                = new MapperScannerConfigurer();
        mapperScannerConfigurer.setBasePackage("com.example.repository");
        return mapperScannerConfigurer;
    }
}
