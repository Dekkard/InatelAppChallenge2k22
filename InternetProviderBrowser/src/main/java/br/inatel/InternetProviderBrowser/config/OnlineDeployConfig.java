package br.inatel.InternetProviderBrowser.config;

import javax.sql.DataSource;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

//@Configuration
public class OnlineDeployConfig {

	public static final String dbUrl = System.getenv("DATABASE_URL");

//	@Bean
	public DataSource dataSource() {
		HikariConfig config = new HikariConfig();
		config.setJdbcUrl(dbUrl);
		return new HikariDataSource(config);
	}
}
