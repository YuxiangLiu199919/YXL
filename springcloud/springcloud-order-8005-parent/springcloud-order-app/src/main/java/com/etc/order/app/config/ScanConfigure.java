package com.etc.order.app.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages ={"com.etc.**.manager*", "com.etc.**.dao*",
        "com.etc.**.utils*","com.etc.order.service*","com.etc.pay.client.PayClient"})
public class ScanConfigure {
}