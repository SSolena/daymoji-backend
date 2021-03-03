package dev.solar.daymoji.configure;

import org.jasypt.encryption.StringEncryptor;
import org.jasypt.encryption.pbe.PooledPBEStringEncryptor;
import org.jasypt.encryption.pbe.config.SimpleStringPBEConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JasyptConfig {
    String password = System.getenv("JASYPT_PASSWORD"); // 서버의 환경변수에 "JASYPT_PASSWORD" 이름으로 등록한 값을 가져와서 사용

    @Bean("jasyptStringEncryptor")
    public StringEncryptor stringEncryptor() {
        PooledPBEStringEncryptor encryptor = new PooledPBEStringEncryptor();
        SimpleStringPBEConfig config = new SimpleStringPBEConfig();
        config.setPassword(password); // 암호화 키 값
        config.setAlgorithm("PBEWithMD5AndDES"); // 사용할 알고리즘
        config.setKeyObtentionIterations("1000"); // 해싱을 반복할 횟수
        config.setPoolSize("1"); // Encryptor를 클론하여 가지고 있을 갯수
        config.setProviderName("SunJCE"); // Default 값? - 없어도 되나 봄
        config.setSaltGeneratorClassName("org.jasypt.salt.RandomSaltGenerator"); // Salt를 생성하는 클래스
        config.setStringOutputType("base64"); // 인코딩 될 값 hexadecimal도 사용가능
        encryptor.setConfig(config);
        return encryptor;
    }
}
