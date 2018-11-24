package vn.edu.saigontech.SGTEnglishClub.Configurations;

import java.util.Arrays;
import java.util.Properties;
import javax.sql.DataSource;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import vn.edu.saigontech.SGTEnglishClub.DAOs.AdminDAO;
import vn.edu.saigontech.SGTEnglishClub.DAOs.FileDAO;
import vn.edu.saigontech.SGTEnglishClub.DAOs.MaterialDAO;
import vn.edu.saigontech.SGTEnglishClub.DAOs.MaterialTypeDAO;
import vn.edu.saigontech.SGTEnglishClub.DAOs.NewsDAO;
import vn.edu.saigontech.SGTEnglishClub.DAOs.NewsTypeDAO;
import vn.edu.saigontech.SGTEnglishClub.DAOs.TipDAO;
import vn.edu.saigontech.SGTEnglishClub.DAOs.TipTypeDAO;
import vn.edu.saigontech.SGTEnglishClub.DAOs.VideoDAO;
import vn.edu.saigontech.SGTEnglishClub.DAOs.VideoTypeDAO;





@Configuration
@ComponentScan("vn.edu.saigontech.SGTEnglishClub.*")
@EnableTransactionManagement
@PropertySource("classpath:databaseConfig.properties")
@Import({ SecurityConfig.class })
public class ApplicationContextConfig {

	@Autowired
	private Environment env;

	@Bean(name = "dataSource")
	public DataSource getDataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();

		dataSource.setDriverClassName(env.getProperty("ds.database-driver"));
		dataSource.setUrl(env.getProperty("ds.url"));
		dataSource.setUsername(env.getProperty("ds.username"));
		dataSource.setPassword(env.getProperty("ds.password"));

		return dataSource;
	}

	@Autowired
	@Bean(name = "sessionFactory")
	public SessionFactory getSessionFactory(DataSource dataSource) throws Exception {
		Properties properties = new Properties();
		properties.put("hibernate.dialect", env.getProperty("hibernate.dialect"));
		properties.put("hibernate.show_sql", env.getProperty("hibernate.show_sql"));
		properties.put("hibernate.hbm2ddl.auto", env.getProperty("hibernate.hbm2ddl.auto"));
		properties.put("current_session_context_class", env.getProperty("current_session_context_class"));
		properties.put("hibernate.enable_lazy_load_no_trans", "true");
		properties.put("hibernate.connection.CharSet", "utf8");
		properties.put("hibernate.connection.characterEncoding", "utf8");
		properties.put("hibernate.connection.useUnicode", "true");
		
		LocalSessionFactoryBean factoryBean = new LocalSessionFactoryBean();
		factoryBean.setPackagesToScan(new String[] { "vn.edu.saigontech.SGTEnglishClub.Models" });
		factoryBean.setDataSource(dataSource);
		factoryBean.setHibernateProperties(properties);
		factoryBean.afterPropertiesSet();
		SessionFactory sf = factoryBean.getObject();
		return sf;
	}

	@Autowired
	@Bean(name = "transactionManager")
	public HibernateTransactionManager getTransactionManager(SessionFactory sessionFactory) {
		HibernateTransactionManager transactionManager = new HibernateTransactionManager(sessionFactory);

		return transactionManager;
	}
	@Bean(name = "corsConfigurationSource")
	public CorsConfigurationSource corsConfigurationSource() {
		CorsConfiguration configuration = new CorsConfiguration();
		configuration.setAllowedOrigins(Arrays.asList("*"));
		configuration.setAllowedMethods(Arrays.asList("GET", "POST", "OPTIONS", "DELETE", "PUT"));
		configuration.setAllowedHeaders(Arrays.asList("x-atlassian-token", "charset", "authorization", "Content-Type",
				"content-type", "x-requested-with", "Access-Control-Allow-Origin", "Access-Control-Allow-Headers",
				"x-auth-token", "x-app-id", "Origin", "Accept", "X-Requested-With", "Access-Control-Request-Method",
				"Access-Control-Request-Headers"));
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", configuration);
		return source;
	}

	@Bean(name = "multipartResolver")
	public CommonsMultipartResolver multipartResolver() {
		CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
		multipartResolver.setDefaultEncoding("utf-8");
		multipartResolver.setMaxUploadSize(10000000);
		return multipartResolver;
	}
	
	@Bean(name = "adminDAO")
	public AdminDAO getAdminDAO() {
		return new AdminDAO();
	}
	
	@Bean(name = "videoDAO")
	public VideoDAO	getVideoDAO() {
		return new VideoDAO();
	}
	@Bean(name = "videoTypeDAO")
	public VideoTypeDAO	getVideoTypeDAO() {
		return new VideoTypeDAO();
	}
	
	@Bean(name="eMaDAO")
	public MaterialDAO getMaterialDAO() {
		return new MaterialDAO();
	}
	@Bean(name="eMTypeDAO")
	public MaterialTypeDAO getMaterialTypeDAO() {
		return new MaterialTypeDAO();
	}
	
	@Bean(name = "fileDao")
	public FileDAO getFileDAO() {
		return new FileDAO();
	}
	
	@Bean(name="newsDAO")
	public NewsDAO getNewsDAO() {
		return new NewsDAO();
	}
	@Bean(name="newsTypeDAO")
	public NewsTypeDAO getNewsTypeDAO() {
		return new NewsTypeDAO();
	}
	
	
	@Bean(name="tipDAO")
	public TipDAO getTipDAO() {
		return new TipDAO();
	}
	@Bean(name="tipTypeDao")
	public TipTypeDAO getTipTypeDao() {
		return new TipTypeDAO();
	}

}