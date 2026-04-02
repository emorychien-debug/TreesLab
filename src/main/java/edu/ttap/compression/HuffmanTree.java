package edu.ttap.compression;


/**
 * A HuffmanTree derives a space-efficient coding of a collection of byte
 * values.
 *
 * The huffman tree encodes values in the range 0--255 which would normally
 * take 8 bits.  However, we also need to encode a special EOF character to
 * denote the end of a .grin file.  Thus, we need 9 bits to store each
 * byte value.  This is fine for file writing (modulo the need to write in
 * byte chunks to the file), but Java does not have a 9-bit data type.
 * Instead, we use the next larger primitive integral type, short, to store
 * our byte values.
 */
public class HuffmanTree {
    private class Node {
        short val;
        Node left;
        Node right;
        boolean isInterior;
        public Node (Node left, Node right) {
            this.left = left;
            this.right = right;
            isInterior = true;
        }
        public Node (short val) {
            this.val = val;
            isInterior = false;
        }
    }

    private Node root; // Stores the huffman tree

    private Node treeBuilder (BitInputStream in) {
        if(in.readBit() == 0) {
            return new Node((short)in.readBits(9));
        } else { 
            Node left = treeBuilder(in);
            Node right = treeBuilder(in);
            return new Node(left, right);
        }
    }
    /**
     * Constructs a new HuffmanTree from the given file.
     * @param in the input file (as a BitInputStream)
     */
    public HuffmanTree(BitInputStream in) {
        this.root = treeBuilder(in); // makes the tree recursively
    }
    

    /**
     * Decodes a stream of huffman codes from a file given as a stream of
     * bits into their uncompressed form, saving the results to the given
     * output stream. Note that the EOF character is not written to out
     * because it is not a valid 8-bit chunk (it is 9 bits).
     * @param in the file to decompress.
     * @param out the file to write the decompressed output to.
     */
    public void decode(BitInputStream in, BitOutputStream out) {
        Node cur;
        while(true) {
            cur = root;
            while(cur.isInterior) {
                if(in.readBit() == 0) {
                    cur = cur.left;
                } else {
                    cur = cur.right;
                }
            }
            if(cur.val == 0x100) { //eof
                System.out.println("found eof");
                return;
            }
            out.writeBits(cur.val, 8);
        }
    }
}
// EOF = 1 0000 0000
// 0x100