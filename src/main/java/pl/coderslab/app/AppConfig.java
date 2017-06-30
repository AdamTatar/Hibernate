package pl.coderslab.app;

import java.util.Locale;

import javax.persistence.EntityManagerFactory;
import javax.validation.Validator;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.format.FormatterRegistry;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalEntityManagerFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.servlet.LocaleContextResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import pl.coderslab.controller.AuthorConverter;

@Configuration
@EnableWebMvc
@EnableTransactionManagement
@ComponentScan(basePackages = "pl.coderslab")
@EnableJpaRepositories(basePackages	=	"pl.coderslab.repository")
public class AppConfig extends WebMvcConfigurerAdapter {

	@Bean
	public LocalEntityManagerFactoryBean entityManagerFactory() {
		LocalEntityManagerFactoryBean emfb = new LocalEntityManagerFactoryBean();
		emfb.setPersistenceUnitName("bookstorePersistenceUnit");
		return emfb;
	}
	/*
	 * ziarno definiuje fabrykę EntityManagera (zarządcy encji) - obiektu którym
	 * będziemy się posługiwać w celu wykonywania operacji na naszych encjach
	 */

	@Bean
	public JpaTransactionManager transactionManager(EntityManagerFactory emf) {
		JpaTransactionManager tm = new JpaTransactionManager(emf);
		return tm;
	}
	/*
	 * Określamy również sposób zarządzania transakcjami - włączamy zarządzanie
	 * transakcjami przez Springa
	 */

	@Bean
	public ViewResolver viewResolver() {
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		viewResolver.setPrefix("/WEB-INF/views/");
		viewResolver.setSuffix(".jsp");
		return viewResolver;
	}

	// rejestruję konwerter:
	@Override
	public void addFormatters(FormatterRegistry registry) {
		registry.addConverter(authorConverter());
	}

	@Bean
	public AuthorConverter authorConverter() {
		return new AuthorConverter();
	}

	//żeby komunikaty z validatora były po polsku:
	@Bean(name = "localeResolver")
	public LocaleContextResolver getLocaleContextResolver() {
		SessionLocaleResolver localeResolver = new SessionLocaleResolver();
		localeResolver.setDefaultLocale(new Locale("pl", "PL"));
		return localeResolver;
	}

	@Bean
	public Validator validator() {
		return new LocalValidatorFactoryBean();
	}
	
	@Bean
	public MessageSource messageSource() {
		ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
		messageSource.setBasename("messages");	// teraz mogę zrobić plik messages.properties i tam obsługiwać błędy
		// na przykład typeMischmatch
		// w docs spring DefaultMessageCodesResolver
		return messageSource;
}
	
}