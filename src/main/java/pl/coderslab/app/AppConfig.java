package pl.coderslab.app;

import javax.persistence.EntityManagerFactory;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalEntityManagerFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
@EnableWebMvc
@EnableTransactionManagement
@ComponentScan(basePackages = "pl.coderslab")
public class AppConfig extends WebMvcConfigurerAdapter {

	@Bean
	public LocalEntityManagerFactoryBean entityManagerFactoryBean() {
		LocalEntityManagerFactoryBean emfb = new LocalEntityManagerFactoryBean();
		emfb.setPersistenceUnitName("bookstorePersistenceUnit");
		return emfb;
	}
/*	ziarno	definiuje	fabrykę
	EntityManagera	(zarządcy	encji)	-	obiektu
	którym	będziemy	się	posługiwać	w	celu
	wykonywania	operacji	na	naszych	encjach*/

	@Bean
	public JpaTransactionManager transactionManager(EntityManagerFactory emf) {
		JpaTransactionManager tm = new JpaTransactionManager(emf);
		return tm;
	}
/*	Określamy	również	sposób	zarządzania
	transakcjami	-	włączamy	zarządzanie
	transakcjami	przez	Springa*/

}