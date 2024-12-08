package Greedy;

import java.util.PriorityQueue;
import java.util.Comparator;

class HuffmanNode {
    char character;
    int frequency;
    HuffmanNode left;
    HuffmanNode right;
}

// Comparator لتحديد الأولوية بناءً على التردد
class FrequencyComparator implements Comparator<HuffmanNode> {
    public int compare(HuffmanNode x, HuffmanNode y) {
        return x.frequency - y.frequency;
    }
}

class Huffman {

    // دالة لطباعة شفرات هوفمان
    public static void printHuffmanCodes(HuffmanNode root, String code) {
        if (root == null) {
            return;
        }

        // إذا كان العقدة ورقة (leaf node)، اطبع الحرف مع الشفرة الخاصة به
        if (root.left == null && root.right == null) {
            System.out.println(root.character + " : " + code);
            return;
        }

        printHuffmanCodes(root.left, code + "0");
        printHuffmanCodes(root.right, code + "1");
    }

    public static void main(String[] args) {
        // الحروف وتردداتها
        char[] characters = { 'a', 'b', 'c', 'd', 'e', 'f' };
        int[] frequencies = { 5, 9, 12, 13, 16, 45 };

        // إنشاء قائمة أولوية لتخزين العقد (nodes)
        PriorityQueue<HuffmanNode> queue = new PriorityQueue<>(new FrequencyComparator());

        // إنشاء عقد لكل حرف وإضافتها إلى قائمة الأولوية
        for (int i = 0; i < characters.length; i++) {
            HuffmanNode node = new HuffmanNode();
            node.character = characters[i];
            node.frequency = frequencies[i];
            node.left = null;
            node.right = null;
            queue.add(node);
        }

        // بناء شجرة هوفمان
        while (queue.size() > 1) {
            // استخراج أقل عقدتين من حيث التردد
            HuffmanNode left = queue.poll();
            HuffmanNode right = queue.poll();

            // إنشاء عقدة جديدة تجمع بين العقدتين
            HuffmanNode newNode = new HuffmanNode();
            newNode.character = '-'; // عقدة داخلية
            newNode.frequency = left.frequency + right.frequency;
            newNode.left = left;
            newNode.right = right;

            // إضافة العقدة الجديدة إلى قائمة الأولوية
            queue.add(newNode);
        }

        // جذر شجرة هوفمان
        HuffmanNode root = queue.poll();

        // طباعة شفرات هوفمان
        System.out.println("Huffman Codes:");
        printHuffmanCodes(root, "");
    }
}
