package es.multiple.was.config;

import java.util.HashMap;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.hibernate.engine.transaction.jta.platform.internal.WebSphereJtaPlatform;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.DependsOn;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.lookup.JndiDataSourceLookup;
//import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.jta.WebSphereUowTransactionManager;

//import com.atomikos.jdbc.AtomikosDataSourceBean;

//@DependsOn("transactionManager")
@Configuration
@EnableJpaRepositories(
    basePackages = "es.multiple.was.ds2.respository", 
    entityManagerFactoryRef = "ds2EntityManager"//, 
    //transactionManagerRef = "transactionManager"
    //transactionManagerRef = "ds2TransactionManager"
)
public class DsTwoConfig {
	     
		@Value("${spring.datasource.second.jndi-name}")
	    private String secondJndiName;
	
	    private JndiDataSourceLookup lookup = new JndiDataSourceLookup();
	
	   // @Autowired
	   // private WebSphereUowTransactionManager wasds2;
	    
	    @Bean
	    public LocalContainerEntityManagerFactoryBean ds2EntityManager(EntityManagerFactoryBuilder builder) {
	    	
	    	HashMap<String, Object> properties = new HashMap<String, Object>();
			properties.put("hibernate.transaction.jta.platform", WebSphereJtaPlatform.class.getName());
			properties.put("javax.persistence.transactionType", "JTA");
	    	
	    	return builder.dataSource(ds2DataSource())
	        .packages("es.multiple.was.ds2.entity")
	        .persistenceUnit("ds2")
	        .jta(true)
	        .properties(properties)
	        .build();
	    }
	 
	    @Bean(destroyMethod = "")
	    public DataSource ds2DataSource() {
	  
	    	return lookup.getDataSource(secondJndiName);
	    	
	    	/*XADataSource ds = (XADataSource) lookup.getDataSource(secondJndiName);

			AtomikosDataSourceBean xaDataSource = new AtomikosDataSourceBean();
			xaDataSource.setXaDataSource(ds);
			xaDataSource.setUniqueResourceName("xads2");
			return xaDataSource;*/
	    }
	 
	    /*@Bean
	    public PlatformTransactionManager ds2TransactionManager(@Qualifier("ds2EntityManager") EntityManagerFactory entityManagerFactory) {
	  
	    	/*WebSphereUowTransactionManager was = new WebSphereUowTransactionManager();
	    	was.setAutodetectTransactionManager(true);
	    	
	    	return wasds2;
	        //return new JpaTransactionManager(entityManagerFactory);
	    }*/
}
