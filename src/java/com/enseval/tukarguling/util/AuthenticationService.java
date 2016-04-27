package com.enseval.tukarguling.util;

public interface AuthenticationService
{
    boolean login(String p0, String p1);
    
    void logout();
    
    UserCredential getUserCredential();
}
