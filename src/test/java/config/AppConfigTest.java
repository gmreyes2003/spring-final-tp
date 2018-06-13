package config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.test.context.TestPropertySource;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

@Configuration
@EnableTransactionManagement
@ComponentScan(basePackages ={"net.viralpatel.spring","net.viralpatel.spring.model","net.viralpatel.spring.repositories"})
@TestPropertySource(value = { "classpath:test_application.properties" })
public class AppConfigTest {

    @Autowired
    private Environment env;

        @Bean
    public HibernateJpaVendorAdapter hibernateJpaAdapter() {
        HibernateJpaVendorAdapter adapter = new HibernateJpaVendorAdapter();
        adapter.setDatabase(Database.MYSQL);
        adapter.setShowSql(Boolean.getBoolean(env.getProperty("hibernate.show_sql")));
        adapter.setGenerateDdl(Boolean.getBoolean(env.getProperty("hibernate.generate_ddl")));

        return adapter;
    }

    @Bean
    public DataSource dataSource() {
        EmbeddedDatabaseBuilder builder = new EmbeddedDatabaseBuilder();
        EmbeddedDatabase db = builder
                .setType(EmbeddedDatabaseType.H2)//.H2 or .DERBY
                .addScript("create_schema.sql")
                .addScript("initial_insert_test.sql")
                .build();
        return db;
    }

    @Bean(name="entityManagerFactory")
    public LocalContainerEntityManagerFactoryBean getEntityManagerFactoryBean() {
        LocalContainerEntityManagerFactoryBean lcemfb = new LocalContainerEntityManagerFactoryBean();
        lcemfb.setJpaVendorAdapter(hibernateJpaAdapter());
        lcemfb.setDataSource(dataSource());
        lcemfb.setPersistenceUnitName("myTestJpaPersistenceUnit");
        lcemfb.setPackagesToScan("net.viralpatel.spring.model");

        return lcemfb;
    }

    @Bean
    public JpaTransactionManager transactionManager() {
        final JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(getEntityManagerFactoryBean().getObject());
        transactionManager.setDataSource(dataSource());

        return transactionManager;
    }
}