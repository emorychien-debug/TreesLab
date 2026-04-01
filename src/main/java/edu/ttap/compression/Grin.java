package edu.ttap.compression;

import java.io.IOException;

/**
 * The driver for the Grin compression program.
 */
public class Grin {
    /**
     * Verifies input stream begins with the magic num denot
     * @param in input stream
     */
    private static void checkMagicNum (BitInputStream in) {
        int magicNumber = (int)in.readBits(32); // check magic number
        if(magicNumber != 1846) {
            throw new IllegalArgumentException(); // Throw error if a grin file is not recieved
        }
    }

    /**
     * Decodes the .grin file denoted by infile and writes the output to the
     * .grin file denoted by outfile.
     * @param infile the file to decode
     * @param outfile the file to ouptut to
     */
    public static void decode(String infile, String outfile) throws IOException {
        BitInputStream in = new BitInputStream(infile);
        BitOutputStream out = new BitOutputStream(outfile);

        checkMagicNum(in);
        
        HuffmanTree tree = new HuffmanTree(in);
        tree.decode(in, out);
        in.close();
        out.close();
    }

    /**
     * The entry point to the program.
     * @param args the command-line arguments.
     */
    public static void main(String[] args) throws IOException {
        if(args.length != 2) {
            System.err.println("Usage: java Grin <infile> <outfile>");
        } else {
            try {
            decode(args[0], args[1]);
            } catch (IllegalArgumentException e) {
                System.err.println("Invalid file type");
            } catch (IOException e) {
                System.err.println("Invalid file name(s)");
            }
        }
    }
}