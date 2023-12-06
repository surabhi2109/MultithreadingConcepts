package com.java.udemy.police_hacker;

public class Vault {
    private int password;

    public Vault(int password) {
        this.password = password;
    }

    public boolean isPasswordCorrect(int guess){
        try {
            Thread.sleep(5);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        return guess == this.password;
    }
}
