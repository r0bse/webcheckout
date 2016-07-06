package de.schroeder.checkout.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import liquibase.Liquibase;
import liquibase.database.Database;
import liquibase.database.DatabaseFactory;
import liquibase.database.jvm.JdbcConnection;
import liquibase.exception.LiquibaseException;
import liquibase.resource.ClassLoaderResourceAccessor;
import org.hibernate.ejb.HibernatePersistence;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.inject.Inject;
import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.Properties;

import static org.slf4j.LoggerFactory.getLogger;

/**
 * @author schroeder
 * @date 06. Jul 2016
 */
@Configuration
@EnableConfigurationProperties( JpaProperties.class )
@EnableJpaRepositories( basePackages = { JpaConfiguration.JPA_PACKAGE } )
@EnableTransactionManagement
public class JpaConfiguration {

    public static final String JPA_PACKAGE = "de.schroeder.checkout.jpa";
    private static final Logger log = getLogger( JpaConfiguration.class );

    @Value( "${application.databasename}" )
    private String dbName;

    @Value( "${liquibase.change-log}" )
    private String changelogPath;

    @Value( "${spring.datasource.driver-class-name}" )
    private String driverClassName;

    @Value( "${spring.datasource.url}" )
    private String url;

    @Value( "${spring.datasource.username}" )
    private String user;

    @Value( "${spring.datasource.password}" )
    private String password;

    @Inject
    private JpaProperties jpaProperties;

    @Bean
    public JpaVendorAdapter jpaVendorAdapter() {
        return new HibernateJpaVendorAdapter();
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() throws SQLException, LiquibaseException {

        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setPackagesToScan( JPA_PACKAGE );
        em.setJpaVendorAdapter( jpaVendorAdapter() );
        em.setJpaProperties( additionalJpaProperties() );
        em.setPersistenceProviderClass(HibernatePersistence.class);
        em.setDataSource(dataSource());
        log.debug( "updating db {} with liquibase", dbName );
        updateDbWithLiquibase( dataSource() );

        return em;
    }

    @Bean
    public DataSource dataSource() {

//        EmbeddedDatabaseBuilder builder = new EmbeddedDatabaseBuilder();
//        EmbeddedDatabase db = builder
//                                .setType( EmbeddedDatabaseType.HSQL )
//                                .setName( dbName )
//                                .build();

        HikariDataSource dataSource = new HikariDataSource( hikariConfig( driverClassName, url, user, password ) );
        return dataSource;
    }

//    @Bean
//    public SpringLiquibase liquibase( DataSource dataSource ) {
//
//        SpringLiquibase sl = new SpringLiquibase();
//        sl.setDataSource( dataSource );
//        sl.setChangeLog( changelogPath );
//        sl.setShouldRun( true );
//        sl.setDropFirst( true );
//
//        return sl;
//    }

    /**
     * provide a connection pooling datasource
     *
     * @param driverClass
     * @param url
     * @param user
     * @param password
     * @return
     */
    private HikariConfig hikariConfig( String driverClass,
                                       String url,
                                       String user,
                                       String password ) {

        HikariConfig config = new HikariConfig();
        config.setDriverClassName( driverClass );
        config.setJdbcUrl( url );
        config.setUsername( user );
        config.setPassword( password );

        return config;
    }

    /**
     * run liquibase on application startup
     *
     * @param ds
     * @throws SQLException
     * @throws LiquibaseException
     */
    private void updateDbWithLiquibase( DataSource ds ) throws SQLException, LiquibaseException {

        Database database = DatabaseFactory.getInstance()
                .findCorrectDatabaseImplementation( new JdbcConnection( ds.getConnection() ) );

        Liquibase liquibase = new Liquibase( changelogPath, new ClassLoaderResourceAccessor(), database );

        log.info( "executing liquibase update" );
        liquibase.update( "" );
        log.info( "finished liquibase update" );
    }

    /**
     * add additional properties to JPA
     *
     * @return
     * @throws SQLException
     * @throws LiquibaseException
     */
    private Properties additionalJpaProperties() throws SQLException, LiquibaseException {

        Properties properties = new Properties();
        properties.putAll( jpaProperties.getProperties() );

        // disable jpa mapping validation
        properties.put( "hibernate.hbm2ddl.auto", "none" );

        log.debug( "using jpa properties:" );
        properties.keySet().stream().forEach( key -> log.debug( "{}:{}", key, properties.get( key ) ) );

        return properties;
    }

    @Bean
    public JpaTransactionManager transactionManager() throws SQLException, LiquibaseException {

        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory( entityManagerFactory().getObject() );
        return transactionManager;
    }
}
