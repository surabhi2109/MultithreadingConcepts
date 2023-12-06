package com.java.udemy.police_hacker;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Client {
    public static final int MAX_PASSWORD = 9999;
    public static void main(String[] args) {
        Random random = new Random();
        List<Thread> threadList = new ArrayList<>();
        Vault vault = new Vault(random.nextInt(MAX_PASSWORD));
        threadList.add(new AscendingHackerThread(vault));
        threadList.add(new DescendingHackerThread(vault));
        threadList.add(new PoliceThread());

        for(Thread thread : threadList){
            thread.start();
        }
    }

    private static class Vault{
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

    private static abstract class HackerThread extends Thread{
        Vault vault;

        public HackerThread(Vault vault){
            this.vault = vault;
            this.setName(this.getClass().getSimpleName());
            this.setPriority(Thread.MAX_PRIORITY);
        }

        @Override
        public synchronized void start() {
            System.out.println("Starting Thread "+this.getName());
            super.start();
        }
    }

    private static class AscendingHackerThread extends HackerThread{
        public AscendingHackerThread(Vault vault) {
            super(vault);
        }

        @Override
        public void run() {
            for(int guess = 0;guess < MAX_PASSWORD;guess++){
                if(vault.isPasswordCorrect(guess)){
                    System.out.println(this.getName()+" guessed the password : "+guess);
                    System.exit(0);
                }
            }
        }
    }

    private static class DescendingHackerThread extends HackerThread{
        public DescendingHackerThread(Vault vault) {
            super(vault);
        }

        @Override
        public void run() {
            for(int guess=MAX_PASSWORD;guess>=0;guess--) {
                if(vault.isPasswordCorrect(guess)){
                    System.out.println(this.getName()+" guessed the password : "+guess);
                    System.exit(0);
                }
            }
        }
    }

    private static class PoliceThread extends Thread{
        public PoliceThread() {
            System.out.println("Starting Police Thread");
            this.setName(this.getClass().getSimpleName());
            this.setPriority(Thread.MAX_PRIORITY);
        }

        @Override
        public void run() {
            for(int i=10;i>0;i--){
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println(i);
            }
            System.out.println("Game over for you hackers!!!");
            System.exit(0);
        }
    }
}
