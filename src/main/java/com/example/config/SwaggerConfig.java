//package com.example.config;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.servlet.config.annotation.EnableWebMvc;
//import springfox.documentation.builders.ApiInfoBuilder;
//import springfox.documentation.builders.ParameterBuilder;
//import springfox.documentation.builders.PathSelectors;
//import springfox.documentation.builders.RequestHandlerSelectors;
//import springfox.documentation.schema.ModelRef;
//import springfox.documentation.service.ApiInfo;
//import springfox.documentation.service.Parameter;
//import springfox.documentation.spi.DocumentationType;
//import springfox.documentation.spring.web.plugins.Docket;
//import springfox.documentation.swagger2.annotations.EnableSwagger2;
//
//import java.util.ArrayList;
//import java.util.List;
//
///**
// * @author tiankaiqiang
// * @version 1.0
// * @date 2020/12/17 16:12
// * @describe
// */
//@Configuration
//@EnableSwagger2
//@EnableWebMvc
//public class SwaggerConfig {
//
//    public static final String AUTHORIZATION_HEADER = "Access-Token";
//    public static final String DEFAULT_INCLUDE_PATTERN = "/api/.*";
//
//
//    /**
//     * TODO
//     * 可以根据配置读取是否开启swagger文档，针对测试与生产环境采用不同的配置
//     */
//    private boolean isSwaggerEnable = true;
//
//    @Bean
//    public Docket createRestApi() {
//        ParameterBuilder ticketPar = new ParameterBuilder();
//        List<Parameter> pars = new ArrayList<Parameter>();
//        ticketPar.name(AUTHORIZATION_HEADER).description("Authorization")//Token 以及Authorization 为自定义的参数，session保存的名字是哪个就可以写成那个
//                .modelRef(new ModelRef("string")).parameterType("header")
//                .required(false).build(); //header中的ticket参数非必填，传空也可以
//        pars.add(ticketPar.build());    //根据每个方法名也知道当前方法在设置什么参数
//
//        return new Docket(DocumentationType.SWAGGER_2)
//                //.groupName("group")
//                .enable(true)
//                .apiInfo(apiInfo()).select()
//                // 对所有该包下的Api进行监控，如果想要监控所有的话可以改成any()
//                //.apis(RequestHandlerSelectors.basePackage("com.iscas"))
//                .apis(RequestHandlerSelectors.any())
//                // 对所有路径进行扫描
//                .paths(PathSelectors.any())
//                .build()
//                .globalOperationParameters(pars);
//    }
//
//    /**
//     * @return 生成文档说明信息
//     */
//    private ApiInfo apiInfo() {
//        return new ApiInfoBuilder()
//                .title("TEST系统")
//                .description("描述")
//                //.termsOfServiceUrl("http://gaohanghang.github.io")
//                .license("Apache 2.0")
//                .licenseUrl("http://www.apache.org/licenses/LICENSE-2.0")
//                .version("2.0.0").build();
//    }
//
//}
//
