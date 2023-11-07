package kr.co.fns.chat.core.config;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.connection.netty.NettyStreamFactoryFactory;
import com.mongodb.reactivestreams.client.MongoClient;
import com.mongodb.reactivestreams.client.MongoClients;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.data.mapping.model.SnakeCaseFieldNamingStrategy;
import org.springframework.data.mongodb.ReactiveMongoDatabaseFactory;
import org.springframework.data.mongodb.ReactiveMongoTransactionManager;
import org.springframework.data.mongodb.config.AbstractReactiveMongoConfiguration;
import org.springframework.data.mongodb.core.convert.DefaultMongoTypeMapper;
import org.springframework.data.mongodb.core.convert.MappingMongoConverter;
import org.springframework.data.mongodb.core.convert.MongoCustomConversions;
import org.springframework.data.mongodb.core.convert.NoOpDbRefResolver;
import org.springframework.data.mongodb.core.mapping.MongoMappingContext;
import org.springframework.transaction.ReactiveTransactionManager;
import org.springframework.transaction.reactive.TransactionalOperator;

@Slf4j
@Configuration
@Profile("local|dev")
@RequiredArgsConstructor
public class LocalMongoConfig extends AbstractReactiveMongoConfiguration {

    @Value("${spring.data.mongodb.host}")
    private String host;

    @Value("${spring.data.mongodb.port}")
    private String port;

    @Value("${spring.data.mongodb.database}")
    private String database;

    @Value("${spring.data.mongodb.username}")
    private String user;

    @Value("${spring.data.mongodb.password}")
    private String password;

    @Override
    protected String getDatabaseName() {
        return database;
    }

    @Override
    public MongoClient reactiveMongoClient() {
        log.info("Applying AWS DocumentDB Configuration");
        MongoClientSettings settings = configureClientSettings();
        return MongoClients.create(settings);
    }

    @SneakyThrows
    protected MongoClientSettings configureClientSettings() {
        return MongoClientSettings.builder()
            .streamFactoryFactory(NettyStreamFactoryFactory.builder().build())
            .applyConnectionString(getConnectionString())
            .build();

//        MongoClientSettings.Builder builder = MongoClientSettings.builder();
//        builder.applyConnectionString(getConnectionString());
//        var endOfCertificateDelimiter = "-----END CERTIFICATE-----";
//        //File resource = resourceLoader.getResource("classpath:cert/rds-combined-ca-bundle.pem").getFile();  // new ClassPathResource("cert/rds-combined-ca-bundle.pem").getFile();
//
//        ClassPathResource resources = new ClassPathResource("cert/rds-combined-ca-bundle.pem");
//        InputStream inputStream = resources.getInputStream();
//        File resource = File.createTempFile("rds-combined-ca-bundle", "pem");
//
//        try {
//            FileUtils.copyInputStreamToFile(inputStream, resource);
//        }finally {
//            inputStream.close();
//        }
//
//        String pemContents = new String(Files.readAllBytes(resource.toPath()));
//        var allCertificates = Arrays.stream(pemContents
//                .split(endOfCertificateDelimiter))
//            .filter(line -> !line.isBlank())
//            .map(line -> line + endOfCertificateDelimiter)
//            .collect(Collectors.toUnmodifiableList());
//
//        var certificateFactory = CertificateFactory.getInstance("X.509");
//        var keyStore = KeyStore.getInstance(KeyStore.getDefaultType());
//        // This allows us to use an in-memory key-store
//        keyStore.load(null);
//
//        for (int i = 0; i < allCertificates.size(); i++) {
//            var certString = allCertificates.get(i);
//            var caCert = certificateFactory.generateCertificate(new ByteArrayInputStream(certString.getBytes()));
//            keyStore.setCertificateEntry(String.format("AWS-certificate-%s", i), caCert);
//        }
//
//        var trustManagerFactory = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
//        trustManagerFactory.init(keyStore);
//
//        var sslContext = SSLContext.getInstance("TLS");
//        sslContext.init(null, trustManagerFactory.getTrustManagers(), null);
//
//        builder.applyToSslSettings(ssl -> ssl.enabled(true).context(sslContext));

//        return builder.build();
    }

    private ConnectionString getConnectionString() {
        String str = String.format("mongodb://%s:%s@%s:%s/%s?authSource=admin&retryWrites=false",
                user,
                password,
                host,
                port,
                database
        );

        return new ConnectionString(str);
    }

    @Bean
    public ReactiveMongoTransactionManager transactionManager(ReactiveMongoDatabaseFactory factory) {
        return new ReactiveMongoTransactionManager(factory);
    }

    @Bean
    public TransactionalOperator transactionOperator(ReactiveTransactionManager manager) {
        return TransactionalOperator.create(manager);
    }
    @Bean
    @Primary
    @Override
    public MappingMongoConverter mappingMongoConverter(ReactiveMongoDatabaseFactory databaseFactory,
        MongoCustomConversions customConversions, MongoMappingContext mappingContext) {
        mappingContext.setFieldNamingStrategy(new SnakeCaseFieldNamingStrategy());
        mappingContext.setAutoIndexCreation(true);
        MappingMongoConverter converter = new MappingMongoConverter(NoOpDbRefResolver.INSTANCE, mappingContext);
        converter.setCustomConversions(customConversions);
        converter.setCodecRegistryProvider(databaseFactory);
        converter.setTypeMapper(new DefaultMongoTypeMapper(null));
        return converter;
    }
}