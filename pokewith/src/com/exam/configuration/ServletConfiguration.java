package com.exam.configuration;

import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.mapper.MapperFactoryBean;
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

import com.exam.mapper.CommentMapper;
import com.exam.mapper.PostMapper;
import com.exam.mapper.UserMapper;


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
	
	@Value("${facebook.clientid}")
	private String facebook_clientid;
	@Value("${facebook.secretcode}")
	private String facebook_secretcode;

	
	@Override
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
	
	
	@Bean
	public SqlSessionFactory sqlSessionFactory(BasicDataSource basicDataSource) throws Exception{
		SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
		factoryBean.setDataSource(basicDataSource);
		SqlSessionFactory sqlSessionFactory = factoryBean.getObject();
		return sqlSessionFactory;
	}
	
	@Bean
	public FacebookConnectionFactory facebookConnctionFactory() throws Exception{
		FacebookConnectionFactory facebook = new FacebookConnectionFactory(facebook_clientid,facebook_secretcode);
		return facebook;
	}
	
	@Bean
	public OAuth2Parameters oAuth2Parameters(){
		OAuth2Parameters oa = new OAuth2Parameters();
		oa.setScope("email");
		oa.setRedirectUri("https://192.168.1.136:8443/facebookSignInCallback");
		return oa;
	}
	
	@Bean
	public MapperFactoryBean<UserMapper> mapperFactoryBean(SqlSessionFactory sqlSessionFactory) {
		MapperFactoryBean<UserMapper> factoryBean = new MapperFactoryBean<>(UserMapper.class);
		factoryBean.setSqlSessionFactory(sqlSessionFactory);
		return factoryBean;
	}
	
	@Bean
	public MapperFactoryBean<PostMapper> mapperFactoryBean2(SqlSessionFactory sqlSessionFactory) {
		MapperFactoryBean<PostMapper> factoryBean = new MapperFactoryBean<>(PostMapper.class);
		factoryBean.setSqlSessionFactory(sqlSessionFactory);
		return factoryBean;
	}
	
	@Bean
	public MapperFactoryBean<CommentMapper> mapperFactoryBean3(SqlSessionFactory sqlSessionFactory) {
		MapperFactoryBean<CommentMapper> factoryBean = new MapperFactoryBean<>(CommentMapper.class);
		factoryBean.setSqlSessionFactory(sqlSessionFactory);
		return factoryBean;
	}
}