package za.ac.nwu.as.repo.config;


import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories("za.ac.nwu.as.repo.persistence")
@EntityScan("za.ac.nwu.as.domain.persistence")
@PropertySource(value = "classpath:application-db.properties")
public class RepositoryConfig {

/*    private static final String[] ENTITY_PACKAGES_TO_SCAN = {"za.ac.new.as.domain.persistence"};
private static final String PERSISTENCE_UNIT_NAME = "account.system.persistence";
@Bean
    public DataSource dataSource()
    {
        EmbeddedDatabaseBuilder builder = new EmbeddedDatabaseBuilder();
        return builder.setType(EmbeddedDatabaseType.HSQL)
                .addScript("script/schema.sql")
                .addScript("script/data.sql")
                .build();
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactoryBean(){
    final LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
        entityManagerFactoryBean.setDataSource(dataSource());
        entityManagerFactoryBean.setPackagesToScan(ENTITY_PACKAGES_TO_SCAN);
        entityManagerFactoryBean.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
        entityManagerFactoryBean.setJpaProperties(buildJpaProperties());
        entityManagerFactoryBean.setPersistenceUnitName(PERSISTENCE_UNIT_NAME);
        return entityManagerFactoryBean;

    }*/
}