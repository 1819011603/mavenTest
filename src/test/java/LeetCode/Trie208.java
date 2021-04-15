package LeetCode;

import java.util.Arrays;

public class Trie208 {
    Node s;
    public Trie208() {
        s = new Node();
    }
    public void insert(String word) {
        int c;
        Node cur = s;
        for (int i = 0; i < word.length(); i++){
            c = word.charAt(i)-'a';
            if(cur.nodes[c] == null){
                cur.nodes[c] = new Node();
                cur = cur.nodes[c];

            }else cur = cur.nodes[c];
        }
        cur.in = true;
    }
    public boolean search(String word) {
        int c;
        Node cur = s;
        for (int i = 0; i < word.length(); i++){
            c = word.charAt(i)-'a';
            if(cur.nodes[c] == null ){
                return false;

            }else cur = cur.nodes[c];
        }

        return cur.in;
    }
    public boolean startsWith(String word){
        int c;
        Node cur = s;
        for (int i = 0; i < word.length(); i++){
            c = word.charAt(i)-'a';
            if(cur.nodes[c] == null ){
                return false;

            }else cur = cur.nodes[c];
        }
        return true;
    }



    static class Node{

        boolean in = false;
        Node[] nodes;

        Node(){

            nodes = new Node[26];

        }


    }

    public static void main(String[] args) {
        Trie208 trie = new Trie208();
        trie.insert("app");
        trie.insert("apple");
        System.out.println(trie.search("apps"));
        System.out.println(trie.search("app"));

    }


    @Override
    public String toString() {
        return "Trie208{" +
                "s=" + s +
                '}';
    }
}

