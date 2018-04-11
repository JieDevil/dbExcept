package org.jiedevil.dbExcept;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.sql.SQLException;

@SpringBootApplication
@EnableTransactionManagement
public class Application {
    /**
     * @param driver
     * @param url
     * @param username
     * @param password
     * @param maxActive
     * @Author: DengWenjie
     * @group: org.jiedevil.dbExcept
     * @Date: 2018/4/11 13:37
     * @Description: 注册Druid数据池接口
     * @version: 1.0.0
     */
    @Bean
    public DataSource druidDataSource(@Value("${spring.datasource.driverClassName}") String driver, @Value("${spring.datasource.url}") String url, @Value("${spring.datasource.username}") String username, @Value("${spring.datasource.password}") String password, @Value("${spring.datasource.maxActive}") int maxActive) {
        DruidDataSource druidDataSource = new DruidDataSource();
        druidDataSource.setDriverClassName(driver);
        druidDataSource.setUrl(url);
        druidDataSource.setUsername(username);
        druidDataSource.setPassword(password);
        druidDataSource.setMaxActive(maxActive);

        System.out.println("DruidConfiguration.druidDataSource(),url=" + url + ",username=" + username + ",password=" + password);

        try {
            druidDataSource.setFilters("stat, wall");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return druidDataSource;
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
