
package com.example.demo.Entity;

public class Model {
    private String modelId;
    private String apiBase;
    private String apiKey;
    private double temperature;

    public Model() {
        this.modelId = "gemini-2.0-flash-exp";
        this.apiBase = "https://generativelanguage.googleapis.com/v1beta/openai/";
        this.apiKey = "AIzaSyDORL2Gmic46k0F_Z46cKnhB07e5PT1aVE";
        this.temperature = 0.7;
    }
}