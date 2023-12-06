package com.java.udemy.police_hacker;

public abstract class Hacker extends Thread{
    private Vault vault;

    public Hacker(Vault vault) {
        this.vault = vault;
        this.setName(this.getClass().getSimpleName());
        this.setPriority(Thread.MAX_PRIORITY);
    }

    @Override
    public synchronized void start() {
        System.out.println("Starting thread "+this.getName());
        super.start();
    }
}
