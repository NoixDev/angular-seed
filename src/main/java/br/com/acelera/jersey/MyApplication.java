	package br.com.acelera.jersey;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import javax.ws.rs.ApplicationPath;

import org.glassfish.jersey.server.ResourceConfig;

/**
 *
 * @author aluno
 */
@ApplicationPath("rest")
public class MyApplication extends ResourceConfig {

    public MyApplication() {
        packages("br.com.acelera.jersey.controllers");
    }
}