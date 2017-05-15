package com.github.bgloeckle.cc.mendingwords;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;

public class Main {
    public static void main(String[] args) throws FileNotFoundException, IOException {
        try (FileInputStream fis = new FileInputStream(args[0])) {
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(fis, Charset.forName("UTF-8")))) {
                new MendingWords(reader).go();
            }
        }
    }
}
