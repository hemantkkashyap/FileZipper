package com.zip.hello;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

class HuffmanNode implements Comparable<HuffmanNode> {
    char data;
    int frequency;
    HuffmanNode left, right;

    public HuffmanNode(char data, int frequency) {
        this.data = data;
        this.frequency = frequency;
        this.left = this.right = null;
    }

    @Override
    public int compareTo(HuffmanNode node) {
        return this.frequency - node.frequency;
    }
}

public class Huffman {

    public static ByteArrayOutputStream compress(byte[] data) {
        // Build frequency table
        int[] frequencyTable = new int[256];
        for (byte b : data) {
            frequencyTable[b & 0xFF]++;
        }

        // Build Huffman tree
        HuffmanNode root = buildHuffmanTree(frequencyTable);

        // Generate Huffman codes
        Map<Character, String> huffmanCodes = generateCodes(root);

        try (ByteArrayOutputStream compressedStream = new ByteArrayOutputStream();
             ObjectOutputStream oos = new ObjectOutputStream(compressedStream)) {

            // Write Huffman codes to the stream
            oos.writeObject(huffmanCodes);

            // Write compressed data to the stream
            for (byte b : data) {
                String code = huffmanCodes.get((char) (b & 0xFF));
                for (char c : code.toCharArray()) {
                    compressedStream.write((c == '0') ? 0 : 1);
                }
            }

            return compressedStream;

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private static HuffmanNode buildHuffmanTree(int[] frequencyTable) {
        PriorityQueue<HuffmanNode> priorityQueue = new PriorityQueue<>(Comparator.comparingInt(node -> node.frequency));

        for (char i = 0; i < 256; i++) {
            if (frequencyTable[i] > 0) {
                priorityQueue.add(new HuffmanNode(i, frequencyTable[i]));
            }
        }

        while (priorityQueue.size() > 1) {
            HuffmanNode left = priorityQueue.poll();
            HuffmanNode right = priorityQueue.poll();

            HuffmanNode internalNode = new HuffmanNode('\0', left.frequency + right.frequency);
            internalNode.left = left;
            internalNode.right = right;

            priorityQueue.add(internalNode);
        }

        return priorityQueue.poll();
    }

    private static Map<Character, String> generateCodes(HuffmanNode root) {
        Map<Character, String> huffmanCodes = new HashMap<>();
        generateCodesRecursive(root, "", huffmanCodes);
        return huffmanCodes;
    }

    private static void generateCodesRecursive(HuffmanNode root, String code, Map<Character, String> huffmanCodes) {
        if (root.left == null && root.right == null) {
            huffmanCodes.put(root.data, code);
        } else {
            generateCodesRecursive(root.left, code + "0", huffmanCodes);
            generateCodesRecursive(root.right, code + "1", huffmanCodes);
        }
    }

    public static void main(String[] args) {
        // Example usage
        byte[] originalData = "hello world".getBytes();

        // Compression
        ByteArrayOutputStream compressedStream = compress(originalData);

        // Print results
        System.out.println("Original: " + new String(originalData));
        System.out.println("Compressed: " + compressedStream.toByteArray());
    }
}
