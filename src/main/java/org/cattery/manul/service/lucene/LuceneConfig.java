package org.cattery.manul.service.lucene;

import org.apache.lucene.store.ByteBuffersDirectory;
import org.apache.lucene.store.Directory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LuceneConfig {

    @Bean
    public Directory makeDirectory() {
        return new ByteBuffersDirectory();
    }
}
