package fr.norsys.config;

import java.net.URI;
import java.net.URISyntaxException;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfiguration {

    private static final String JNDI_NAME = "java:comp/env/jdbc/bonecp";

    // @Bean
    // public DataSource dataSource() throws NamingException {
    // final Context ctx = new InitialContext();
    // // final JndiDataSourceLookup lookup = new JndiDataSourceLookup();
    // return (DataSource) ctx.lookup( JNDI_NAME );
    // }

    @Bean
    public BasicDataSource dataSource() throws URISyntaxException {
        final URI dbUri = new URI( System.getenv( "DATABASE_URL" ) );

        final String username = dbUri.getUserInfo().split( ":" )[0];
        final String password = dbUri.getUserInfo().split( ":" )[1];
        final String dbUrl = "jdbc:postgresql://" + dbUri.getHost() + ':' + dbUri.getPort() + dbUri.getPath();

        final BasicDataSource basicDataSource = new BasicDataSource();
        basicDataSource.setUrl( dbUrl );
        basicDataSource.setUsername( username );
        basicDataSource.setPassword( password );

        return basicDataSource;
    }

}
