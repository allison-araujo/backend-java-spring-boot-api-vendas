package com.io.github.allison;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class AnimalConfiguration {
    

    @Bean(name="cahorro") 
    public Animal cachorro(){
        return new Animal() {
            @Override
            public void fazerBarulho(){
                System.out.println("Auau auau");
            }
        };
    }


    @Bean(name="gato") 
    public Animal gato(){
        return new Animal() {
            @Override
            public void fazerBarulho(){
                System.out.println("miaaiuuuu");
            }
        };
    }

}
