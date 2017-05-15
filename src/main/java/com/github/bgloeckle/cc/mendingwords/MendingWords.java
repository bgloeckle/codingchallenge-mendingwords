package com.github.bgloeckle.cc.mendingwords;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.NavigableSet;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.concurrent.atomic.AtomicInteger;

public class MendingWords {
    private BufferedReader wordReader;
    private NavigableSet<String> eightCharWords = new TreeSet<>();
    private Set<String> smallWords = new HashSet<>();

    public MendingWords(BufferedReader wordReader) {
        this.wordReader = wordReader;
    }

    public void go() throws IOException {
        String word;
        while ((word = wordReader.readLine()) != null) {
            if (word.length() > 8) {
                continue;
            }
            if (word.length() == 8) {
                eightCharWords.add(word);
            } else {
                smallWords.add(word);
            }
        }
        System.out.println(
                        "Found " + eightCharWords.size() + " 8-char words & " + smallWords.size() + " smaller words.");

        AtomicInteger count = new AtomicInteger(0);

        smallWords.stream().parallel().forEach(small -> {
            SortedSet<String> tail = eightCharWords.tailSet(small);
            for (Iterator<String> it = tail.iterator(); it.hasNext();) {
                String w = it.next();
                if (w.startsWith(small)) {
                    String rest = w.substring(small.length());
                    if (smallWords.contains(rest)) {
                        count.incrementAndGet();
                    }
                }
            }
        });

        System.out.println("Found " + count.get() + " words matching the criteria.");
    }
}
