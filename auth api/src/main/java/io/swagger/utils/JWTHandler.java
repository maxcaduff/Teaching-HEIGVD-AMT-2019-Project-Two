package io.swagger.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;

import java.util.Date;

/**
 * Created on 10.01.20.
 *
 * @author Max
 */
public class JWTHandler {
    private static final String secret = "wonderfulSecretWhichShouldBePassedAsEnv";
    private static final String issuer = "AuthApi";
    private static final Algorithm algorithm = Algorithm.HMAC256(secret);
    // Reusable verifier instance
    private static final JWTVerifier verifier = JWT.require(algorithm)
                                                    .withIssuer(issuer)
                                                    .build();

    /**
     * Verifies secret and issuer of JWT, if valid returns the encoded email
     * */
    public static String verifyJWT (String token) {
        try {
            String decoded = verifier.verify(token).getSubject();
            return decoded == null ? "" : decoded;
        }
        catch (JWTVerificationException exception) {
            //Invalid signature/claims
            return "";
        }
    }

    public static String createJWT(String email) {
        try {
            return  JWT.create()
                    .withIssuer(issuer)
                    .withSubject(email)
                    .withExpiresAt(new Date(System.currentTimeMillis() + 600000))
                    .sign(algorithm);
        }
        catch (JWTCreationException exception) {
            //Invalid Signing configuration / Couldn't convert Claims.
            return "";
        }
    }
}
