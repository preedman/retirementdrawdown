package com.reedmanit.retirementdrawdown.service;

import org.junit.jupiter.api.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EncryptPasswordTest {


    //private final ArrayList<AnnualDrawdown> listOfTestAnnualDrawdown = new ArrayList<>();

    public EncryptPasswordTest() {


    }

    @BeforeAll
    public static void setUpClass() {


    }

    @AfterAll
    public static void tearDownClass() {
    }

    @BeforeEach
    public void setUp() {


    }

    @AfterEach
    public void tearDown() {
    }

    @Test
    public void testEncryptPassword() {
        System.out.println("testEncryptPassword");
        String password = "pulsar";
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String testPasswordEncoded = passwordEncoder.encode(password);
        System.out.println("encoded password = "+testPasswordEncoded);





    }


}
