package com.example.config;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;

@Configurable
@Import({DataConfig.class})
@ComponentScan({"com.example.service", "com.example.utils"})
public class RootConfig {
}
