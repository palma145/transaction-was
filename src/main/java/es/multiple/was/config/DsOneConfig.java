package es.multiple.was.config;

import java.util.HashMap;

import javax.sql.DataSource;

import org.hibernate.engine.transaction.jta.platform.internal.WebSphereJtaPlatform;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.lookup.JndiDataSourceLookup;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;

//@DependsOn("transactionManager")
@Configuration
@EnableJpaRepositories(
    basePackages = "es.multiple.was.ds1.respository", 
    entityManagerFactoryRef = "ds1EntityManager"//, 
    //transactionManagerRef = "transactionManager"
    //transactionManagerRef = "ds1TransactionManager"
)
public class DsOneConfig {
	     
		@Value("${spring.datasource.primary.jndi-name}")
	    private String primaryJndiName;
	
	    private JndiDataSourceLookup lookup = new JndiDataSourceLookup();
	
	   // @Autowired
	    //private WebSphereUowTransactionManager wasds1;
	    
	    @Bean
	    @Primary
	    public LocalContainerEntityManagerFactoryBean ds1EntityManager(EntityManagerFactoryBuilder builder) {
	    	
	    	HashMap<String, Object> properties = new HashMap<String, Object>();
			properties.put("hibernate.transaction.jta.platform", WebSphereJtaPlatform.class.getName());
			properties.put("javax.persistence.transactionType", "JTA");
	    	
	    	return builder.dataSource(ds1DataSource())
	        .packages("es.multiple.was.ds1.entity")
	        .persistenceUnit("ds1")
	        .jta(true)
	        .properties(properties)
	        .build();
	    }
	 
	    @Primary
	    @Bean(destroyMethod = "")
	    public DataSource ds1DataSource() {
	  
	    	return lookup.getDataSource(primaryJndiName);
	    	
	    	/*XADataSource ds = (XADataSource) lookup.getDataSource(primaryJndiName);

			AtomikosDataSourceBean xaDataSource = new AtomikosDataSourceBean();
			xaDataSource.setXaDataSource(ds);
			xaDataSource.setUniqueResourceName("xads1");
			return xaDataSource;*/
	    }
	 
	    /*@Primary
	    @Bean
	    public PlatformTransactionManager ds1TransactionManager() {
	  
	    	return wasds1;
	        //return new JtaTransactionManager();
	    }*/
}
