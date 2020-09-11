package com.exam.configuration;

import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.social.facebook.connect.FacebookConnectionFactory;
import org.springframework.social.oauth2.OAuth2Parameters;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;

import com.exam.controller.WebSocketChatController;

<<<<<<< HEAD
import lombok.RequiredArgsConstructor;
=======
>>>>>>> 73945c5b839eb04b0044164b018754b3ba3fa744

@Configuration
@EnableWebMvc
@ComponentScan("com.exam")
@PropertySource("/WEB-INF/properties/db.properties")
public class ServletConfiguration implements WebMvcConfigurer {

	@Value("${db.classname}")
	private String db_classname;
	@Value("${db.url}")
	private String db_url;
	@Value("${db.username}")
	private String db_username;
	@Value("${db.password}")
	private String db_password;
	
<<<<<<< HEAD
=======
	@Value("${facebook.clientid}")
	private String facebook_clientid;
	@Value("${facebook.secretcode}")
	private String facebook_secretcode;

	
	@Override
>>>>>>> 73945c5b839eb04b0044164b018754b3ba3fa744
	public void configureViewResolvers(ViewResolverRegistry registry) {
		registry.jsp("/views/", ".jsp");
	}
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/**").addResourceLocations("/resources/");
	}
	
	@Bean
	public BasicDataSource basicDataSource() {
		BasicDataSource basicDataSource = new BasicDataSource();
			basicDataSource.setDriverClassName(db_classname);
		basicDataSource.setUrl(db_url);
		basicDataSource.setUsername(db_username);
		basicDataSource.setPassword(db_password);
		return basicDataSource;
	}
	
//	@Bean
//	public JdbcTemplate jdbcTemplate(BasicDataSource basicDataSource) {
//		JdbcTemplate jdbcTemplate = new JdbcTemplate(basicDataSource);
//		return jdbcTemplate;
//	}
	
	@Bean
	public SqlSessionFactory sqlSessionFactory(BasicDataSource basicDataSource) throws Exception{
		SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
		factoryBean.setDataSource(basicDataSource);
		SqlSessionFactory sqlSessionFactory = factoryBean.getObject();
		return sqlSessionFactory;
	}
	
	
<<<<<<< HEAD
	
	
=======
	@Bean
	public OAuth2Parameters oAuth2Parameters(){
		OAuth2Parameters oa = new OAuth2Parameters();
		oa.setScope("email");
		oa.setRedirectUri("https://localhost:8443/facebookSignInCallback");
		return oa;
	}
>>>>>>> 73945c5b839eb04b0044164b018754b3ba3fa744
	
}