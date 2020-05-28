package es.multiple.was.config;

import java.util.HashMap;

import javax.sql.DataSource;

import org.hibernate.engine.transaction.jta.platform.internal.WebSphereJtaPlatform;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.lookup.JndiDataSourceLookup;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;

@Configuration
@EnableJpaRepositories(
    basePackages = "es.multiple.was.ds2.respository", 
    entityManagerFactoryRef = "ds2EntityManager"
)
public class DsTwoConfig {
	     
		@Value("${spring.datasource.second.jndi-name}")
	    private String secondJndiName;
	
	    private JndiDataSourceLookup lookup = new JndiDataSourceLookup();
		    
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
	    }
}
