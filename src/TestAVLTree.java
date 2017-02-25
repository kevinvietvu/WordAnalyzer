/**
 * @author Kevin Vu
 */

public class TestAVLTree {
    public static void main(String[] args) {
        boolean error = false;

        AVLTree<Integer> AVLtree = new AVLTree<>();
        for(int i = 0; i < 16; i++) {
            AVLtree.incCount(i);
        }

        DataCount<Integer>[] dataCounts = new DataCount[AVLtree.getSize()];

        AVLtree.traverse(AVLtree.overallRoot, dataCounts, 0);

        error = dataCounts.length != 16;

        // Checks if the BST condition holds after all the rotations made
        for(int i = 0; i < dataCounts.length - 1 && !error; i++) {
            if(dataCounts[i].data > dataCounts[i + 1].data) {
                error = true;
            }
        }

        // Increment counts only for even numbers
        for(int i = 0; i < 16; i += 2) {
            AVLtree.incCount(i);
        }

        // Check the counts
        for(int i = 0; i < dataCounts.length; i++) {
            if(i % 2 == 0) {
                error = dataCounts[i].count != 2;
            } else {
                error = dataCounts[i].count != 1;
            }
        }

        if(error) {
            System.out.println("Tests failed!");
        } else {
            System.out.println("Tests passed!");
        }
    }
}