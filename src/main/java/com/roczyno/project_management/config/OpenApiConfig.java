package com.roczyno.project_management.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.servers.Server;

@OpenAPIDefinition(
        info = @Info(
                contact = @Contact(
                        name = "Jacob Adiaba",
                        email = "jacob.adiaba@gmail.com"
                ),
                description = "Project management app where users can manage tasks, collaborate effectively," +
                        " and streamline workflows. Users have the ability to assign tasks, invite team members to tasks" +
                        ", create and track issues, and engage in discussions by commenting on specific issues." +
                        " With intuitive features and seamless communication, this application empowers teams to" +
                        " efficiently organize projects, prioritize tasks, and achieve their goals. Stay connected, " +
                        "stay productive!"
                ,
                version = "1.0",
                termsOfService = "Terms of service"
        ),
        servers = @Server(
                description = "Local ENV",
                url = "http://localhost:8080"
        )
)
@SecurityScheme(
        name = "bearerAuth",
        description = "Login to get the jwt token",
        scheme = "bearer",
        type = SecuritySchemeType.HTTP,
        bearerFormat = "JWT",
        in = SecuritySchemeIn.HEADER
)
public class OpenApiConfig {

}
