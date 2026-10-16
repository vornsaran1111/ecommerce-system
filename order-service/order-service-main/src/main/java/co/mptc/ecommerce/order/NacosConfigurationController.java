package co.mptc.ecommerce.order;


import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("nacos/config")
@RequiredArgsConstructor
//@RefreshScope
public class NacosConfigurationController {

    private final NacosConfigProps nacosConfigProps;

//    @Value("${service.name}")
//    private String serviceName;
//
//    @Value("${service.info}")
//    private String serviceInfo;
//
//    @Value("${service.version}")
//    private String serviceVersion;


    @GetMapping
    public Map<String, Object> getConfig()
    {
        return Map.of(
                "ServiceName", nacosConfigProps.getName(),
                "ServiceInfo", nacosConfigProps.getInfo(),
                "ServiceVersion", nacosConfigProps.getVersion()
        );
    }
}
