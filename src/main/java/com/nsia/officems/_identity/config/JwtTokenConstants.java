package com.nsia.officems._identity.config;

public class JwtTokenConstants {
    public static final long ACCESS_TOKEN_VALIDITY_SECONDS = 30*60*60;
    public static final String SIGNING_KEY = "nSia@#786*";
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER_STRING = "Authorization";
}