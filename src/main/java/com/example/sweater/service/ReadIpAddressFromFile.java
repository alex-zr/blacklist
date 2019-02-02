package com.example.sweater.service;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


@Service
public class ReadIpAddressFromFile {
    private  List<String> ipAddress = new ArrayList<>();
    private File file = new File("templates/BlackListIp.txt");
    private Scanner scanner;
    @Scheduled(fixedRate = 5000)
    public void readAndAddToList(){

        {
            try {
                scanner = new Scanner(file);
                while (scanner.hasNextLine()){
                    ipAddress.add(scanner.nextLine());
                }
            } catch (FileNotFoundException e) {
                System.out.println("Sorry! File not found");

            }

        }

    }


    public List<String> getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(List<String> ipAddress) {
        this.ipAddress = ipAddress;
    }
}
