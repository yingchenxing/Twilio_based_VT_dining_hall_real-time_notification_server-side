package com.example.vthacks_serverside.utils;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;

import java.util.Collections;

public class CodeGenerator {
    public static void main(String[] args) {
        generate();
    }

    private static void generate() {
        FastAutoGenerator.create("jdbc:mysql://localhost:3306/enge1414", "root", "123456")
                .globalConfig(builder -> {
                    builder.author("xic")
                            .fileOverride() // cover the exist file
                            .outputDir("D:\\vthacks\\vthacks_serverside\\src\\main\\java\\"); // output directory
                })
                .packageConfig(builder -> {
                    builder.parent("com.example.vthacks_serverside") // set the name of super package
                            .moduleName(null) // set the module name
                            .pathInfo(Collections.singletonMap(OutputFile.mapperXml, "D:\\vthacks\\vthacks_serverside\\src\\main\\resources\\mapper\\")); // output directory for mapperXml
                })
                .strategyConfig(builder -> {
                    builder.entityBuilder().enableLombok();
                    builder.controllerBuilder().enableRestStyle();
                    builder
                            .addInclude("message_info") // table name
//                            .addInclude("hall") // table name
                    ;
                })
//                .templateEngine(new FreemarkerTemplateEngine()) // use template engine
                .execute();
    }

}
